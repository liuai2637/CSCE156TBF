package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Dataloader class for reading through file and output information
 * according to person type, asset type or portfolio type
 * 28 February 2020
 */

public class DataLoader {
	
	//Data loader for persons
	public static HashMap<String, Person> peopleReadIn(String fileName) {
		
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Find number of Persons by reading in the first line
		int n = Integer.parseInt(s.nextLine());
		
		//Maps each personCode to the person
		HashMap<String, Person> codePersonHashMap = new HashMap<>();
		
		// Iterate through each entry
		for (int i = 1; i <= n; i++) {
			String account = s.nextLine();		//String to split
			String[] info = account.split(";");	//Info array contains all the fields
			
			//personCode is the unique code every person has (Maybe a natural Id for SQL database?)
			String personCode = info[0];
			
			//Every person has a broker object, but if the person doesn't have one, it is set as empty
			Broker broker = new Broker("", "");
			if (info[1].length() != 0) {
				String[] bro = info[1].split(",");
				broker = new Broker(bro[0], bro[1]);	//Broker object is composed of Type and Code
			}
			
			// split last name and first name and put into name class
			String[] nam = info[2].split(",");
			Name name = new Name(nam[0].trim(), nam[1].trim());	//trim() removes any whitespace from firstName and lastName
			
			// split each element of address and put into address class
			String[] add = info[3].split(",");
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);
			
			// create ArrayList for the list of emails (type String) (can have none)
			List<String> email = new ArrayList<>();
			if (info.length == 5) { //If != 5, means the email field is empty
				String[] ema = info[4].split(",");
				for (String x : ema) {
					email.add(x);
				}
			}
			
			// Call proper Person constructor depending on whether or not the person is a broker
			//	And add constructed person object to codePersonHashMap which maps each unique personCode to a person
			if (!broker.isBroker()) {
				Person person = new Person(personCode, name, address, email);
				codePersonHashMap.put(personCode, person);
			} else {
				Person person = new Person(personCode, broker, name, address, email);
				codePersonHashMap.put(personCode, person);
			}
		}
		
		return codePersonHashMap; //HashMap which maps every personCode to it person in the whole system
	}
	//End of personReadIn
	
	//Data Loader for assets
	public static HashMap<String, Asset> assetReadIn(String fileName){
		
		Scanner sAsset = null;
		try {
			sAsset = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Finds the total number of assets in the system
		int nAsset = Integer.parseInt(sAsset.nextLine());

		// List to store the assets
		HashMap<String, Asset> codeAssetHashMap = new HashMap<>();

		// Iterate through each entry in Assets.dat
		for (int i = 0; i < nAsset; i++) {

			String entry = sAsset.nextLine();

			// Split entry into different info fields and record length into numInfo
			String[] infoAsset = entry.split(";");
			int numInfoAsset = infoAsset.length;

			// Depending on the length of the numInfoAsset, construct the corresponding type of asset and add to asset list
			if (numInfoAsset == 4) {
				Asset asset = new DepositAccount(infoAsset[0], infoAsset[2], Double.parseDouble(infoAsset[3]));
				codeAssetHashMap.put(infoAsset[0], asset);
			} else if (numInfoAsset == 8) {
				Asset asset = new Stock(infoAsset[0], infoAsset[2], Double.parseDouble(infoAsset[3]), Double.parseDouble(infoAsset[4]),
						Double.parseDouble(infoAsset[5]), infoAsset[6], Double.parseDouble(infoAsset[7]));
				codeAssetHashMap.put(infoAsset[0],asset);
			} else {
				Asset asset = new PrivateInvestment(infoAsset[0], infoAsset[2],
						Double.parseDouble(infoAsset[3]), Double.parseDouble(infoAsset[4]), Double.parseDouble(infoAsset[5]),
						Double.parseDouble(infoAsset[6]));
				codeAssetHashMap.put(infoAsset[0],asset);
			}
		}
		
		return codeAssetHashMap;	//HashMap which maps each assetCode to its asset in the whole system
	}
	//End of assetReadIn
	
	//Data loader for portfolio
	public static ArrayList<Portfolio> portReadIn(String fileName) {
		
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Finds number of portfolios from the first line
		int n = Integer.parseInt(s.nextLine());
		
		//Iterate through Portfolio.dat and splits each line into different fields and constructs an asset array
		ArrayList<Portfolio> portfolioArrayList = new ArrayList<>();
		HashMap<String, Person> peopleMap = peopleReadIn("data/Persons.dat"); //HashMap which maps each personCode to their person object
		for(int i=0; i<n; i++) {
			
			String data = s.nextLine();
			String[] info = data.split(";", -1);	//Info array contains all fields
			
			String portfolioCode = info[0];	//not null
			
			String ownerCode = info[1];		//not null
			Person owner = peopleMap.get(ownerCode);	//Every owner is a person and can be found in peopleMap
			
			String managerCode = info[2];	//not null
			Person manager = peopleMap.get(managerCode);//Every manager is a person and can be found in peopleMap
			
			String beneficiaryCode = info[3];// can be null
			Person beneficiary = peopleMap.get(beneficiaryCode);//Every beneficiary is a person and can be found in peopleMap
			
			//Reads in the assets EACH SINGLE PORTFOLIO has (Can have none), along with their input value
			String[] assetArr = info[4].split(",", -1);
			HashMap<String, Asset> assetMap = assetReadIn("data/Assets.dat"); // assetMap contains all assets in the system
			if(!assetArr.equals(null)) {	//If the current portfolio HAS assets
				
				//assetList contains all the assets EACH SINGLE PORTFOLIO has
				List<Asset> assetList = new ArrayList<Asset>();
				for(String x: assetArr) {
					String[] xAsset= x.split(":", -1);
					Asset a = assetMap.get(xAsset[0]);	// a is one asset in a portfolio (Deposit Account, Private Investment, or Stock)
					if(xAsset.length == 2) {
						a.setNumAsset(Double.parseDouble(xAsset[1]));	// Sets the input value for the asset (Polymorphism)
						assetList.add(a);	//Add all assets in this portfolio to assetList
					}
					
				}
				
				// Construct a portfolio object and add it to portfolioArrayList
				Portfolio portfolio = new Portfolio(portfolioCode, owner, manager, beneficiary, assetList);
				portfolioArrayList.add(portfolio);	
			} else {	//The current portfolio HAS NO assets
				Portfolio portfolio = new Portfolio(portfolioCode, owner, manager, beneficiary);
				portfolioArrayList.add(portfolio);	
			}			
		}
		
		return portfolioArrayList;	//ArrayList which holds all the portfolios in the entire system
	}
}
