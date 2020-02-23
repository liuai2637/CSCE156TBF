package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortfolioReport {
	
	public static ArrayList<Portfolio> dataReadIn(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int n = Integer.parseInt(s.nextLine());
		ArrayList<Portfolio> portfolioArrayList = new ArrayList<>();
		for(int i=0; i<=n; i++) {
			String data = s.nextLine();
			String[] info = data.split(";");
			String portfolioCode = info[0];
			String ownerCode = info[1];
			String managerCode = info[2];
			String beneficiaryCode = info[3];
			String[] assetArr = info[4].split(",");
			List<String> assetList = new ArrayList<String>();
			for(String x: assetArr) {
				assetList.add(x);
			}
			Portfolio portfolio = new Portfolio(portfolioCode, ownerCode, managerCode, beneficiaryCode, assetList);
			portfolioArrayList.add(portfolio);		
 					
		}
		return portfolioArrayList;
	}
	
	//hi
}
