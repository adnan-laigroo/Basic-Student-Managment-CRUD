package com.magic.project.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Student")
public class Student {
	@Id
	@Pattern(regexp = "^MAGIC.*")
	@NotNull
	private String enrollmentId;

	@Embedded
	@NotNull
	private Address address;

	@NotNull(message = "Name is mandatory")
	@Pattern(regexp = "[A-Za-z' ']*", message = "Only alphabets are allowed")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@NotNull(message = "Date of birth is mandatory")
	private String dob;

	@Pattern(regexp = "^(Computer Science and Engineering|Electrical Engineering|Mechanical Engineering)$")
	@NotNull(message = "Branch  is mandatory")
	private String branch;

	public String getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
