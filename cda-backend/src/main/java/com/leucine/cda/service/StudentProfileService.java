// src/main/java/com/leucine/cda/service/StudentProfileService.java
package com.leucine.cda.service;

import com.leucine.cda.dto.StudentProfileDTO;
import com.leucine.cda.model.StudentProfile;
import com.leucine.cda.model.User;
import com.leucine.cda.model.Department;
import com.leucine.cda.repository.StudentProfileRepository;
import com.leucine.cda.repository.UserRepository;
import com.leucine.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<StudentProfileDTO> getAllStudentProfiles() {
        return studentProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentProfileDTO getStudentProfileById(long id) {
        Optional<StudentProfile> optionalStudentProfile = studentProfileRepository.findById(id);
        return optionalStudentProfile.map(this::convertToDTO).orElse(null);
    }

    public StudentProfileDTO createStudentProfile(StudentProfileDTO studentProfileDTO) {
        Optional<User> userOptional = userRepository.findById(studentProfileDTO.getUserId());
        Optional<Department> departmentOptional = departmentRepository.findById(studentProfileDTO.getDepartmentId());

        if (userOptional.isPresent() && departmentOptional.isPresent()) {
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setUser(userOptional.get());
            studentProfile.setPhoto(studentProfileDTO.getPhoto());
            studentProfile.setDepartment(departmentOptional.get());
            // Set other properties as needed

            StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);
            return convertToDTO(savedStudentProfile);
        }
        return null; // or handle as appropriate
    }

    public StudentProfileDTO updateStudentProfile(long id, StudentProfileDTO studentProfileDTO) {
        Optional<StudentProfile> optionalStudentProfile = studentProfileRepository.findById(id);
        if (optionalStudentProfile.isPresent()) {
            StudentProfile studentProfile = optionalStudentProfile.get();

            Optional<User> userOptional = userRepository.findById(studentProfileDTO.getUserId());
            Optional<Department> departmentOptional = departmentRepository.findById(studentProfileDTO.getDepartmentId());

            if (userOptional.isPresent() && departmentOptional.isPresent()) {
                studentProfile.setUser(userOptional.get());
                studentProfile.setPhoto(studentProfileDTO.getPhoto());
                studentProfile.setDepartment(departmentOptional.get());
                // Update other properties as needed

                StudentProfile updatedStudentProfile = studentProfileRepository.save(studentProfile);
                return convertToDTO(updatedStudentProfile);
            }
        }
        return null; // or handle as appropriate
    }

    public void deleteStudentProfile(long id) {
        studentProfileRepository.deleteById(id);
    }

    private StudentProfileDTO convertToDTO(StudentProfile studentProfile) {
        StudentProfileDTO dto = new StudentProfileDTO();
//        dto.setId(studentProfile.getId()); // Assuming DTO has an ID field
        dto.setUserId(studentProfile.getUser().getId());
        dto.setPhoto(studentProfile.getPhoto());
        dto.setDepartmentId(studentProfile.getDepartment().getId());
        // Set other properties as needed
        return dto;
    }
}
