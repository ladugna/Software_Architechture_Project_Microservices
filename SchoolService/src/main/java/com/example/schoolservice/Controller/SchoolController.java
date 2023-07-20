package com.example.schoolservice.Controller;

import com.example.schoolservice.Dto.SchoolDto;
import com.example.schoolservice.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/we")
    public ResponseEntity<SchoolDto> addSchool(@RequestBody SchoolDto schoolDto) {
        SchoolDto newSchool = schoolService.addSchool(schoolDto);
        return new ResponseEntity<>(newSchool, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Specify the path variable name inside curly braces
    public ResponseEntity<SchoolDto> getSchoolById(@PathVariable String id) {
        Optional<SchoolDto> school = schoolService.getSchoolById(id);
        return school.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}") // Specify the path variable name inside curly braces
    public ResponseEntity<Void> deleteSchool(@PathVariable String id) {
        schoolService.removeSchool(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}") // Specify the path variable name inside curly braces
    public ResponseEntity<SchoolDto> updateSchool(@PathVariable String id, @RequestBody SchoolDto schoolDto) {
        schoolDto.setId(id);
        SchoolDto updatedSchool = schoolService.updateSchool(schoolDto);
        if (updatedSchool != null) {
            return new ResponseEntity<>(updatedSchool, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
