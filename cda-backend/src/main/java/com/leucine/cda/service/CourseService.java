// src/main/java/com/leucine/cda/service/CourseService.java
package com.leucine.cda.service;

import com.leucine.cda.dto.CourseDTO;
import com.leucine.cda.model.Course;
import com.leucine.cda.model.FacultyProfile;
import com.leucine.cda.model.Department;
import com.leucine.cda.repository.CourseRepository;
import com.leucine.cda.repository.FacultyProfileRepository;
import com.leucine.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.map(this::convertToDTO).orElse(null);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();

        // Set Department entity
        Department department = departmentRepository.findById(courseDTO.getDepartmentId()).orElse(null);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        course.setDepartment(department);

        // Set FacultyProfile entity
        FacultyProfile facultyProfile = facultyProfileRepository.findById(courseDTO.getFacultyId()).orElse(null);
        if (facultyProfile == null) {
            throw new RuntimeException("FacultyProfile not found");
        }
        course.setFaculty(facultyProfile);

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());

        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public CourseDTO updateCourse(long id, CourseDTO courseDTO) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            // Update Department entity
            Department department = departmentRepository.findById(courseDTO.getDepartmentId()).orElse(null);
            if (department == null) {
                throw new RuntimeException("Department not found");
            }
            course.setDepartment(department);

            // Update FacultyProfile entity
            FacultyProfile facultyProfile = facultyProfileRepository.findById(courseDTO.getFacultyId()).orElse(null);
            if (facultyProfile == null) {
                throw new RuntimeException("FacultyProfile not found");
            }
            course.setFaculty(facultyProfile);

            course.setTitle(courseDTO.getTitle());
            course.setDescription(courseDTO.getDescription());

            Course updatedCourse = courseRepository.save(course);
            return convertToDTO(updatedCourse);
        }
        return null;
    }

    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setDepartmentId(course.getDepartment().getId()); // Assuming Department ID is needed
        dto.setFacultyId(course.getFaculty().getId()); // Assuming Faculty ID is needed
        return dto;
    }
}
