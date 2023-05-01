package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.AuthRequest;
import com.school.schoolstat.models.dto.requests.UpdateRequest;
import com.school.schoolstat.models.dto.requests.UserRequestDto;
import com.school.schoolstat.models.dto.responses.JwtResponse;
import com.school.schoolstat.models.dto.responses.ResponseMessage;
import com.school.schoolstat.models.entities.User;
import com.school.schoolstat.services.impelements.AuthServiceImpl;
import com.school.schoolstat.services.interfaces.UserService;
import com.school.schoolstat.services.interfaces.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UtilsService utilsService;


    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String basicString) throws Exception {
        String[] credentials = utilsService.extractCredentials(basicString);
        if (credentials != null && credentials.length == 2) {
            String username = credentials[0];
            String password = credentials[1];
            return authService.login(username, password);
        } else {
            return new ResponseEntity<>(new JwtResponse("", 400, "", "Invalid credentials!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String basicString) throws Exception {
        String[] credentials = utilsService.extractCredentials(basicString);
        if (credentials != null && credentials.length == 2) {
            String username = credentials[0];
            String sessionId = credentials[1];
            boolean resp = authService.logout(username, sessionId);
            return ResponseEntity.ok(resp);
        } else {
            return new ResponseEntity<>(new JwtResponse("", 400, "", "Invalid credentials!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userDto,
                                      @RequestHeader("Authorization") String basicString) throws Exception {
        String[] credentials = utilsService.extractCredentials(basicString);
        if (credentials != null && credentials.length == 2) {
            String username = credentials[0];
            String password = credentials[1];
            User user = userService.save(userDto, username, password);
            if (user == null) {
                return new ResponseEntity<>(new ResponseMessage(400, "email or Phone number invalide"),
                        HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(new ResponseMessage(200, "User successfully added!"));
        }

        return new ResponseEntity<>(new ResponseMessage(400, "Invalid credentials!"), HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestBody AuthRequest request) {
        if (!authService.validate(request.getCredential(), request.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage(400, "code invalide or username incorrect"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ResponseMessage(200, "Correct OTP code"));
    }

    @PostMapping("/add-role")
    public ResponseEntity<?> addRoleToAccount(@RequestBody AuthRequest request) {
        if (authService.AddRoleToUser(request.getUsername(), request.getCredential())) {
            return ResponseEntity.ok(new ResponseMessage(200, "Role successfully added"));
        }
        return new ResponseEntity<>(new ResponseMessage(400, "Account does not exist or is suspended!"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/remove-role")
    public ResponseEntity<?> RemoveRoleToAccount(@RequestBody AuthRequest request) {
        if (authService.RemoveRoleToUser(request.getUsername(), request.getCredential())) {
            return ResponseEntity.ok(new ResponseMessage(200, "Role successfully removed!"));
        }
        return new ResponseEntity<>(new ResponseMessage(400, "Account does not exist!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/enable-disable")
    public ResponseEntity<?> EnableDisableAccount(@RequestBody AuthRequest request) {
        if (authService.EnabledOrDisabledAccount(request.getUsername(), request.isEnable())) {
            return ResponseEntity.ok(new ResponseMessage(200, "Operation successfully completed!"));
        }
        return new ResponseEntity<>(new ResponseMessage(400, "CAccount does not exist!"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/forgot-password/{username}")
    public ResponseEntity<?> forgotPassword(@PathVariable String username) {

        if (authService.forgotPassword(username)) {
            return ResponseEntity.ok(new ResponseMessage(200,
                    "username correct, validate your otp code send in your phone number or email"));
        }

        return new ResponseEntity<>(new ResponseMessage(400, "username  does not exist!"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "update-password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody UpdateRequest request) {
        if (token != null) {
            try {
                if (!authService.isExpireToken(request.getUsername(), token.substring(7))) {
                    if (authService.updatePassword(request)) {
                        return ResponseEntity
                                .ok(new ResponseMessage(200, "your password has been successfully changed"));
                    }
                }
                return new ResponseEntity<>(new ResponseMessage(400, "your login token has expired!"),
                        HttpStatus.BAD_REQUEST);

            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseMessage(400, "invalid token!"), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(new ResponseMessage(400, "the token does not exist"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/reset-password/{username}")
    public ResponseEntity<?> resetPassword(@PathVariable String username, @RequestHeader("Authorization") String basicToken) {
        if (basicToken != null) {
            String[] credentials = utilsService.extractCredentials(basicToken);

            if (credentials != null && credentials.length == 2) {
                String optCode = credentials[0];
                String newPassword = credentials[1];
                System.out.println(newPassword+optCode);
                if (authService.resetPassword(username, optCode, newPassword)) {
                    return ResponseEntity.ok(new ResponseMessage(200, "your password has been successfully reset"));
                }

            }
        }

        return new ResponseEntity<>(new ResponseMessage(400, "The token does not exist!"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/token-check/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkToken(@RequestHeader("Authorization") String token,
                                        @PathVariable(required = true) String username) {
        Boolean resp;
        try {
            if (token != null) {
                resp = authService.isExpireToken(username, token.substring(7));
                return ResponseEntity.ok().body(new ResponseMessage(200, resp.toString()));
            } else {
                return new ResponseEntity<>(new ResponseMessage(401, "Invalid token!"), HttpStatus.FORBIDDEN);
            }
        } catch (ExpiredJwtException ex) {
            resp = true;
            return ResponseEntity.ok().body(new ResponseMessage(200, resp.toString()));
        } catch (MalformedJwtException ex) {
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(
                    new ResponseMessage(400, "new gisResponseMessage(400, \"This token is malformed!\")"),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
