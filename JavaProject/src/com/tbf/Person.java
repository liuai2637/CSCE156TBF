package com.tbf;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Person
 * 14 February 2020
 */
public class Person {
	private String personCode;
	private Broker broker;
	private Name name;
	private Address address;
	private List<String> emailAddresses = new ArrayList<>();
	
	//Constructor that's called if person IS NOT a broker
	public Person(String personCode, Name name,
			Address address, List<String> emailAddresses) {
		this.personCode = personCode;
		this.name = name;
		this.address = address;
		this.emailAddresses = emailAddresses;
	}
	
	//Constructor that's called if person IS a broker
	public Person(String personCode, Broker broker, Name name,
			Address address, List<String> emailAddresses) {
		this.personCode = personCode;
		this.broker = broker;
		this.name = name;
		this.address = address;
		this.emailAddresses = emailAddresses;
	}
	
	//Getters for all fields
	public String getPersonCode() {
		return personCode;
	}

	public Broker getBroker() {
		return broker;
	}

	public Name getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public List<String> getEmailAddresses() {
		return emailAddresses;
	}
}
