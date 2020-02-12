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
		String str =  s.nextLine();
		
		System.out.println(n);
		System.out.println(str);
		ArrayList<Person> peopleArrayList = new ArrayList<>();
		
	    s.useDelimiter("\n");
	    
	    
	    
	    for(int i=1; i<=n; i++) {
	    	
	    }
	    //TODO: output to xml
	}
}


