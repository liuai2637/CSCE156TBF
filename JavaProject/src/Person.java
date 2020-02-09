import java.util.ArrayList;
import java.util.List;

public class Person {
	private String personCode;
	private char brokerType;
	private String brokerSec;
	private String firstName;
	private String lastName;
	private Address address;
	List<String> emailAddresses = new ArrayList<>();
	public String getPersonCode() {
		return personCode;
	}
	public char getBrokerType() {
		return brokerType;
	}
	public String getBrokerSec() {
		return brokerSec;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Address getAddress() {
		return address;
	}
	public List<String> getEmailAddresses() {
		return emailAddresses;
	}
	
	public Person(String personCode, char brokerType, String brokerSec, String firstName, String lastName,
			Address address, List<String> emailAddresses) {
		this.personCode = personCode;
		this.brokerType = brokerType;
		this.brokerSec = brokerSec;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailAddresses = emailAddresses;
	}
	
}
