package com.example.schoolservice.Respository;

import com.example.schoolservice.Domain.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository extends MongoRepository<School, String> {
}
