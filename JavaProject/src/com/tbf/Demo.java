package com.tbf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo {

	public static void main(String[] args) {
		
		ArrayList<Portfolio> portfolioList = DataLoaderSql.loadPortfolio();
		
		
		for(Portfolio x : portfolioList) {
			System.out.println(x.getOwner().getName().getLastName());
			List<Asset> assets = x.getAssets();
			for(Asset y : assets) {
				System.out.println(y.getLabel() + y.getValue());
			}
		}

	}

}

