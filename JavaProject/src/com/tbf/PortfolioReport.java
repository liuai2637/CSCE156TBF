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
		ArrayList<PortfolioString> portfolioStringArray = new ArrayList<>();
		// initialize variables for the overall total amount for value, annualReturn,
		// commissions and fees.
		double[] totalAmount = {0.0,0.0,0.0,0.0};
		Portfolio.calculation(portfolioStringArray, totalAmount);
		double totalValue = totalAmount[0];
		double totalFee = totalAmount[1];
		double totalCommission = totalAmount[2];
		double totalReturn = totalAmount[3];
		// Sort portfolioArrary by alphabetic order
		Collections.sort(portfolioStringArray, new SortByOwnerLast());
		// Print out summary report on commend line
		System.out.println("Portfolio Summary Report");
		System.out.println(
				"===============================================================================================================================");
		System.out.println(
				"Portfolio  Owner                Manager                       Fees     Commisions  Weighted Risk         Return          Total");
		for (PortfolioString x : portfolioStringArray) {
			totalAmount[0] += x.getpValue();
			
			System.out.printf("%-11s", x.getpCode());
			System.out.printf("%-21s", String.format("%s, %s", x.getpOwnerLast(), x.getpOwnerFirst()));
			System.out.printf("%-20s", String.format("%s, %s", x.getpManagerLast(), x.getpManagerFirst()));
			System.out.printf("$ %12.2f", x.getpFees());
			System.out.printf("  $ %11.2f", x.getpCommissions());
			System.out.printf("%15.4f", x.getpRisk());
			System.out.printf("  $ %11.2f", x.getpReturns());
			System.out.printf("  $ %11.2f", x.getpValue());
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
		for (PortfolioString x : portfolioStringArray) {
			System.out.printf("Portfolio %s", x.getpCode());
			System.out.printf("------------------------------------------\n");
			// Print information about owner
			System.out.printf("Owner:\n");
			System.out.printf("%s, %s\n", x.getpOwnerLast(), x.getpOwnerFirst());
			if (!x.getpBroker().isBlank()) {
				System.out.printf("%s\n", x.getpBroker());
			}
			System.out.println(x.getpEmailAddresses());
			System.out.printf("%s\n", x.getpAddress().getStreet());
			System.out.printf("%s, %s %s %s\n", x.getpAddress().getCity(), x.getpAddress().getState(),
					x.getpAddress().getCountry(), x.getpAddress().getZip());
			// print information about manager
			System.out.println("Manager:");
			System.out.printf("%s, %s\n", x.getpManagerLast(), x.getpManagerFirst());
			// print information about beneficiary
			System.out.println("Beneficiary:");
			if (x.getpBeneficiaryFirst().isBlank() && x.getpBeneficiaryLast().isBlank()) {
				System.out.println("none");
			} else {
				System.out.printf("%s, %s\n", x.getpBeneficiaryLast(), x.getpBeneficiaryFirst());
				System.out.println(x.getpEmailAddressesB());
				System.out.printf("%s\n", x.getpAddressB().getStreet());
				System.out.printf("%s, %s %s %s\n", x.getpAddressB().getCity(), x.getpAddressB().getState(),
						x.getpAddressB().getCountry(), x.getpAddressB().getZip());
			}
			// print asset list and corresponding amount for each asset
			System.out.println("Assets");
			System.out.println(
					"Code       Asset                           Return Rate          Risk          Annual Return      Value");
			for (AssetString y : x.getpAssetString()) {
				if (y.getsValue() != 0) {
					System.out.printf("%-11s", y.getsAssetCode());
					System.out.printf("%-39s", y.getsTitle());
					System.out.printf("%-15.2f", y.getsReturnRate());
					System.out.printf("%-15.2f", y.getsRisk());
					System.out.printf("$  %-13.2f", y.getsAnnualReturn());
					System.out.printf("$  %-13.2f\n", y.getsValue());
				}
			}
			System.out.println(
					"                                                        --------------------------------------------");
			System.out.printf(
					"                                                         Totals %-13.4f  $  %-13.2f  $  %-13.2f\n",
					x.getpRisk(), x.getpReturns(), x.getpValue());
			System.out.printf("\n");
		}
	}
}
