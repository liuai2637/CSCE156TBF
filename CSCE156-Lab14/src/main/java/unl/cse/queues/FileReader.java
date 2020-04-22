package unl.cse.queues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

	Queue<String> lines = new Queue<String>();
	
	public void readFile(String fileName) {
		try {
			Scanner s = new Scanner(new File(fileName));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				//TODO: do something with line
				lines.enqueue(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void display() {
		
		int maxCols = 80;
		int maxLines = 40;
		boolean cont = true;
		while(!lines.isEmpty() && cont) {
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<maxLines && !lines.isEmpty(); i++) {
				//TODO: modify the following line of code to get an element from the queue
				String line = lines.dequeue();
				if(line.length() < maxCols) {
					sb.append(line);
					sb.append("\n");
					i++;
				} else {
					String words[] = line.split("\\s+");
					StringBuilder subLine = new StringBuilder();
					for(String w : words) {
						if ((w.length() + subLine.length() + 1) >= maxCols) {
							sb.append(subLine.toString());
							sb.append("\n");
							subLine = new StringBuilder();
						}
						subLine.append(w);
						subLine.append(" ");
					}
					sb.append(subLine);
					sb.append("\n");
				}
			}
			System.out.println(sb.toString());
			
			System.out.println("PRESS <ENTER> TO CONTINUE, Q-ENTER to QUIT...");
			int b = 0;
			try {
				b = System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(b == 81 || b == 113)
				cont = false;
		}
	}
	
	public static void main(String args[]) {
		String fileName = "data/star_wars.txt";
		FileReader fr = new FileReader();
		fr.readFile(fileName);
		fr.display();
		System.out.println("DONE");
	}
}
