package com.magic.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.magic.project.handler.NoContentFoundException;
import com.magic.project.handler.StudentNotFoundException;
import com.magic.project.models.Address;
import com.magic.project.models.Student;

import com.magic.project.repository.StudentRepository;
import com.magic.project.services.implementation.StudentServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class StudentServiceJUnitTest {
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
	private StudentServiceImplementation studentService;
	@Mock
	private StudentRepository studentRepository;

	@Test
	void test_getStudentByEnrollmentId() {
		Student student = buildTestingStudent();
		when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.of(student));
		Student returnedStudent = studentService.getStudentByEnrollmentId("MAGIC001");
		assertEquals(student.getEnrollmentId(), returnedStudent.getEnrollmentId());
		verify(studentRepository).findById("MAGIC001");
		when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		Throwable exception = assertThrows(StudentNotFoundException.class, () -> {
			studentService.getStudentByEnrollmentId("TEST_ID");
		});
		assertEquals("No record found", exception.getMessage());
	}
	@Test
	void test_getStudentByEnrollmentIdForPatch() {
		Student student = buildTestingStudent();
		when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.of(student));
		Student returnedStudent = studentService.getStudentByEnrollmentIdForPatch("MAGIC001");
		assertEquals(student.getEnrollmentId(), returnedStudent.getEnrollmentId());
		verify(studentRepository).findById("MAGIC001");
		when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		Throwable exception = assertThrows(NoContentFoundException.class, () -> {
			studentService.getStudentByEnrollmentIdForPatch("TEST_ID");
		});
		assertEquals("No record found", exception.getMessage());
	}

	@Test
	void test_saveStudent() {
		Student student = buildTestingStudent();
		studentService.saveStudent(student);
		verify(studentRepository).save(student);
	}

	@Test
	void test_PatchupdateStudent() {
		Student student = buildTestingStudent();
		Student newStudent = new Student();
		Address address = testAddress();
		newStudent.setAddress(address);
		newStudent.setBranch("Computer Science and Engineering");
		newStudent.setDob("01/01/2000");
		newStudent.setEnrollmentId("MAGIC001");
		newStudent.setName("NAME_TEST");
		when(studentRepository.save(newStudent)).thenReturn(newStudent);
		Student result = studentService.patchUpdateStudent(student, newStudent);
		assertEquals(newStudent, result);
		verify(studentRepository).save(newStudent);
	}

	@Test
	public void test_PatchUpdateStudentWithNullValues() {
		// Given
		Student existingStudent = new Student();
		existingStudent.setEnrollmentId("MAGIC001");
		existingStudent.setName("NAME_TEST");
		Address address = testAddress();
		existingStudent.setAddress(address);
		existingStudent.setBranch("Computer Science and Engineering");
		existingStudent.setDob("01/01/2000");
		Student updatedStudent = new Student();
		Student result = studentService.patchUpdateStudent(existingStudent, updatedStudent);
		assertEquals("MAGIC001", result.getEnrollmentId());
		assertEquals("NAME_TEST", result.getName());
		assertEquals(address, result.getAddress());
		assertEquals("Computer Science and Engineering", result.getBranch());
		assertEquals("01/01/2000", result.getDob());
		verify(studentRepository).save(result);
	}

	@Test
	void test_deleteDoctor() {
		Student student = buildTestingStudent();
		when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.of(student));
		studentService.deleteStudent(student.getEnrollmentId());
		verify(studentRepository).deleteById(student.getEnrollmentId());
		when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		Throwable exception = assertThrows(StudentNotFoundException.class, () -> {
			studentService.deleteStudent("TEST_ID");
		});
		assertEquals("No record found", exception.getMessage());
	}

}
