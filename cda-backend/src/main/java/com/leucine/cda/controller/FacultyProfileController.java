package com.leucine.cda.controller;

import com.leucine.cda.dto.FacultyProfileDTO;
import com.leucine.cda.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty-profiles")
public class FacultyProfileController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @GetMapping
    public List<FacultyProfileDTO> getAllFacultyProfiles() {
        return facultyProfileService.getAllFacultyProfiles();
    }

    @GetMapping("/{id}")
    public FacultyProfileDTO getFacultyProfileById(@PathVariable long id) {
        return facultyProfileService.getFacultyProfileById(id);
    }

    @PostMapping
    public FacultyProfileDTO createFacultyProfile(@RequestBody FacultyProfileDTO facultyProfileDTO) {
        return facultyProfileService.createFacultyProfile(facultyProfileDTO);
    }

    @PutMapping("/{id}")
    public FacultyProfileDTO updateFacultyProfile(@PathVariable long id, @RequestBody FacultyProfileDTO facultyProfileDTO) {
        return facultyProfileService.updateFacultyProfile(id, facultyProfileDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFacultyProfile(@PathVariable long id) {
        facultyProfileService.deleteFacultyProfile(id);
    }
}
