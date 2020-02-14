package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Address
 * 14 February 2020
 */
public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	//Constructor
	public Address(String street, String city, String state, String zip, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}
	
	//Getters for all fields
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	public String getCountry() {
		return country;
	}
	
}
