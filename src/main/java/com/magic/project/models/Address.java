package com.magic.project.models;

import javax.validation.constraints.NotNull;

public class Address {

	@NotNull(message = "City is mandatory")
	private String city;

	@NotNull(message = "State is mandatory")
	private String state;

	@NotNull(message = "Pincode is mandatory")
	private String pincode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
