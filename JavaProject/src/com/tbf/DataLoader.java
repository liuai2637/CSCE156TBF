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
	public static HashMap<String, Person> peopleReadIn(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = Integer.parseInt(s.nextLine());
		HashMap<String, Person> codePersonHashMap = new HashMap<>();
		// Iterate through each entry
		for (int i = 1; i <= n; i++) {
			String account = s.nextLine();
			String[] info = account.split(";");
			String personCode = info[0];
			Broker broker = new Broker("", "");
			if (info[1].length() != 0) {
				String[] bro = info[1].split(",");
				broker = new Broker(bro[0], bro[1]);
			}
			// split last name and first name by "," and put into name class
			String[] nam = info[2].split(",");
			Name name = new Name(nam[0].trim(), nam[1].trim());
			// split each element of address and put into address class
			String[] add = info[3].split(",");
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);
			// create ArrayList for list of email that is separated by ","
			List<String> email = new ArrayList<>();
			if (info.length == 5) {
				String[] ema = info[4].split(",");
				for (String x : ema) {
					email.add(x);
				}
			}
			// Input different formatted person object depend on whether the person is a
			// broker or not
			if (!broker.isBroker()) {
				Person person = new Person(personCode, name, address, email);
				codePersonHashMap.put(personCode, person);
			} else {
				Person person = new Person(personCode, broker, name, address, email);
				codePersonHashMap.put(personCode, person);
			}
		}
		return codePersonHashMap;
	}
	public static HashMap<String, Asset> assetReadIn(String fileName){
		Scanner sAsset = null;
		try {
			sAsset = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int nAsset = Integer.parseInt(sAsset.nextLine());

		// List to store the assets
		HashMap<String, Asset> codeAssetHashMap = new HashMap<>();

		// Iterate through each entry
		for (int i = 0; i < nAsset; i++) {

			String entry = sAsset.nextLine();

			// Split entry into different info fields and record length into numInfo
			String[] infoAsset = entry.split(";");
			int numInfoAsset = infoAsset.length;

			// Depending on the length of the numInfoAsset, construct the corresponding type of
			// asset and add to asset list
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
		return codeAssetHashMap;
		
	}
	//data loader for portfolio
	public static ArrayList<Portfolio> portReadIn(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Collect number of portfolio from first line
		int n = Integer.parseInt(s.nextLine());
		//iterate through the file and separate each line into different codes and asset array
		ArrayList<Portfolio> portfolioArrayList = new ArrayList<>();
		HashMap<String, Person> peopleMap = peopleReadIn("data/Persons.dat");
		for(int i=0; i<n; i++) {
			String data = s.nextLine();
			String[] info = data.split(";", -1);
			String portfolioCode = info[0];
			String ownerCode = info[1];
			Person owner = peopleMap.get(ownerCode);
			String managerCode = info[2];
			Person manager = peopleMap.get(managerCode);
			String beneficiaryCode = info[3];
			Person beneficiary = peopleMap.get(beneficiaryCode);
			String[] assetArr = info[4].split(",", -1);
			HashMap<String, Asset> assetMap = assetReadIn("data/Assets.dat");
			if(!assetArr.equals(null)) {
				List<Asset> assetList = new ArrayList<Asset>();
				for(String x: assetArr) {
					String[] xAsset= x.split(":", -1);
					Asset a = assetMap.get(xAsset[0]);
					if(xAsset.length == 2) {
						a.setNumAsset(Double.parseDouble(xAsset[1]));
						assetList.add(a);
					}
					
				}
				//add portfolio in portfolio type format into an arrayList of portfolio
				Portfolio portfolio = new Portfolio(portfolioCode, owner, manager, beneficiary, assetList);
				portfolioArrayList.add(portfolio);	
			} else {
				Portfolio portfolio = new Portfolio(portfolioCode, owner, manager, beneficiary);
				portfolioArrayList.add(portfolio);	
			}
			
				
 					
		}
		return portfolioArrayList;				
	}
}
