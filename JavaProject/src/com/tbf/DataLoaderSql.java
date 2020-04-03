package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLoaderSql {

	public static HashMap<String, Person> loadPerson() {

		HashMap<String, Person> codePersonHashMap = new HashMap<>();

		// loading jdbc driver
		try {
			String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// creating connection
		Connection conn = null;
		String url = "jdbc:mysql://cse.unl.edu/byong?useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "byong";
		String password = "iK3anPF4";

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// prepare query
		String getPerson = "select * from Person p " + "join Address a on p.addressId = a.addressId "
				+ "join State s on s.stateId = a.stateId " + "join Country c on c.countryId = a.countryId;";

		String getEmail = "select * from Email where personId = ? ;";

		
		
		PreparedStatement psPerson = null;
		ResultSet rsPerson = null;

		PreparedStatement psEmail = null;
		ResultSet rsEmail = null;

		try {
			psPerson = conn.prepareStatement(getPerson);
			rsPerson = psPerson.executeQuery();
			
			while (rsPerson.next()) {
				List<String> emailAddresses = new ArrayList<>();
				// proccess the record for person
				int personId = rsPerson.getInt("personId");
				String personCode = rsPerson.getString("personCode");
				String brokerType = rsPerson.getString("brokerType");
				String brokerCode = rsPerson.getString("brokerCode");
				String firstName = rsPerson.getString("firstName");
				String lastName = rsPerson.getString("lastName");
				String street = rsPerson.getString("street");
				String city = rsPerson.getString("city");
				String state = rsPerson.getString("state");
				String zip = rsPerson.getString("zip");
				String country = rsPerson.getString("country");
				
				Name name =  new Name(lastName, firstName);
			
				Address address = new Address(street, city, state, zip, country);
				
				psEmail = conn.prepareStatement(getEmail);
				psEmail.setInt(1, personId);

				rsEmail = psEmail.executeQuery();
				
				while (rsEmail.next()) {
					//proccess email for each person
					
					String emailAddress = rsEmail.getString("emailAddress");
					emailAddresses.add(emailAddress);
				}
				
				if(brokerType != null && brokerCode != null) {
					Broker broker = new Broker(brokerType, brokerCode);
					Person person = new Person(personCode, broker, name, address, emailAddresses);
					codePersonHashMap.put(personCode, person);
				} else {
					Person person = new Person(personCode, name, address, emailAddresses);
					codePersonHashMap.put(personCode, person);
				}
				
			}
	
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		// close the connection
		try {
			if (rsPerson != null && !rsPerson.isClosed()) {
				rsPerson.close();
			}
			if (psPerson != null && !psPerson.isClosed()) {
				psPerson.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return codePersonHashMap;

	}

}
