package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.UserRequestDto;
import com.school.schoolstat.models.dto.responses.UserResponseDto;
import com.school.schoolstat.models.entities.User;
import com.school.schoolstat.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserRequestDto userRequest){
        User user = modelMapper.map(userRequest, User.class);

        return ResponseEntity.ok(
             modelMapper.map(userService.createUser(user), UserResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<User> users = userService.retrieveUser();

        return ResponseEntity.ok(
                users.stream().map(
                        user -> modelMapper.map(user, UserResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") User user){
        return ResponseEntity.ok(
                modelMapper.map(user, UserResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") User id, @RequestBody UserRequestDto userRequest){
        User user = modelMapper.map(userRequest, User.class);
        user.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(userService.updateUser(user), UserResponseDto.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") User id){
        userService.deleteUser(id);
        return ResponseEntity.ok("deleted successful");
    }
}
