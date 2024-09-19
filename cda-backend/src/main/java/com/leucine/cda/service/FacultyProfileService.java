// src/main/java/com/leucine/cda/service/FacultyProfileService.java
package com.leucine.cda.service;

import com.leucine.cda.dto.FacultyProfileDTO;
import com.leucine.cda.model.FacultyProfile;
import com.leucine.cda.model.User;
import com.leucine.cda.model.Department;
import com.leucine.cda.repository.FacultyProfileRepository;
import com.leucine.cda.repository.UserRepository;
import com.leucine.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<FacultyProfileDTO> getAllFacultyProfiles() {
        return facultyProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FacultyProfileDTO getFacultyProfileById(long id) {
        Optional<FacultyProfile> optionalFacultyProfile = facultyProfileRepository.findById(id);
        return optionalFacultyProfile.map(this::convertToDTO).orElse(null);
    }

    public FacultyProfileDTO createFacultyProfile(FacultyProfileDTO facultyProfileDTO) {
        Optional<User> userOptional = userRepository.findById(facultyProfileDTO.getUserId());
        Optional<Department> departmentOptional = departmentRepository.findById(facultyProfileDTO.getDepartmentId());

        if (userOptional.isPresent() && departmentOptional.isPresent()) {
            FacultyProfile facultyProfile = new FacultyProfile();
            facultyProfile.setUser(userOptional.get());
            facultyProfile.setPhoto(facultyProfileDTO.getPhoto());
            facultyProfile.setDepartment(departmentOptional.get());
            // Set other properties as needed

            FacultyProfile savedFacultyProfile = facultyProfileRepository.save(facultyProfile);
            return convertToDTO(savedFacultyProfile);
        }
        return null; // or handle as appropriate
    }

    public FacultyProfileDTO updateFacultyProfile(long id, FacultyProfileDTO facultyProfileDTO) {
        Optional<FacultyProfile> optionalFacultyProfile = facultyProfileRepository.findById(id);
        if (optionalFacultyProfile.isPresent()) {
            FacultyProfile facultyProfile = optionalFacultyProfile.get();

            Optional<User> userOptional = userRepository.findById(facultyProfileDTO.getUserId());
            Optional<Department> departmentOptional = departmentRepository.findById(facultyProfileDTO.getDepartmentId());

            if (userOptional.isPresent() && departmentOptional.isPresent()) {
                facultyProfile.setUser(userOptional.get());
                facultyProfile.setPhoto(facultyProfileDTO.getPhoto());
                facultyProfile.setDepartment(departmentOptional.get());
                // Update other properties as needed

                FacultyProfile updatedFacultyProfile = facultyProfileRepository.save(facultyProfile);
                return convertToDTO(updatedFacultyProfile);
            }
        }
        return null; // or handle as appropriate
    }

    public void deleteFacultyProfile(long id) {
        facultyProfileRepository.deleteById(id);
    }

    private FacultyProfileDTO convertToDTO(FacultyProfile facultyProfile) {
        FacultyProfileDTO dto = new FacultyProfileDTO();
//        dto.setId(facultyProfile.getId()); // Assuming DTO has an ID field
        dto.setUserId(facultyProfile.getUser().getId());
        dto.setPhoto(facultyProfile.getPhoto());
        dto.setDepartmentId(facultyProfile.getDepartment().getId());
        // Set other properties as needed
        return dto;
    }
}
