package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Name
 * 14 February 2020
 */
public class Name {
	private String lastName;
	private String firstName;

	//Constructor
	public Name(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	//Getters for all fields
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}
}
