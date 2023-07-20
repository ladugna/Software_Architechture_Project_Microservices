package com.example.studentservice.service;

import com.example.studentservice.DTO.RewardItemDTO;
import com.example.studentservice.DTO.StudentDTO;
import com.example.studentservice.ResourceNotFoundException;
import com.example.studentservice.model.RewardItem;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        student.setScore(1000); // Set initial score to 1000
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }
    public Student getStudent(String id){
        return studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
    }


    public void removeStudent(String id) {
        studentRepository.deleteById(id);
    }

    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setId(studentDTO.getId());
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setStudentNumber(studentDTO.getStudentNumber());
            student.setSchool(studentDTO.getSchool());
            student.setStudentClassDTO(studentDTO.getStudentClassDTO());
            student.setScore(studentDTO.getScore());
            student.setAvatar(studentDTO.getAvatar());
            student.setRewards(studentDTO.getRewards());
            student.setElements(studentDTO.getElements());
            Student updatedStudent = studentRepository.save(student);
            return convertToDTO(updatedStudent);
        }
        return null;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setStudentNumber(studentDTO.getStudentNumber());
        student.setSchool(studentDTO.getSchool());
        student.setStudentClassDTO(studentDTO.getStudentClassDTO());
        student.setScore(studentDTO.getScore());
        student.setAvatar(studentDTO.getAvatar());
        student.setRewards(studentDTO.getRewards()); // Assigning directly without conversion
        return student;
    }

    private List<RewardItem> convertToRewardItems(List<RewardItemDTO> rewardItemDTOs) {
        return rewardItemDTOs.stream()
                .map(this::convertToRewardItem)
                .collect(Collectors.toList());
    }

    private RewardItem convertToRewardItem(RewardItemDTO rewardItemDTO) {
        RewardItem rewardItem = new RewardItem();
        rewardItem.setName(rewardItemDTO.getName());
        rewardItem.setValue(rewardItemDTO.getValue());
        return rewardItem;
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setStudentNumber(student.getStudentNumber());
        studentDTO.setSchool(student.getSchool());
        studentDTO.setStudentClassDTO(student.getStudentClassDTO());
        studentDTO.setScore(student.getScore());
        studentDTO.setAvatar(student.getAvatar());
        studentDTO.setRewards(student.getRewards()); // Assigning directly without conversion
        return studentDTO;
    }

    private List<RewardItemDTO> convertToRewardItemsDTO(List<RewardItem> rewardItems) {
        return rewardItems.stream()
                .map(this::convertToRewardItemDTO)
                .collect(Collectors.toList());
    }

    private RewardItemDTO convertToRewardItemDTO(RewardItem rewardItem) {
        RewardItemDTO rewardItemDTO = new RewardItemDTO();
        rewardItemDTO.setName(rewardItem.getName());
        rewardItemDTO.setValue(rewardItem.getValue());
        return rewardItemDTO;
    }

}
