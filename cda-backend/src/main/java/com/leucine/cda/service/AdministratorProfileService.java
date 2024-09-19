// src/main/java/com/leucine/cda/service/AdministratorProfileService.java
package com.leucine.cda.service;

import com.leucine.cda.dto.AdministratorProfileDTO;
import com.leucine.cda.model.AdministratorProfile;
import com.leucine.cda.model.User;
import com.leucine.cda.model.Department;
import com.leucine.cda.repository.AdministratorProfileRepository;
import com.leucine.cda.repository.UserRepository;
import com.leucine.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministratorProfileService {

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<AdministratorProfileDTO> getAllAdministratorProfiles() {
        return administratorProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdministratorProfileDTO getAdministratorProfileById(long id) {
        Optional<AdministratorProfile> optionalProfile = administratorProfileRepository.findById(id);
        return optionalProfile.map(this::convertToDTO).orElse(null);
    }

    public AdministratorProfileDTO createAdministratorProfile(AdministratorProfileDTO administratorProfileDTO) {
        AdministratorProfile administratorProfile = new AdministratorProfile();

        // Set User entity
        User user = userRepository.findById(administratorProfileDTO.getUserId()).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        administratorProfile.setUser(user);

        // Set Department entity
        Department department = departmentRepository.findById(administratorProfileDTO.getDepartmentId()).orElse(null);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        administratorProfile.setDepartment(department);

        administratorProfile.setPhoto(administratorProfileDTO.getPhoto());

        AdministratorProfile savedProfile = administratorProfileRepository.save(administratorProfile);
        return convertToDTO(savedProfile);
    }

    public AdministratorProfileDTO updateAdministratorProfile(long id, AdministratorProfileDTO administratorProfileDTO) {
        Optional<AdministratorProfile> optionalProfile = administratorProfileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            AdministratorProfile administratorProfile = optionalProfile.get();

            // Update User entity
            User user = userRepository.findById(administratorProfileDTO.getUserId()).orElse(null);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            administratorProfile.setUser(user);

            // Update Department entity
            Department department = departmentRepository.findById(administratorProfileDTO.getDepartmentId()).orElse(null);
            if (department == null) {
                throw new RuntimeException("Department not found");
            }
            administratorProfile.setDepartment(department);

            administratorProfile.setPhoto(administratorProfileDTO.getPhoto());

            AdministratorProfile updatedProfile = administratorProfileRepository.save(administratorProfile);
            return convertToDTO(updatedProfile);
        }
        return null;
    }

    public void deleteAdministratorProfile(long id) {
        administratorProfileRepository.deleteById(id);
    }

    private AdministratorProfileDTO convertToDTO(AdministratorProfile administratorProfile) {
        AdministratorProfileDTO dto = new AdministratorProfileDTO();
//        dto.setId(administratorProfile.getId());
        dto.setPhoto(administratorProfile.getPhoto());
        dto.setDepartmentId(administratorProfile.getDepartment().getId()); // Assuming Department ID is needed
        dto.setUserId(administratorProfile.getUser().getId()); // Assuming User ID is needed
        return dto;
    }
}
