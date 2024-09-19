package com.leucine.cda.controller;

import com.leucine.cda.dto.StudentProfileDTO;
import com.leucine.cda.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-profiles")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public List<StudentProfileDTO> getAllStudentProfiles() {
        return studentProfileService.getAllStudentProfiles();
    }

    @GetMapping("/{id}")
    public StudentProfileDTO getStudentProfileById(@PathVariable long id) {
        return studentProfileService.getStudentProfileById(id);
    }

    @PostMapping
    public StudentProfileDTO createStudentProfile(@RequestBody StudentProfileDTO studentProfileDTO) {
        return studentProfileService.createStudentProfile(studentProfileDTO);
    }

    @PutMapping("/{id}")
    public StudentProfileDTO updateStudentProfile(@PathVariable long id, @RequestBody StudentProfileDTO studentProfileDTO) {
        return studentProfileService.updateStudentProfile(id, studentProfileDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentProfile(@PathVariable long id) {
        studentProfileService.deleteStudentProfile(id);
    }
}
