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
		
		ArrayList<Person> peopleArrayList = new ArrayList<>();
		
	    s.useDelimiter("\n");
	    
	    while(s.hasNext()){
	        String acc = s.nextLine();
	        String[] info = acc.split(";", 5);
	        String[] name = info[2].split(",");
	        String[] address = info[3].split(",", 4);
	        //TODO: check for country
	        Address a = new Address(address[0], address[1], address[2], address[3], null);
	        List<String> emails = Arrays.asList(info[4].split(","));
	        // check if is broker
	        if(!info[1].isEmpty()) {
	        	Person p = new Person(info[0], info[1].charAt(0), info[1].substring(2), name[1], name[0], a, emails);
	        	peopleArrayList.add(p);
	        } else {
	        	Person p = new Person(info[0], 'n', "n", name[1], name[0], a, emails);
	        	peopleArrayList.add(p);
	        }
	        
			/*
			 * for(int i = 0; i < peopleArrayList.size(); i++) {
			 * System.out.println(peopleArrayList.get(i).getPersonCode()); }
			 */
	    }    
	    //TODO: output to xml
	}

}
