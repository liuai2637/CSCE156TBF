package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PortfolioReport {

	public static void main(String[] args) {

		ArrayList<PortBeneficiaryString> portfolioStringArray = new ArrayList<>();
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
			List<String> pEmailAddresses = new ArrayList<>();
			Address pAddress = new Address("st", "ct", "sta", "zc", "cy"); 
			Address pAddressB = new Address("st", "ct", "sta", "zc", "cy"); 
			List<String> pEmailAddressesB = new ArrayList<>();
			List<AssetString> pAssetString= new ArrayList<>();
			String pBroker = "";
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
			String sAssetCode = "";
			String sTitle = "";
			double sReturnRate = 0.0;
			double sRisk = 0.0;
			double sAnnualReturn= 0.0;
			double sValue = 0.0;
			
			
			
			
			if(beneficiary.isBlank()) {
				pBeneficiaryFirst = "";
				pBeneficiaryLast = "";
			}
			for (String z : x.getAssets()) {
				String info[] = z.split(":", -1);
				for (Asset i : DataLoader.assetReadIn("data/Assets.dat")) {
					if (info[0].equals(i.getCode())) {
						sAssetCode = i.getCode();
						sTitle = i.getLabel();
						sReturnRate = i.getAnnualReturn(Double.parseDouble(info[1])) / i.getValue(Double.parseDouble(info[1]))  * 100.0;
						sRisk = i.getRisk();
						sAnnualReturn = i.getAnnualReturn(Double.parseDouble(info[1]));
						sValue = i.getValue(Double.parseDouble(info[1]));
						value = i.getValue(Double.parseDouble(info[1])) + value;
						annualReturn = i.getAnnualReturn(Double.parseDouble(info[1])) + annualReturn;
						risk = i.getRisk() * i.getValue(Double.parseDouble(info[1]));
						weightedRisk = risk + weightedRisk;
						
					}
				}
				AssetString assetString = new AssetString(sAssetCode, sTitle, sReturnRate,  sRisk, sAnnualReturn, sValue);
				pAssetString.add(assetString);
			}

			for (Person y : DataLoader.peopleReadIn("data/Persons.dat")) {

				if (owner.equals(y.getPersonCode())) {
					pOwnerFirst = y.getName().getFirstName();
					pOwnerLast = y.getName().getLastName();
					if(y.getBroker() == null) {
						pBroker = "";
					} else {
						if ((y.getBroker().getType()).equals("E")) {
							pBroker = "Expert Broker";
						} else if ((y.getBroker().getType()).equals("J")) {
							pBroker = "Junior Broker";
						}
					}
					pEmailAddresses = y.getEmailAddresses();
					pAddress = y.getAddress(); 
				}
				if (beneficiary.equals(y.getPersonCode())) {
					pBeneficiaryFirst = y.getName().getFirstName();
					pBeneficiaryLast = y.getName().getLastName();
					pEmailAddressesB = y.getEmailAddresses();
					pAddressB = y.getAddress();
				}
				if (manager.equals(y.getPersonCode())) {
					pManagerFirst = y.getName().getFirstName();
					pManagerLast = y.getName().getLastName();
					
				
					if ((y.getBroker().getType()).equals("E")) {
						pFees = 0.0;					
						pCommissions = 0.0375 * annualReturn;
						totalFees = totalFees + pFees;
						totalCommissions = totalCommissions + pCommissions;
					} else if ((y.getBroker().getType()).equals("J")) {
						for (String j : x.getAssets()) {
							if (!j.isBlank()) {
								count++;
							}
						}
						pFees = 75 * count;

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

			PortBeneficiaryString portfolioReport = new PortBeneficiaryString(pCode, pOwnerFirst, pOwnerLast, pManagerFirst,
					pManagerLast, pBeneficiaryFirst, pBeneficiaryLast, pFees, pCommissions, pRisk, pReturns, pValue,
					pEmailAddresses, pAddress, pBroker, pEmailAddressesB, pAddressB, pAssetString);
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
				"                                             Totals $      %.2f  $     %.2f                 $   %.2f  $  %.2f",
				totalFees, totalCommissions, totalAnnualReturn, totalValue);
		System.out.printf("\n\n\n\n\n");
		System.out.printf("Portfolio Details\r\n"
				+ "================================================================================================================\n");
		for (PortBeneficiaryString x : portfolioStringArray) {
			System.out.printf("Portfolio %s", x.getpCode());
			System.out.printf("------------------------------------------\n");
			System.out.printf("Owner:\n");
			System.out.printf("%s, %s\n", x.getpOwnerLast(), x.getpOwnerFirst());
			// TODO: get email from Person
			if(!x.getpBroker().isBlank()) {
				System.out.printf("%s\n",x.getpBroker());
			}
			System.out.println(x.getpEmailAddresses());
			System.out.printf("%s\n",x.getpAddress().getStreet());
			System.out.printf("%s, %s %s %s\n",x.getpAddress().getCity(), x.getpAddress().getState(), x.getpAddress().getCountry(), x.getpAddress().getZip());
			System.out.println("Manager:");
			System.out.printf("%s, %s\n", x.getpManagerLast(), x.getpManagerFirst());
			System.out.println("Beneficiary:");
			if(x.getpBeneficiaryFirst().isBlank() && x.getpBeneficiaryLast().isBlank()) {
				System.out.println("none");
			} else {
				System.out.printf("%s, %s\n", x.getpBeneficiaryLast(), x.getpBeneficiaryFirst());
				System.out.println(x.getpEmailAddressesB());
				System.out.printf("%s\n",x.getpAddressB().getStreet());
				System.out.printf("%s, %s %s %s\n",x.getpAddressB().getCity(), x.getpAddressB().getState(), x.getpAddressB().getCountry(), x.getpAddressB().getZip());
			}
			
			System.out.println("Assets");
			System.out.println("Code       Asset                           Return Rate          Risk          Annual Return      Value");
			for(AssetString y : x.getpAssetString()) {
				if(y.getsValue() != 0) {
					System.out.printf("%-11s", y.getsAssetCode());
					System.out.printf("%-39s", y.getsTitle());
					System.out.printf("%-15.2f", y.getsReturnRate());
					System.out.printf("%-15.2f  $", y.getsRisk());
					System.out.printf("%-13.2f  $", y.getsAnnualReturn());
					System.out.printf("%-13.2f\n", y.getsValue());
				}
			}
			System.out.println("                                                        --------------------------------------------");
			System.out.printf("                                                         Totals %-13.4f  $%-13.2f  $%-13.2f\n",x.getpRisk(), x.getpReturns(), x.getpValue());
			System.out.printf("\n");
		}
	}
}
