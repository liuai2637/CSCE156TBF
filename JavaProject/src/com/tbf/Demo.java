package com.tbf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
/**
 * Author: Sunny Liu, Bryce Yong Date: 04/03/2020 This is a demo program for testing
 * 
 */

public class Demo {
	
	private static final Logger LOG = (Logger) LogManager.getLogger(Demo.class);

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

