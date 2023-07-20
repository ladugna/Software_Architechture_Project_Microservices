package com.example.teacherservice.service;

import com.example.teacherservice.DTO.TeacherDTO;
import com.example.teacherservice.Repository.TeacherRepository;
import com.example.teacherservice.event.TeacherAddedEvent;
import com.example.teacherservice.model.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private final KafkaTemplate<String, TeacherAddedEvent> kafkaTemplate;



    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);

  kafkaTemplate.send("notificationTopic",new TeacherAddedEvent(savedTeacher.getFirstName(), savedTeacher.getLastName(), savedTeacher.getSchool()));
    log.info("teacher with firstname "+ savedTeacher.getFirstName()+  "\n teacher with lastname "+ savedTeacher.getLastName()+"\n teacher with School "+savedTeacher.getSchool()+" added");
        return convertToDTO(savedTeacher);
    }

    public void removeTeacher(String id) {
        teacherRepository.deleteById(id);
    }

    public TeacherDTO updateTeacher(String id, TeacherDTO teacherDTO) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher.setContact(teacherDTO.getContact());
            teacher.setSchool(teacherDTO.getSchool());
            teacher.setTeachingClass(teacherDTO.getTeachingClass());

            Teacher updatedTeacher = teacherRepository.save(teacher);
            return convertToDTO(updatedTeacher);
        }
        return null;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setContact(teacherDTO.getContact());
        teacher.setSchool(teacherDTO.getSchool());
        teacher.setTeachingClass(teacherDTO.getTeachingClass());
        return teacher;
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setContact(teacher.getContact());
        teacherDTO.setSchool(teacher.getSchool());
        teacherDTO.setTeachingClass(teacher.getTeachingClass());
        return teacherDTO;
    }
}
