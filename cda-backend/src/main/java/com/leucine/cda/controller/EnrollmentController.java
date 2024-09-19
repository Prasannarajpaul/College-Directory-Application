package com.leucine.cda.controller;

import com.leucine.cda.dto.EnrollmentDTO;
import com.leucine.cda.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public EnrollmentDTO getEnrollmentById(@PathVariable long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PostMapping
    public EnrollmentDTO createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        return enrollmentService.createEnrollment(enrollmentDTO);
    }

    @PutMapping("/{id}")
    public EnrollmentDTO updateEnrollment(@PathVariable long id, @RequestBody EnrollmentDTO enrollmentDTO) {
        return enrollmentService.updateEnrollment(id, enrollmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable long id) {
        enrollmentService.deleteEnrollment(id);
    }
}
