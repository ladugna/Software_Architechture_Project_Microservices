package com.example.userservice.auth;

import com.example.userservice.Domain.User;
import com.example.userservice.Respository.UserRepository;
import com.example.userservice.Service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    JwtTokenService tokenService;

    UserRepository userRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain chain) throws ServletException, IOException {
        // look for Bearer auth header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        final String token = header.substring(7);
        final String username = tokenService.validateTokenAndGetUsername(token);
        if (username == null) {
            // validation failed or token expired
            chain.doFilter(request, response);
            return;
        }
        Optional<User> optionalUser = userRepository.findFirstByUsername(username);
        if(optionalUser.isEmpty()){
            chain.doFilter(request, response);
            return;
        }
        User user = optionalUser.get();
//        System.out.println("get user: <"+user+">");
        // set user details on spring security context
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // continue with authenticated user
        chain.doFilter(request, response);
    }
}
