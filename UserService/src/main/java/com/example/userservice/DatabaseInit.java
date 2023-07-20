package com.example.userservice;

import com.example.userservice.Domain.Role;
import com.example.userservice.Respository.UserRepository;
import com.example.userservice.Service.UserService;
import com.example.userservice.dto.CreateUserDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseInit implements CommandLineRunner {
    UserRepository userRepository;
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Create admin if not exists
        if (userRepository.findFirstByUsername("admin").isEmpty()) {
            userService.CreateUser(new CreateUserDto("admin", "admin", Role.Admin));
        }
        // Create teacher if not exists
        if (userRepository.findFirstByUsername("teacher").isEmpty()) {
            userService.CreateUser(new CreateUserDto("teacher", "teacher", Role.Teacher));
        }
//
//        // Create student if not exists
        if (userRepository.findFirstByUsername("student").isEmpty()) {
            userService.CreateUser(new CreateUserDto("student", "student", Role.Student));
        }
    }
}
