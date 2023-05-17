package com.magic.project.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.magic.project.handler.NoContentFoundException;
import com.magic.project.handler.StudentNotFoundException;
import com.magic.project.models.Address;
import com.magic.project.models.Student;
import com.magic.project.services.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerJUnitTest {
	private Student buildTestingStudent() {
		Student student = new Student();
		Address address = testAddress();
		student.setAddress(address);
		student.setBranch("Computer Science and Engineering");
		student.setDob("01/01/2000");
		student.setEnrollmentId("MAGIC001");
		student.setName("NAME_TEST");
		return student;
	}

	private Address testAddress() {
		Address address = new Address();
		address.setCity("CITY");
		address.setPincode("10009");
		address.setState("STATE");
		return address;
	}

	@InjectMocks
	private StudentController studentController;
	@Mock
	private StudentService studentService;

	@Test
	void test_addStudent() throws Exception {
		Student student = buildTestingStudent();
		doNothing().when(studentService).saveStudent(ArgumentMatchers.any(Student.class));
		ResponseEntity<Student> response = studentController.addStudent(student);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(student);
	}

	@Test
	void test_getStudent() throws StudentNotFoundException {
		Student student = buildTestingStudent();
		when(studentService.getStudentByEnrollmentId(Mockito.anyString())).thenReturn(student);
		ResponseEntity<Student> response = studentController.getStudent("TEST_ID");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(student);
	}

	@Test
	void test_updateStudent() throws NoContentFoundException {
		Student student = buildTestingStudent();
		Student newStudent = new Student();
		newStudent.setName("NEW_TEST_NAME");
		Student updatedStudent = new Student();
		updatedStudent.setEnrollmentId(student.getEnrollmentId());
		updatedStudent.setAddress(student.getAddress());
		updatedStudent.setBranch(student.getBranch());
		updatedStudent.setDob(student.getDob());
		updatedStudent.setName(newStudent.getName());
		when(studentService.getStudentByEnrollmentIdForPatch(Mockito.anyString())).thenReturn(student);
		when(studentService.patchUpdateStudent(student, newStudent)).thenReturn(updatedStudent);
		ResponseEntity<Student> response = studentController.updateStudent("MAGIC001", newStudent);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("MAGIC001", response.getBody().getEnrollmentId());
		assertEquals(newStudent.getName(), response.getBody().getName());
	}
	@Test
	void test_deleteStudent() throws StudentNotFoundException {
		ResponseEntity<String> response = studentController.deleteStudent("MAGIC001");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Record deleted successfully");
	}

}
