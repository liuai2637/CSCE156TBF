import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		
		ArrayList<Person> peopleArrayList = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			//TODO: split line by "," and ";"
		}

	}

}
