package unl.cse.stacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A basic JSON validator. This validator only checks that the JSON 
 * file is well-balanced: that all opening brackets have a corresponding 
 * closing bracket and that they follow legal nesting rules.
 * 
 */
public class JsonValidator {

	public static boolean isValidJSON(String jsonString) {
		// TODO: implement this function
		// TODO: implement this function
		boolean result = true;
		Stack<Character> stack = new Stack<>();
		char current, previous;
		for (int i = 0; i < jsonString.length(); i++) {
			current = jsonString.charAt(i);
			if (current == '[' || current == '{') {
				stack.push(current);
			} else if (current == ']' || current == '}') {
				if (stack.isEmpty()) {
					result = false;
				} else {
					previous = stack.pop();
					if ((current != ']' && previous != '[') || (current != '}' && previous != '{')) {
						result = false;
					}
				}
			}
		}
		if (!stack.isEmpty()) {
			result = false;
		}
		return result;
	}

	public static String getFileContents(String fileName) {
		StringBuilder sb = new StringBuilder();
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		while (s.hasNextLine()) {
			sb.append(s.nextLine());
		}
		s.close();
		return sb.toString();
	}

	public static void main(String[] args) {
		String jsonFileName = "data/data001.json";
		String jsonString = getFileContents(jsonFileName);
		System.out.println(isValidJSON(jsonString) ? "Valid" : "Invalid");
	}
}
