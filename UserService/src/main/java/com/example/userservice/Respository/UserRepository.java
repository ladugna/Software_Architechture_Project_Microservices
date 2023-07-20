package com.example.userservice.Respository;

import com.example.userservice.Domain.User;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findFirstByUsername(String username);
}
