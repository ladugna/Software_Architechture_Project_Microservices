package com.example.userservice.Controller;


import com.example.userservice.Domain.Role;
import com.example.userservice.Domain.User;
import com.example.userservice.Service.UserService;
import com.example.userservice.dto.CreateUserDto;
import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body((User) authentication.getPrincipal());
    }
    @PostMapping("/teachers")
    public ResponseEntity<User> CreateTeacher(@RequestBody CreateUserDto createUserDto) {
        createUserDto.setRole(Role.Teacher);
        User userDto = userService.CreateUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    @PostMapping("/students")
    public ResponseEntity<User> CreateStudent(@RequestBody CreateUserDto createUserDto) {
        createUserDto.setRole(Role.Student);
        User userDto = userService.CreateUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> Update(@PathVariable String id, @PathVariable UpdateUserDto updateUserDto) {
//        UserDto userDto = userService.update(id, updateUserDto);
//        if (userDto != null) {
//            return ResponseEntity.ok(userDto);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UpdateUserDto updateUserDto) {
        UserDto userDto = userService.update(id, updateUserDto);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllAUser() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }
}
