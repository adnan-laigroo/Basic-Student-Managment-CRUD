package com.magic.project.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
public class ExceptionAndValidationHandlerJUnitTest {

	@Mock
	private BindingResult bindingResult;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_handleMethodArgumentNotValid() throws Exception {
		List<ObjectError> errors = new ArrayList<>();
		errors.add(new ObjectError("Student", "Name is required."));
		when(bindingResult.getAllErrors()).thenReturn(errors);

		ExceptionAndValidationHandler handler = new ExceptionAndValidationHandler();
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		WebRequest request = null;
		ResponseEntity<Object> responseEntity = handler.handleMethodArgumentNotValid(ex, headers, status, request);

		String expectedResponseBody = "\"message\" : \"Invalid request payload\"";
		String actualResponseBody = (String) responseEntity.getBody();
		assertEquals(expectedResponseBody, actualResponseBody);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void test_handleExceptionStudentNotFoundException() throws Exception {
		ExceptionAndValidationHandler handler = new ExceptionAndValidationHandler();
		StudentNotFoundException ex = new StudentNotFoundException("No record found");
		ResponseEntity<Object> responseEntity = handler.handleExceptionNotFoundException(ex);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void test_handleExceptionNoContentFoundException() throws Exception {
		ExceptionAndValidationHandler handler = new ExceptionAndValidationHandler();
		NoContentFoundException ex = new NoContentFoundException("No record found.");
		ResponseEntity<Object> responseEntity = handler.handleExceptionNotFoundException(ex);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
}
