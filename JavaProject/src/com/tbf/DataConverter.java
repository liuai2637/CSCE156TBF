package com.tbf;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.thoughtworks.xstream.XStream;

public class DataConverter {
	
	/**
	 * Author: Sunny Liu, Bryce Yong
	 * Date: 02/14/2020
	 * This program convert a flat data file with data about TBF's person 
	 * and assets to json and xml format.
	 * @param args
	 */

	public static void main(String[] args) {
		//read in the file
		String fileName = "data/Persons.dat";
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = Integer.parseInt(s.nextLine());
		ArrayList<Person> peopleArrayList = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			String account = s.nextLine();

			String[] info = account.split(";");

			String personCode = info[0];

			Broker broker = new Broker("", "");
			if (info[1].length() != 0) {
				String[] bro = info[1].split(",");
				broker = new Broker(bro[0], bro[1]);
			}

			String[] nam = info[2].split(",");
			Name name = new Name(nam[0], nam[1]);

			String[] add = info[3].split(",");
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);
			
			List<String> email = new ArrayList<>();
			if (info.length == 5) {
				String[] ema = info[4].split(",");
				for (String x : ema) {
					email.add(x);
				}
			}
			//Input different formatted person object depend on whether the person is a broker or not
			if (!broker.isBroker()) {
				Person person = new Person(personCode, name, address, email);
				peopleArrayList.add(person);
			} else {
				Person person = new Person(personCode, broker, name, address, email);
				peopleArrayList.add(person);
			}
		}

		
		//Initialize new json object g
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		//Initialize new xml object xstream
		XStream xstream = new XStream();

		try {
			//print formatted files to new output file
			File outputJson = new File("data/Persons.json");
			File outputXml = new File("data/Persons.xml");
			PrintWriter pwJson = new PrintWriter(outputJson);
			PrintWriter pwXml = new PrintWriter(outputXml);
			pwJson.println(g.toJson(peopleArrayList));
			
			String people = xstream.toXML(peopleArrayList);
			int locOfFirstTransaction = people.indexOf("<list");
			String firstPart = people.substring(0, locOfFirstTransaction);
		    firstPart = firstPart.concat("\n\t<person>\n\t"); 
		    // Define the remaining part of the string and concatenate the firstPart to it
		    String lastPart = people.substring(locOfFirstTransaction, people.length());
		    people = firstPart.concat(lastPart);

		    // Replace the closing tag for rootTag
		    people = people.replace("</list", "\t</person>\n</currentscreen");
			pwXml.println(xstream.toXML(peopleArrayList));

			pwJson.close();
			pwXml.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Scan in the asset data file and record number of entries into nAsset
				String fileNameAsset = "data/Assets.dat";
				Scanner sAsset = null;
				try {
					sAsset = new Scanner(new File(fileNameAsset));
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
					// asset
					// and add to asset list
					// Checking the label doesn't work for some reason
					// e.g. if(num[1] == "D") doesn't work, when it is D, it just skips
					if (numInfoAsset == 4) {
						DepositAccount depositAccount = new DepositAccount(infoAsset[0], infoAsset[2], Double.parseDouble(infoAsset[3]));
						Asset asset = new Asset(depositAccount);
						assetArrayList.add(asset);
					} else if (numInfoAsset == 8) {
						Stock stock = new Stock(infoAsset[0], infoAsset[2], Double.parseDouble(infoAsset[3]), Double.parseDouble(infoAsset[4]),
								Double.parseDouble(infoAsset[5]), infoAsset[6], Double.parseDouble(infoAsset[7]));
						Asset asset = new Asset(stock);
						assetArrayList.add(asset);
					} else {
						PrivateInvestment privateInvestment = new PrivateInvestment(infoAsset[0], infoAsset[2],
								Double.parseDouble(infoAsset[3]), Double.parseDouble(infoAsset[4]), Double.parseDouble(infoAsset[5]),
								Double.parseDouble(infoAsset[6]));
						Asset asset = new Asset(privateInvestment);
						assetArrayList.add(asset);
					}
				}

				Gson gAsset = new GsonBuilder().setPrettyPrinting().create();
				XStream xstreamAsset = new XStream();
				try {
					File outputJson = new File("data/Assets.json");
					File outputXml = new File("data/Assets.xml");
					PrintWriter pwJson = new PrintWriter(outputJson);
					PrintWriter pwXml = new PrintWriter(outputXml);
					pwJson.println(gAsset.toJson(assetArrayList));
					pwXml.println(xstreamAsset.toXML(assetArrayList));
					pwJson.close();
					pwXml.close();
				} catch (JsonIOException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}


	}

}

