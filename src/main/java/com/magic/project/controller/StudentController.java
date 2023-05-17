package com.magic.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magic.project.models.Student;
import com.magic.project.services.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;

	@PostMapping
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {

		studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}

	@GetMapping
	public ResponseEntity<Student> getStudent(@Valid @RequestParam String enrollmentId) {

		Student student = studentService.getStudentByEnrollmentId(enrollmentId);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	@GetMapping("list")
	public ResponseEntity<List<Student>> getAllStudents() {

		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.status(HttpStatus.OK).body(students);
	}

	@PatchMapping("{enrollmentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable String enrollmentId, @RequestBody Student newStudent) {
		Student student = studentService.getStudentByEnrollmentIdForPatch(enrollmentId);
		Student updatedStudent = studentService.patchUpdateStudent(student, newStudent);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("{enrollmentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable String enrollmentId) {
		studentService.deleteStudent(enrollmentId);
		return ResponseEntity.status(HttpStatus.OK).body("Record deleted successfully");
	}
}
