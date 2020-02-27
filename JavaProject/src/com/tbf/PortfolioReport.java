package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PortfolioReport {

	public static void main(String[] args) {

		ArrayList<PortfolioString> portfolioStringArray = new ArrayList<>();
		double totalValue = 0.0;
		double totalAnnualReturn = 0.0;
		double totalCommissions = 0.0;
		double totalFees = 0.0;
		for (Portfolio x : DataLoader.portReadIn("data/Portfolios.dat")) {
			String owner = x.getOwnerCode();
			String manager = x.getManagerCode();
			String beneficiary = x.getBeneficiaryCode();
			String pCode = x.getPortfolioCode();

			String pOwnerFirst = "";
			String pOwnerLast = "";
			String pManagerFirst = "";
			String pManagerLast = "";
			String pBeneficiaryFirst = "";
			String pBeneficiaryLast = "";
			double pFees = 0.0;
			double pCommissions = 0.0;
			double pRisk = 0.0;
			double pReturns = 0.0;
			double pValue = 0.0;
			double value = 0.0;
			double annualReturn = 0.0;
			double risk = 0.0;
			double weightedRisk = 0.0;
			int count = 0;

			

	

			for (String z : x.getAssets()) {
				String info[] = z.split(":", -1);
				for (Asset i : DataLoader.assetReadIn("data/Assets.dat")) {
					if (info[0].equals(i.getCode())) {
						value = i.getValue(Double.parseDouble(info[1])) + value;
						annualReturn = i.getAnnualReturn(Double.parseDouble(info[1])) + annualReturn;
						risk = i.getRisk() * i.getValue(Double.parseDouble(info[1]));
						weightedRisk = risk + weightedRisk;
					}
				}
			}
			
			for (Person y : DataLoader.peopleReadIn("data/Persons.dat")) {
			
				if (owner.equals(y.getPersonCode())) {
					pOwnerFirst = y.getName().getFirstName();
					pOwnerLast = y.getName().getLastName();
				}
				if (manager.equals(y.getPersonCode())) {
					pManagerFirst = y.getName().getFirstName();
					pManagerLast = y.getName().getLastName();
					if((y.getBroker().getType()).equals("E")){
						pFees = 0.0;
						pCommissions = 0.0375 * annualReturn;
						totalFees = totalFees + pFees;
						totalCommissions = totalCommissions + pCommissions;
					} else if ((y.getBroker().getType()).equals("J")){
						for(String j: x.getAssets()) {
							if(!j.isBlank()) {
								count++;
							}
						}						
							pFees =75 * count;
						
					
						pCommissions = 0.0125 * annualReturn;
						totalFees = totalFees + pFees;
						totalCommissions = totalCommissions + pCommissions;
					}
				}
				if (beneficiary.equals(y.getPersonCode())) {
					pBeneficiaryFirst = y.getName().getFirstName();
					pBeneficiaryLast = y.getName().getLastName();
				}
			}
			
			pValue = value;
			pReturns = annualReturn;
			if (value == 0.0) {
				pRisk = 0.0;
			} else {
				pRisk = weightedRisk / value;
			}
			totalValue = totalValue + value;
			totalAnnualReturn = totalAnnualReturn + annualReturn;
			value = 0.0;
			annualReturn = 0.0;
			weightedRisk = 0.0;

			// TODO: Calculate the total Annual Return for the person x
			// By looping through each asset inside the current Portfolio x
			// Calling the getAnnualReturn method for each of them
			// Set pReturn to the sum of them

			PortfolioString portfolioReport = new PortfolioString(pCode, pOwnerFirst, pOwnerLast, pManagerFirst,
					pManagerLast, pBeneficiaryFirst, pBeneficiaryLast, pFees, pCommissions, pRisk, pReturns, pValue);
			portfolioStringArray.add(portfolioReport);
		}
		Collections.sort(portfolioStringArray, new SortByOwnerLast());
		System.out.println("Portfolio Summary Report");
		System.out.println(
				"===============================================================================================================================");
		System.out.println(
				"Portfolio  Owner                Manager                       Fees     Commisions  Weighted Risk         Return          Total");
		for (PortfolioString x : portfolioStringArray) {
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
				"                                             Totals $      %.2f  $     %.2f                 $   %.2f  $  %.2f"
				, totalFees, totalCommissions, totalAnnualReturn, totalValue);
		System.out.printf("\n\n\n\n\n");
		System.out.printf("Portfolio Details\r\n" + 
				"================================================================================================================\n");
		for (PortfolioString x : portfolioStringArray) {
		System.out.printf("Portfolio %s",x.getpCode());
		System.out.printf("------------------------------------------\n");
		System.out.printf("Owner:\n");
		System.out.printf("%s, %s\n", x.getpOwnerLast(), x.getpOwnerFirst());

		System.out.printf("\n");
		}
	}
}
