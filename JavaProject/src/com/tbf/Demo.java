package com.tbf;

import java.util.HashMap;
import java.util.Map;


public class Demo {

	public static void main(String[] args) {
		
		HashMap<String, Person> codePersonHashMap = DataLoaderSql.loadPerson();
		
		for (Map.Entry<String, Person> entry : codePersonHashMap.entrySet()) {
			String str = entry.getValue().getName().getFirstName();
		    System.out.println(str);
		}
		
		
	}

}

