import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AssetDemo {

	public static void main(String[] args) {

		// Scan in the asset data file and record number of entries into n
		String fileName = "data/Assets.dat";
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = Integer.parseInt(s.nextLine());

		// List to store the assets
		ArrayList<Asset> assetArrayList = new ArrayList<>();

		// Iterate through each entry
		for (int i = 0; i < n; i++) {

			String entry = s.nextLine();

			// Split entry into different info fields and record length into numInfo
			String[] info = entry.split(";");
			int numInfo = info.length;

			// Depending on the length of the numInfo, construct the corresponding type of
			// asset
			// and add to asset list
			// Checking the label doesn't work for some reason
			// e.g. if(num[1] == "D") doesn't work, when it is D, it just skips
			if (numInfo == 4) {
				DepositAccount depositAccount = new DepositAccount(info[0], info[2], Double.parseDouble(info[3]));
				Asset asset = new Asset(depositAccount);
				assetArrayList.add(asset);
			} else if (numInfo == 8) {
				Stock stock = new Stock(info[0], info[2], Double.parseDouble(info[3]), Double.parseDouble(info[4]),
						Double.parseDouble(info[5]), info[6], Double.parseDouble(info[7]));
				Asset asset = new Asset(stock);
				assetArrayList.add(asset);
			} else {
				PrivateInvestment privateInvestment = new PrivateInvestment(info[0], info[2],
						Double.parseDouble(info[3]), Double.parseDouble(info[4]), Double.parseDouble(info[5]),
						Double.parseDouble(info[6]));
				Asset asset = new Asset(privateInvestment);
				assetArrayList.add(asset);
			}
		}

		Gson g = new GsonBuilder().setPrettyPrinting().create();
		XStream xstream = new XStream(new DomDriver());

		try {
			File outputJson = new File("data/Assets.json");
			File outputXml = new File("data/AssetsTest.xml");
			PrintWriter pwJson = new PrintWriter(outputJson);
			PrintWriter pwXml = new PrintWriter(outputXml);
			pwJson.println(g.toJson(assetArrayList));
			pwXml.println(xstream.toXML(assetArrayList));
			pwJson.close();
			pwXml.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
