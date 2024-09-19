package com.leucine.cda.controller;

import com.leucine.cda.dto.AdministratorProfileDTO;
import com.leucine.cda.service.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrator-profiles")
public class AdministratorProfileController {

    @Autowired
    private AdministratorProfileService administratorProfileService;

    @GetMapping
    public List<AdministratorProfileDTO> getAllAdministratorProfiles() {
        return administratorProfileService.getAllAdministratorProfiles();
    }

    @GetMapping("/{id}")
    public AdministratorProfileDTO getAdministratorProfileById(@PathVariable long id) {
        return administratorProfileService.getAdministratorProfileById(id);
    }

    @PostMapping
    public AdministratorProfileDTO createAdministratorProfile(@RequestBody AdministratorProfileDTO administratorProfileDTO) {
        return administratorProfileService.createAdministratorProfile(administratorProfileDTO);
    }

    @PutMapping("/{id}")
    public AdministratorProfileDTO updateAdministratorProfile(@PathVariable long id, @RequestBody AdministratorProfileDTO administratorProfileDTO) {
        return administratorProfileService.updateAdministratorProfile(id, administratorProfileDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministratorProfile(@PathVariable long id) {
        administratorProfileService.deleteAdministratorProfile(id);
    }
}
