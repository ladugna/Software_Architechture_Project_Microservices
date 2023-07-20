package com.example.userservice.Controller;


import com.example.userservice.Domain.User;
import com.example.userservice.Respository.UserRepository;
import com.example.userservice.Service.JwtTokenService;
import com.example.userservice.dto.auth.AuthRequest;
import com.example.userservice.dto.auth.AuthResponse;
import com.example.userservice.errors.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
public class AuthController {

    private final JwtTokenService tokenService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("/authenticate")
    AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findFirstByUsername(authRequest.getUsername()).orElseThrow(() -> new UserNotFoundException("username doesn't exist"));
//        User user = userRepository.findFirstByUsername(authRequest.getUsername()).orElse(null);
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword()))
            return new AuthResponse(tokenService.generateToken(user));
        return new AuthResponse("Credentials not valid");
    }
}
