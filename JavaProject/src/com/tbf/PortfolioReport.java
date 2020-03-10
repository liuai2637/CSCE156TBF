package com.tbf;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Author: Sunny Liu, Bryce Yong Date: 02/28/2020 This program output a report
 * on commend line including calculation in fees, return and risk and detailed
 * information about each portfolio.
 * 
 */

public class PortfolioReport {

	public static void main(String[] args) {
		// create arraylist that hold portforlioString for each portfolio in the
		// document.
		ArrayList<Portfolio> portfolioArray = new ArrayList<>();
		portfolioArray = DataLoader.portReadIn("data/Portfolios.dat");
		
		
		// initialize variables for the overall total amount for value, annualReturn,
		// commissions and fees.

		double totalValue = 0;
		double totalFee = 0;
		double totalCommission = 0;
		double totalReturn = 0;
		// Sort portfolioArrary by alphabetic order
		Collections.sort(portfolioArray, new SortByOwnerLast());
		// Print out summary report on commend line
		System.out.println("Portfolio Summary Report");
		System.out.println(
				"===============================================================================================================================");
		System.out.println(
				"Portfolio  Owner                Manager                       Fees     Commisions  Weighted Risk         Return          Total");
		for (Portfolio x : portfolioArray) {
			
			System.out.printf("%-11s", x.getPortfolioCode());
			System.out.printf("%-21s", String.format("%s, %s", x.getOwner().getName().getLastName(), x.getOwner().getName().getFirstName()));
			System.out.printf("%-20s", String.format("%s, %s", x.getManager().getName().getLastName(), x.getManager().getName().getFirstName()));
			System.out.printf("$ %12.2f", x.getFee());
			totalFee += x.getFee();
			System.out.printf("  $ %11.2f", x.getComission());
			totalCommission += x.getComission();
			System.out.printf("%15.4f", x.getPortRisk());
			System.out.printf("  $ %11.2f", x.getPortReturn());
			totalReturn += x.getPortReturn();
			System.out.printf("  $ %11.2f", x.getPortValue());
			totalValue += x.getPortValue();
			System.out.printf("\n");
		}
		System.out.println(
				"                                                     -------------------------------------------------------------------------");
		System.out.printf(
				"                                             Totals $      %.2f  $     %.2f                 $   %.2f  $  %.2f",
				totalFee, totalCommission, totalReturn, totalValue);
		System.out.printf("\n\n\n\n\n");
		// Print report for each individual portfolio
		System.out.printf("Portfolio Details\r\n"
				+ "================================================================================================================\n");
		for (Portfolio x : portfolioArray) {
			x.printPortfolio();
			System.out.printf("\n");
		}
	}
}
