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

			for (Person y : DataLoader.peopleReadIn("data/Persons.dat")) {
				if (owner.equals(y.getPersonCode())) {
					pOwnerFirst = y.getName().getFirstName();
					pOwnerLast = y.getName().getLastName();
				}
				if (manager.equals(y.getPersonCode())) {
					pManagerFirst = y.getName().getFirstName();
					pManagerLast = y.getName().getLastName();
				}
				if (beneficiary.equals(y.getPersonCode())) {
					pBeneficiaryFirst = y.getName().getFirstName();
					pBeneficiaryLast = y.getName().getLastName();
				}
			}
			
			//TODO: Calculate the total Annual Return for the person x
			//By looping through each asset inside the current Portfolio x
			//Calling the getAnnualReturn method for each of them
			//Set pReturn to the sum of them
			
			PortfolioString portfolioReport = new PortfolioString(pCode, pOwnerFirst, pOwnerLast, pManagerFirst,
					pManagerLast, pBeneficiaryFirst, pBeneficiaryLast, pFees, pCommissions, pRisk, pReturns, pValue);
			portfolioStringArray.add(portfolioReport);
		}
		Collections.sort(portfolioStringArray, new SortByOwnerLast());
		System.out.println("Portfolio Summary Report");		
		System.out.println("===============================================================================================================================");
		System.out.println("Portfolio  Owner                Manager                       Fees     Commisions  Weighted Risk         Return          Total");
		for(PortfolioString x: portfolioStringArray) {
			System.out.printf("%-11s", x.getpCode());
			System.out.printf("%-21s",String.format("%s, %s",x.getpOwnerLast(), x.getpOwnerFirst()));
			System.out.printf("%s",String.format("%s, %s",x.getpManagerLast(), x.getpManagerFirst()));
			System.out.printf("\n");
		}

	}
}
