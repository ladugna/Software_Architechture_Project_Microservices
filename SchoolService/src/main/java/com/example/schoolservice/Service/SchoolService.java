package com.example.schoolservice.Service;

import com.example.schoolservice.Domain.School;
import com.example.schoolservice.Dto.SchoolDto;
import com.example.schoolservice.Respository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public SchoolDto addSchool(SchoolDto schoolDto) {
        School school = mapDtoToSchool(schoolDto);
        School newSchool = schoolRepository.save(school);
        return mapSchoolToDto(newSchool);
    }

    public List<SchoolDto> getAllSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream()
                .map(this::mapSchoolToDto)
                .collect(Collectors.toList());
    }

    public Optional<SchoolDto> getSchoolById(String id) {
        return schoolRepository.findById(id).map(this::mapSchoolToDto);
    }

    public void removeSchool(String id) {
        schoolRepository.deleteById(id);
    }

    public SchoolDto updateSchool(SchoolDto schoolDto) {
        Optional<School> existingSchool = schoolRepository.findById(schoolDto.getId());
        if (existingSchool.isPresent()) {
            School school = existingSchool.get();
            mapDtoToSchool(schoolDto, school);
            School updatedSchool = schoolRepository.save(school);
            return mapSchoolToDto(updatedSchool);
        } else {
            return null;
        }
    }

    private SchoolDto mapSchoolToDto(School school) {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(school.getId());
        schoolDto.setName(school.getName());
        schoolDto.setAddress(school.getAddress());
        schoolDto.setEmail(school.getEmail());
        schoolDto.setPhone(school.getPhone());
        return schoolDto;
    }

    private School mapDtoToSchool(SchoolDto schoolDto) {
        School school = new School();
        mapDtoToSchool(schoolDto, school);
        return school;
    }

    private void mapDtoToSchool(SchoolDto schoolDto, School school) {
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setEmail(schoolDto.getEmail());
        school.setPhone(schoolDto.getPhone());
    }
}
