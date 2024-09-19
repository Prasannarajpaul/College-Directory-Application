// src/main/java/com/leucine/cda/service/EnrollmentService.java
package com.leucine.cda.service;

import com.leucine.cda.dto.EnrollmentDTO;
import com.leucine.cda.model.Enrollment;
import com.leucine.cda.model.StudentProfile;
import com.leucine.cda.model.Course;
import com.leucine.cda.repository.EnrollmentRepository;
import com.leucine.cda.repository.StudentProfileRepository;
import com.leucine.cda.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(long id) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(id);
        return optionalEnrollment.map(this::convertToDTO).orElse(null);
    }

    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(enrollmentDTO.getStudentId());
        Optional<Course> courseOptional = courseRepository.findById(enrollmentDTO.getCourseId());

        if (studentProfileOptional.isPresent() && courseOptional.isPresent()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(studentProfileOptional.get());
            enrollment.setCourse(courseOptional.get());

            Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
            return convertToDTO(savedEnrollment);
        }
        return null; // or handle as appropriate
    }

    public EnrollmentDTO updateEnrollment(long id, EnrollmentDTO enrollmentDTO) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(id);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();

            Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(enrollmentDTO.getStudentId());
            Optional<Course> courseOptional = courseRepository.findById(enrollmentDTO.getCourseId());

            if (studentProfileOptional.isPresent() && courseOptional.isPresent()) {
                enrollment.setStudent(studentProfileOptional.get());
                enrollment.setCourse(courseOptional.get());

                Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
                return convertToDTO(updatedEnrollment);
            }
        }
        return null; // or handle as appropriate
    }

    public void deleteEnrollment(long id) {
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId()); // Assuming DTO has an ID field
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setCourseId(enrollment.getCourse().getId());
        return dto;
    }
}
