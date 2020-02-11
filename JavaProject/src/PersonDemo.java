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
		
	    s.useDelimiter("\n");
	    
	    while(scan.hasNext()){
	        String acc = s.nextLine();
	        String[] info = acc.split(";");
	        Person p = new Person(info[0], info[1], info[2], info[3], info[4]);
	        peopleArrayList.add(p);
	    }    
	    //TODO: output to xml
	}

}
