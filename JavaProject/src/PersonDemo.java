import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class PersonDemo {

	public static void main(String[] args) {

		String fileName = "data/Persons.dat";
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = Integer.parseInt(s.nextLine());

		System.out.println(n);
		ArrayList<Person> peopleArrayList = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			String account = s.nextLine();

			String[] info = account.split(";");

			String personCode = info[0];

			Broker broker = new Broker("", "");
			if (info[1].length() != 0) {
				String[] bro = info[1].split(",");
				broker = new Broker(bro[0], bro[1]);
			}

			String[] nam = info[2].split(",");
			Name name = new Name(nam[0], nam[1]);

			String[] add = info[3].split(",");
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);

			List<String> email = new ArrayList<>();
			if (info.length == 5) {
				String[] ema = info[4].split(",");
				for (String x : ema) {
					email.add(x);
				}
			}
			if (!broker.isBroker()) {
				Person person = new Person(personCode, name, address, email);
				peopleArrayList.add(person);
			} else {
				Person person = new Person(personCode, broker, name, address, email);
				peopleArrayList.add(person);
			}
		}

		// I also download the JSON library since I cannot download xml
		// library also and it is easy to convert from Json to xml

		Gson g = new GsonBuilder().setPrettyPrinting().create();
		XStream xstream = new XStream(new StaxDriver());

		try {
			File outputJson = new File("data/Persons.json");
			File outputXml = new File("data/PersonsTest.xml");
			PrintWriter pwJson = new PrintWriter(outputJson);
			PrintWriter pwXml = new PrintWriter(outputXml);
			pwJson.println(g.toJson(peopleArrayList));
			pwXml.println(xstream.toXML(peopleArrayList));

			pwJson.close();
			pwXml.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
