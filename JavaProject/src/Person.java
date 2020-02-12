import java.util.ArrayList;
import java.util.List;

public class Person {
	private String personCode;
	private Broker broker;
	private Name name;
	private Address address;
	private List<String> emailAddresses = new ArrayList<>();
	
	public Person(String personCode, Broker broker, Name name,
			Address address, List<String> emailAddresses) {
		this.personCode = personCode;
		this.broker = broker;
		this.name = name;
		this.address = address;
		this.emailAddresses = emailAddresses;
	}
	
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
