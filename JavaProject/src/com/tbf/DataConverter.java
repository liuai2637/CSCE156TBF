package com.tbf;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.thoughtworks.xstream.XStream;


/**
 * Author: Sunny Liu, Bryce Yong
 * Date: 02/14/2020
 * This program convert a flat data file with data about TBF's person 
 * and assets to json and xml format.
 * 
 */

public class DataConverter {

	public static void main(String[] args) {
		//read in the file
		String fileName = "data/Persons.dat";
		HashMap<String, Person> codePeopleHashMap = DataLoader.peopleReadIn(fileName);
		
		//Initialize new json object g
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		//Initialize new xml object xstream
		XStream xstream = new XStream();

		try {
			//print formatted files to new output file using print writers
			File outputJson = new File("data/Persons.json");
			File outputXml = new File("data/Persons.xml");
			PrintWriter pwJson = new PrintWriter(outputJson);
			PrintWriter pwXml = new PrintWriter(outputXml);
			
			pwJson.println(g.toJson(codePeopleHashMap.values()));
			pwXml.println(xstream.toXML(codePeopleHashMap.values()));
			pwJson.close();
			pwXml.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Scan in the asset data file and record number of entries into nAsset
				String fileNameAsset = "data/Assets.dat";
				HashMap<String, Asset> codeAssetHashMap = DataLoader.assetReadIn(fileNameAsset);
				//output in format of jason and xml with Gson and xstream
				Gson gAsset = new GsonBuilder().setPrettyPrinting().create();
				XStream xstreamAsset = new XStream();
				try {
					File outputJson = new File("data/Assets.json");
					File outputXml = new File("data/Assets.xml");
					PrintWriter pwJson = new PrintWriter(outputJson);
					PrintWriter pwXml = new PrintWriter(outputXml);
					pwJson.println(gAsset.toJson(codeAssetHashMap.values()));
					pwXml.println(xstreamAsset.toXML(codeAssetHashMap.values()));
					pwJson.close();
					pwXml.close();
				} catch (JsonIOException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}

