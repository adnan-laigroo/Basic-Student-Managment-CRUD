package com.magic.project.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.project.handler.NoContentFoundException;
import com.magic.project.handler.StudentNotFoundException;
import com.magic.project.models.Student;
import com.magic.project.repository.StudentRepository;
import com.magic.project.services.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {
	public static final String NOT_FOUND = "No record found";
	@Autowired
	private StudentRepository studentRepository;

	public void saveStudent(@Valid Student student) {
		studentRepository.save(student);
	}

	public Student getStudentByEnrollmentId(@Valid String enrollmentId) {
		Student student = studentRepository.findById(enrollmentId).orElse(null);
		if (student == null) {
			throw new StudentNotFoundException(NOT_FOUND);

		} else
			return student;
	}

	@Override
	public Student patchUpdateStudent(Student student, @Valid Student newStudent) {
		if (newStudent.getAddress() == null) {
			newStudent.setAddress(student.getAddress());
		}
		if (newStudent.getBranch() == null) {
			newStudent.setBranch(student.getBranch());
		}
		if (newStudent.getDob() == null) {
			newStudent.setDob(student.getDob());
		}
		if (newStudent.getEnrollmentId() == null) {
			newStudent.setEnrollmentId(student.getEnrollmentId());
		} 
		if (newStudent.getName() == null) {
			newStudent.setName(student.getName());
		}
		studentRepository.save(newStudent);
		return newStudent;
	}

	@Override
	public void deleteStudent(@Valid String enrollmentId) {
		Student student = studentRepository.findById(enrollmentId).orElse(null);
		if (student == null) {
			throw new StudentNotFoundException(NOT_FOUND);
		}
		studentRepository.deleteById(enrollmentId);
	}

	@Override
	public Student getStudentByEnrollmentIdForPatch(String enrollmentId) {
		Student student = studentRepository.findById(enrollmentId).orElse(null);
		if (student == null) {
			throw new NoContentFoundException(NOT_FOUND);

		} else
			return student;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		if (students.isEmpty()) {
			throw new StudentNotFoundException(NOT_FOUND);
		}
		return students;
	}
}
