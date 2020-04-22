package com.tbf;

import java.util.ArrayList;
import java.util.Collections;

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
		double totalValue = 0;
		double totalFee = 0;
		double totalCommission = 0;
		double totalReturn = 0;
		
		// Sort portfolioArrary by alphabetic order
		Collections.sort(portfolioArray, new SortBy());
		
		// Print out summary report on command line
		System.out.println("Portfolio Summary Report");
		System.out.println(
				"===============================================================================================================================");
		System.out.println(
				"Portfolio  Owner                Manager                       Fees     Commisions  Weighted Risk         Return          Total");
		
		//Iterate through each portfolio in portfolioArray
		for (Portfolio x : portfolioArray) {
			
			//Print out all the information in the first section of the report
			System.out.printf("%-11s", x.getPortfolioCode());
			System.out.printf("%-21s", String.format("%s, %s", x.getOwner().getName().getLastName(), x.getOwner().getName().getFirstName()));
			System.out.printf("%-20s", String.format("%s, %s", x.getManager().getName().getLastName(), x.getManager().getName().getFirstName()));
			System.out.printf("$ %12.2f", x.getFee());
			System.out.printf("  $ %11.2f", x.getComission());
			System.out.printf("%15.4f", x.getPortRisk());
			System.out.printf("  $ %11.2f", x.getPortReturn());
			System.out.printf("  $ %11.2f", x.getPortValue());
			System.out.printf("\n");
			
			//Calculate the sum of fees, commission, return, and value of all portfolios in the system
			totalFee += x.getFee();
			totalCommission += x.getComission();
			totalReturn += x.getPortReturn();
			totalValue += x.getPortValue();
		}
		System.out.println(
				"                                                     -------------------------------------------------------------------------");
		System.out.printf(
				"                                             Totals $      %.2f  $     %.2f                 $   %.2f  $  %.2f",
				totalFee, totalCommission, totalReturn, totalValue); //Print out totals (last line of first section of report)
		System.out.printf("\n\n\n\n\n");
		
		// Print reports for each individual portfolio
		System.out.printf("Portfolio Details\r\n"
				+ "================================================================================================================\n");
		for (Portfolio x : portfolioArray) {
			x.printPortfolio();
		}
	}
}
