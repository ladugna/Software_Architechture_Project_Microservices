package com.example.userservice.Service;


import com.example.userservice.Domain.User;
import com.example.userservice.Respository.UserRepository;
import com.example.userservice.dto.CreateUserDto;
import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User CreateUser(CreateUserDto createUser) {
        User user = new User();
        user.setUsername(createUser.getUsername());
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        user.setRole(createUser.getRole());

        User newUser = userRepository.save(user);
        return newUser;
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(newUser, userDto);
//        return userDto;
    }

    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no user with this Id"));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public UserDto update(String id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user with this id not found"));
        user.setUsername(updateUserDto.getUsername());
        user.setPassword(updateUserDto.getPassword());
        user.setRole(updateUserDto.getRole());

        User Update = userRepository.save(user);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(Update, userDto);
        return userDto;
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

//    private UserDto mapUserToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setUsername(user.getUsername());
//        userDto.setRole(user.getRole());
//        return userDto;
//    }

}
