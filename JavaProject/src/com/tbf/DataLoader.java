package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {
	public static ArrayList<Person> peopleReadIn(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = Integer.parseInt(s.nextLine());
		ArrayList<Person> peopleArrayList = new ArrayList<>();
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
				peopleArrayList.add(person);
			} else {
				Person person = new Person(personCode, broker, name, address, email);
				peopleArrayList.add(person);
			}
		}
		return peopleArrayList;
	}
	public static ArrayList<Asset> assetReadIn(String fileName){
		Scanner sAsset = null;
		try {
			sAsset = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int nAsset = Integer.parseInt(sAsset.nextLine());

		// List to store the assets
		ArrayList<Asset> assetArrayList = new ArrayList<>();

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
				assetArrayList.add(asset);
			} else if (numInfoAsset == 8) {
				Asset asset = new Stock(infoAsset[0], infoAsset[2], Double.parseDouble(infoAsset[3]), Double.parseDouble(infoAsset[4]),
						Double.parseDouble(infoAsset[5]), infoAsset[6], Double.parseDouble(infoAsset[7]));
				assetArrayList.add(asset);
			} else {
				Asset asset = new PrivateInvestment(infoAsset[0], infoAsset[2],
						Double.parseDouble(infoAsset[3]), Double.parseDouble(infoAsset[4]), Double.parseDouble(infoAsset[5]),
						Double.parseDouble(infoAsset[6]));
				assetArrayList.add(asset);
			}
		}
		return assetArrayList;
		
	}
	//data loader for portfolio
	public static ArrayList<Portfolio> portReadIn(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int n = Integer.parseInt(s.nextLine());
		ArrayList<Portfolio> portfolioArrayList = new ArrayList<>();
		for(int i=0; i<n; i++) {
			String data = s.nextLine();
			String[] info = data.split(";", -1);
			String portfolioCode = info[0];
			String ownerCode = info[1];
			String managerCode = info[2];
			String beneficiaryCode = info[3];
			String[] assetArr = info[4].split(",", -1);
			List<String> assetList = new ArrayList<String>();
			for(String x: assetArr) {
				assetList.add(x);
			}
			Portfolio portfolio = new Portfolio(portfolioCode, ownerCode, managerCode, beneficiaryCode, assetList);
			portfolioArrayList.add(portfolio);		
 					
		}
		return portfolioArrayList;				
	}
}
