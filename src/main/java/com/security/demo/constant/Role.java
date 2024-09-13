package com.security.demo.constant;

public enum Role {

	ADMIN("ADMIN"), EMPLOYEE("EMPLOYEE");

	private String value;

	private Role(String value) {

		this.value = value;
	}

}
