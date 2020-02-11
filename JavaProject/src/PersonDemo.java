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
	    
	    while(s.hasNext()){
	        String acc = s.nextLine();
	        String[] info = acc.split(";");
	        String[] address = info[5].split(",");
	        Address a = new Address(address[0], address[1], address[2], address[3], address[4]);
	        //TODO:email list
	        Person p = new Person(info[0], info[1].charAt(0), info[2], info[3], info[4], a, null);
	        peopleArrayList.add(p);
	    }    
	    //TODO: output to xml
	}

}
