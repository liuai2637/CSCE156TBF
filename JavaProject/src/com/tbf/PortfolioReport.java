package com.tbf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
/**
 * Author: Sunny Liu, Bryce Yong Date: 02/28/2020 This program output a report
 * on commend line including calculation in fees, return and risk and detailed
 * information about each portfolio.
 * 
 */

public class PortfolioReport {

	public static void main(String[] args) {
		
		// configurator for logging
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
		
		ArrayList<Portfolio> portfolioArray = new ArrayList<>();
		portfolioArray = DataLoaderSql.loadPortfolio();	//portfolioArray contains all the portfolios in the system
		
		// initialize variables for the overall total amount for value, annualReturn, commissions and fees. (Sum of all portfolios)
		//double totalValue = 0;
		//double totalFee = 0;
		//double totalCommission = 0;
		//double totalReturn = 0;
		
		// Sort portfolioArrary by alphabetic order
		LinkedList <Portfolio> sortedByOwnerLastName = new LinkedList<Portfolio>(LinkedList.sortByOwnerLastName);
		for(Portfolio x : portfolioArray) {
			sortedByOwnerLastName.insertIntoSorted(x);
		}
		Portfolio.printSummary(sortedByOwnerLastName);
		
		// Sort portfolio by value (descending)
		LinkedList <Portfolio> sortedByPortfolioValueDesc = new LinkedList<Portfolio>(LinkedList.sortByPortfolioValueDesc);
		for(Portfolio x : portfolioArray) {
			sortedByPortfolioValueDesc.insertIntoSorted(x);
		}
		Portfolio.printSummary(sortedByPortfolioValueDesc);
		
		// Sort portfolio by manager
		LinkedList <Portfolio> sortedByManager = new LinkedList<Portfolio>(LinkedList.sortByManager);
		for(Portfolio x : portfolioArray) {
			sortedByManager.insertIntoSorted(x);
		}
		Portfolio.printSummary(sortedByManager);

		
		/**
		System.out.printf("Portfolio Details\r\n"
				+ "================================================================================================================\n");
		for (Portfolio x : sortedByOwnerLastName) {
			x.printPortfolio();
		}
		**/
	}
}
