# Data-Base Schema

This document outlines the database schema for the system, detailing the entities and their relationships.

## Entities

### 1. User

**Fields:**
- `id`: Unique identifier for each user.
- `username`: Login name for the user.
- `password`: Encrypted password for the user.
- `role`: Role of the user, defined using an enum (Student, FacultyMember, Administrator).
- `name`: Full name of the user.
- `email`: Contact email of the user.
- `phone`: Contact phone number of the user.

**Explanation:** This entity stores the basic login credentials and contact information for all users in the system. The `role` field, using an enum, helps in differentiating between students, faculty members, and administrators.

---

### 2. StudentProfile

**Fields:**
- `user_id`: Foreign key linking to the User entity.
- `photo`: URL or path to the student's profile photo.
- `department_id`: Foreign key linking to the Department entity.
- `year`: Year of study (e.g., Freshman, Sophomore, etc.).

**Explanation:** This entity extends the User entity to include additional information specific to students, such as their academic details, department, and year of study.

---

### 3. FacultyProfile

**Fields:**
- `user_id`: Foreign key linking to the User entity.
- `photo`: URL or path to the faculty member's profile photo.
- `department_id`: Foreign key linking to the Department entity.
- `office_hours`: Office hours for the faculty member.

**Explanation:** This entity extends the User entity to include additional information specific to faculty members, such as their department and office hours.

---

### 4. AdministratorProfile

**Fields:**
- `user_id`: Foreign key linking to the User entity.
- `photo`: URL or path to the administrator's profile photo.
- `department_id`: Foreign key linking to the Department entity.

**Explanation:** This entity extends the User entity to include additional information specific to administrators, such as their department.

---

### 5. Course

**Fields:**
- `id`: Unique identifier for each course.
- `title`: Title of the course.
- `description`: Description of the course content.
- `department_id`: Foreign key linking to the Department entity.
- `faculty_id`: Foreign key linking to the FacultyProfile entity.

**Explanation:** This entity stores information about the courses offered by the college, including the title, description, department, and the faculty member responsible for the course.

---

### 6. Enrollment

**Fields:**
- `id`: Unique identifier for each enrollment record.
- `student_id`: Foreign key linking to the StudentProfile entity.
- `course_id`: Foreign key linking to the Course entity.

**Explanation:** This entity represents the many-to-many relationship between students and courses, indicating which students are enrolled in which courses.

---

### 7. Department

**Fields:**
- `id`: Unique identifier for each department.
- `name`: Name of the department.
- `description`: Description of the department.

**Explanation:** This entity stores information about the various departments within the college. It is referenced by the StudentProfile, FacultyProfile, AdministratorProfile, and Course entities to ensure proper association of users and courses with their respective departments.

---

## Relationships

- **User to StudentProfile/FacultyProfile/AdministratorProfile:**  
  One-to-One relationship where each user has a corresponding profile in either the StudentProfile, FacultyProfile, or AdministratorProfile entities.

- **FacultyProfile to Course:**  
  One-to-Many relationship where one faculty member can teach multiple courses.

- **Course to StudentProfile (through Enrollment):**  
  Many-to-Many relationship facilitated by the Enrollment entity, where students can enroll in multiple courses, and each course can have multiple students.

- **Department to StudentProfile/FacultyProfile/AdministratorProfile/Course:**  
  One-to-Many relationship where each department can have multiple students, faculty members, administrators, and courses associated with it.
