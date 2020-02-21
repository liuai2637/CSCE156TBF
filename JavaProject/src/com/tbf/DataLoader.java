package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {
	public static ArrayList<Person> dataReadIn(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = Integer.parseInt(s.nextLine());
		ArrayList<Person> peopleArrayList = new ArrayList<>();
		// Iterate through each entry
		for (int i = 1; i <= n; i++) {
			String account = s.nextLine();
			String[] info = account.split(";");
			String personCode = info[0];
			Broker broker = new Broker("", "");
			if (info[1].length() != 0) {
				String[] bro = info[1].split(",");
				broker = new Broker(bro[0], bro[1]);
			}
			// split last name and first name by "," and put into name class
			String[] nam = info[2].split(",");
			Name name = new Name(nam[0], nam[1]);
			// split each element of address and put into address class
			String[] add = info[3].split(",");
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);
			// create ArrayList for list of email that is separated by ","
			List<String> email = new ArrayList<>();
			if (info.length == 5) {
				String[] ema = info[4].split(",");
				for (String x : ema) {
					email.add(x);
				}
			}
			// Input different formatted person object depend on whether the person is a
			// broker or not
			if (!broker.isBroker()) {
				Person person = new Person(personCode, name, address, email);
				peopleArrayList.add(person);
			} else {
				Person person = new Person(personCode, broker, name, address, email);
				peopleArrayList.add(person);
			}
		}
		return peopleArrayList;
	}
}
