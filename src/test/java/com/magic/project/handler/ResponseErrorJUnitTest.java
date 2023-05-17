package com.magic.project.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ResponseErrorJUnitTest {

	@Test
	public void test_GetInfo() {
		String expectedInfo = "Error Information";
		List<String> messages = new ArrayList<>(Arrays.asList("message 1", "message 2"));
		ResponseError error = new ResponseError(expectedInfo, messages);
		String actualInfo = error.getInfo();
		assertEquals(expectedInfo, actualInfo);
	}

	@Test
	public void test_GetMessages() {
		String info = "Error Information";
		List<String> expectedMessages = new ArrayList<>(Arrays.asList("message 1", "message 2"));
		ResponseError error = new ResponseError(info, expectedMessages);
		List<String> actualMessages = error.getMessages();
		assertEquals(expectedMessages, actualMessages);
	}

	@Test
	public void test_SetInfo() {
		String info = "Error Information";
		ResponseError error = new ResponseError();
		error.setInfo(info);
		assertEquals(info, error.getInfo());
	}

	@Test
	public void test_SetMessages() {
		List<String> messages = new ArrayList<>(Arrays.asList("message 1", "message 2"));
		ResponseError error = new ResponseError();
		error.setMessages(messages);
		assertEquals(messages, error.getMessages());
	}
}
