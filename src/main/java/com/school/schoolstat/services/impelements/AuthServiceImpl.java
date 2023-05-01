package com.school.schoolstat.services.impelements;

import com.school.schoolstat.config.jwt.JwtTokenUtil;
import com.school.schoolstat.dao.AccountRepository;
import com.school.schoolstat.dao.RoleRepository;
import com.school.schoolstat.dao.SessionRepository;
import com.school.schoolstat.models.dto.requests.UpdateRequest;
import com.school.schoolstat.models.dto.responses.JwtResponse;
import com.school.schoolstat.models.entities.Account;
import com.school.schoolstat.models.entities.Role;
import com.school.schoolstat.models.entities.Session;
import com.school.schoolstat.models.enums.AccounStatus;
import com.school.schoolstat.services.interfaces.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public ResponseEntity<?> login(String username, String password) {
        boolean isAuthenticate = false;
        Account account = accountRepository.findByEmailOrPhone(username);

        if (account == null) {
            return new ResponseEntity<>(new JwtResponse(username, 401, "", "No account found with username " + username), HttpStatus.UNAUTHORIZED);
        } else {
            if(account.isDisable()){
                return new ResponseEntity<>(new JwtResponse(username, 401, "", "your account has been blocked please contact gis management for more information!"), HttpStatus.UNAUTHORIZED);
            } else {
                isAuthenticate = this.checkEmailOrPhonePasswordMatch(account, username, password);
            }
            if(isAuthenticate) {

                String infos = username + ":" + password + ":" + (new Date()).getTime();
                String chiffre = encoder.encode(infos);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                JwtResponse logResponse= new JwtResponse(username, 200, chiffre, "Successfully authentification");

                String token = jwtUtil.generateToken(userDetails, account.getId());
                logResponse.setJwttoken(token);

                Session session = new Session();
                session.setId(chiffre);
                session.setLastConnectionAt(LocalDateTime.now());
                sessionRepository.save(session);

                return new ResponseEntity<>(logResponse,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new JwtResponse(username, 401, "", "Password incorrect!"),HttpStatus.UNAUTHORIZED);
            }
        }
    }

    private boolean checkEmailOrPhonePasswordMatch(Account account, String username, String password) {
        if (account.getEmail().equals(username)){
            if(account.isEmailValidated()){
                return this.checkPassworkMatch(password, account.getPassword());
            }
        }else {
            if(account.isPhoneValidated()){
                return this.checkPassworkMatch(password, account.getPassword());
            }
        }
        return false;
    }

    @Override
    public boolean logout(String username, String sessionId) {
        Optional<Session> optSession = sessionRepository.findById(sessionId);
        Session sess = (optSession.isPresent()) ? optSession.get() : null;
        if (sess == null) {
            return false;
        } else {
            if (sess.getUsername().equals(username)) {
                sess.setLastDisconnectionAt(LocalDateTime.now());
                sessionRepository.save(sess);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean AddRoleToUser(String username, String rolename) {
        Account account = accountRepository.findByEmailOrPhone(username);
        if ((account != null && !account.getStatus().equals(AccounStatus.VALIDATE)) || account == null) {
            return false;
        }
        Role role = roleRepository.findByName(rolename);
        if (account.getRoles() == null) {
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            account.setRoles(roles);
        } else {
            account.getRoles().add(role);
        }
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean RemoveRoleToUser(String username, String rolename) {
        Account account = accountRepository.findByEmailOrPhone(username);
        if (account == null) {
            return false;
        }
        Role role = roleRepository.findByName(rolename);
        if (account.getRoles() != null) {
            account.getRoles().remove(role);
            if (account.getRoles().isEmpty()) {
                account.setDisable(true);
            }
            accountRepository.save(account);
            return true;
        }

        return false;
    }

    @Override
    public boolean resetPassword(String username, String otpcode, String newPassword) {
        Account account = accountRepository.findByEmailOrPhone(username);
        if (account != null) {
            if ((account.getEmail().equals(username)&& account.getEmailOtp().equals(otpcode))) {
                if(account.getStatus().equals(AccounStatus.AWAITING_OTP_CODE)|| account.getStatus().equals(AccounStatus.AWAITING_VALIDATE_OTP_CODE))
                    account.setStatus(AccounStatus.VALIDATE);
                else
                    account.setStatus(AccounStatus.EMAIL_VALIDATE);
                account.setPassword(encoder.encode(newPassword));
                accountRepository.save(account);
                // send email
                return true;
            }else {
                if ((account.getPhone().equals(username)&& account.getPhoneOtp().equals(otpcode))) {
                    if(account.getStatus().equals(AccounStatus.AWAITING_OTP_CODE)|| account.getStatus().equals(AccounStatus.AWAITING_VALIDATE_OTP_CODE))
                        account.setStatus(AccounStatus.VALIDATE);
                    else
                        account.setStatus(AccounStatus.PHONE_VALIDATE);
                    account.setPassword(encoder.encode(newPassword));
                    accountRepository.save(account);

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean updatePassword(UpdateRequest request ) {
        Account account = accountRepository.findByEmailOrPhone(request.getUsername());
        if (account != null) {
            if (encoder.matches(request.getOldPassord(), account.getPassword())) {
                if(account.getStatus().equals(AccounStatus.VALIDATE)) {
                    account.setStatus(AccounStatus.VALIDATE);
                }else {
                    if(account.getStatus().equals(AccounStatus.PHONE_VALIDATE))
                        account.setStatus(AccounStatus.PHONE_VALIDATE);
                    else
                        account.setStatus(AccounStatus.EMAIL_VALIDATE);
                }
                account.setPassword(encoder.encode(request.getNewpassword()));
                accountRepository.save(account);
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean forgotPassword(String username) {
        Account account = accountRepository.findByEmailOrPhone(username);
        String otp = UserServiceImpl.getRandomNumberString();
        if (account != null) {
            if (account.getEmail()!=null &&account.getEmail().equals(username)) {
                if(account.getStatus().equals(AccounStatus.VALIDATE))
                    account.setStatus(AccounStatus.AWAITING_OTP_CODE);
                else {
                    if (account.getStatus().equals(AccounStatus.PHONE_VALIDATE))
                        account.setStatus(AccounStatus.AWAITING_VALIDATE_OTP_CODE);
                    else
                        account.setStatus(AccounStatus.AWAITING_EMAIL_OTP_CODE);
                }
                account.setEmailOtp(otp);
                //send email
            } else {
                if(account.getStatus().equals(AccounStatus.VALIDATE))
                    account.setStatus(AccounStatus.AWAITING_OTP_CODE);
                else {
                    if (account.getStatus().equals(AccounStatus.EMAIL_VALIDATE))
                        account.setStatus(AccounStatus.AWAITING_VALIDATE_OTP_CODE);
                    else
                        account.setStatus(AccounStatus.AWAITING_PHONE_OTP_CODE);
                }
                account.setPhoneOtp(otp);
                //send sms
            }
            accountRepository.save(account);
            // call the methode who to send the otp code

            return true;
        }

        return false;
    }

    @Override
    public boolean validate(String otpCode, String username) {
        Account account = accountRepository.findByEmailOrPhone(username);
        boolean resp = false;

        if (account != null) {
            if (account.getEmail().equals(username)) {
                //cas ou c'est l'email qu'il faut valider une fonction pour valider l'email
                if ((account.getEmailOtp().equals(otpCode) )) {
                    account.setEmailValidated(true);
                    resp = true;
                }
            } else{
                //cas ou c'est le phone qu'il faut valider une fonction pour valider le phone
                if ((account.getPhoneOtp().equals(otpCode) )) {
                    account.setPhoneValidated(true);
                    resp = true;
                }
            }

            if (resp) { accountRepository.save(account); }
        }

        return resp;
    }

    @Override
    public Boolean EnabledOrDisabledAccount(String username, boolean status) {
        Account account = accountRepository.findByEmailOrPhone(username);
        if (account == null) {
            return null;
        }
        if (status) {
            account.setDisable(false);
        }else {
            account.setDisable(true);
        }
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean isExpireToken(String username, String token) throws ExpiredJwtException, MalformedJwtException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return !jwtUtil.validateToken(token, userDetails);
    }

    @Override
    public boolean checkPassworkMatch(String sendPassword, String registerPassword) {
        // TODO Auto-generated method stub
        return encoder.matches(sendPassword, registerPassword);

    }




}

