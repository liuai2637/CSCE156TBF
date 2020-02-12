import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
			int numInfo = 0;
			for (String x : info) {
				numInfo++;
			}

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
			if (numInfo == 5) {
				String[] ema = info[4].split(",");
				for (String x : ema) {
					email.add(x);
				}
			}

			Person person = new Person(personCode, broker, name, address, email);

			peopleArrayList.add(person);

		}

		// TODO: output to xml
	}
}
