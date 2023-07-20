package com.example.teacherservice.controller;

import com.example.teacherservice.DTO.TeacherDTO;
import com.example.teacherservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }

    @DeleteMapping("/{id}")
    public void removeTeacher(@PathVariable String id) {
        teacherService.removeTeacher(id);
    }

    @PutMapping("/{id}")
    public TeacherDTO updateTeacher(@PathVariable String id, @RequestBody TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(id, teacherDTO);
    }

//    @GetMapping
//    public List<TeacherDTO> getAllTeachers() {
//        return teacherService.getAllTeachers();
//    }
    @GetMapping
    public String test(){
        return "teacher controller is working";
    }

}
