package com.magic.project.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.project.models.Student;

public interface StudentService {

	void saveStudent(@Valid Student student);

	Student getStudentByEnrollmentId(@Valid String enrollmentId);

	Student patchUpdateStudent(Student student, @Valid Student newStudent);

	void deleteStudent(@Valid String enrollmentId);

	Student getStudentByEnrollmentIdForPatch(String enrollmentId);

	List<Student> getAllStudents();

}
