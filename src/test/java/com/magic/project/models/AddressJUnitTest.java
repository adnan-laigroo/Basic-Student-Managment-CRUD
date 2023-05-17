package com.magic.project.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressJUnitTest {

	@Test
	public void test_AddressGettersAndSetters() {
		Address address = new Address();
		address.setCity("TEST_CITY");
		address.setState("TEST_STATE");
		address.setPincode("TEST_PIN");
		assertEquals("TEST_CITY", address.getCity());
		assertEquals("TEST_STATE", address.getState());
		assertEquals("TEST_PIN", address.getPincode());
	}

}
