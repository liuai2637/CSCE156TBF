package com.tbf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortfolioReport {

	public static void main(String[] args) {

		ArrayList<PortBeneficiaryString> portfolioStringArray = new ArrayList<>();
		double totalValue = 0.0;
		double totalAnnualReturn = 0.0;
		double totalCommissions = 0.0;
		double totalFees = 0.0;
		for (Portfolio port : DataLoader.portReadIn("data/Portfolios.dat")) {
			String ownerCode = port.getOwnerCode();
			String managerCode = port.getManagerCode();
			String beneficiaryCode = port.getBeneficiaryCode();
			String pCode = port.getPortfolioCode();

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
			List<AssetString> pAssetString = new ArrayList<>();
			String pBroker = "";
			double pFees = 0.0;
			double pCommissions = 0.0;
			double pRisk = 0.0;
			double pReturns = 0.0;
			double pValue = 0.0;
			double tValuePerPort = 0.0;
			double tAnnualReturn = 0.0;
			double risk = 0.0;
			double weightedRisk = 0.0;
			String sAssetCode = "";
			String sTitle = "";
			double sReturnRate = 0.0;
			double sRisk = 0.0;
			double sAnnualReturn = 0.0;
			double sValue = 0.0;

			if (beneficiaryCode.isBlank()) {
				pBeneficiaryFirst = "";
				pBeneficiaryLast = "";
			}
			for (String assetStringList : port.getAssets()) {
				String info[] = assetStringList.split(":", -1);
				for (Asset asset : DataLoader.assetReadIn("data/Assets.dat")) {
					if (info[0].equals(asset.getCode())) {
						sAssetCode = asset.getCode();
						sTitle = asset.getLabel();
						sReturnRate = asset.getAnnualReturn(Double.parseDouble(info[1]))
								/ asset.getValue(Double.parseDouble(info[1])) * 100.0;
						sRisk = asset.getRisk();
						sAnnualReturn = asset.getAnnualReturn(Double.parseDouble(info[1]));
						sValue = asset.getValue(Double.parseDouble(info[1]));
						tValuePerPort += asset.getValue(Double.parseDouble(info[1]));
						tAnnualReturn += asset.getAnnualReturn(Double.parseDouble(info[1]));
						risk = asset.getRisk() * asset.getValue(Double.parseDouble(info[1]));
						weightedRisk += risk;

					}
				}
				AssetString assetString = new AssetString(sAssetCode, sTitle, sReturnRate, sRisk, sAnnualReturn,
						sValue);
				pAssetString.add(assetString);
			}

			for (Person person : DataLoader.peopleReadIn("data/Persons.dat")) {

				if (ownerCode.equals(person.getPersonCode())) {
					pOwnerFirst = person.getName().getFirstName();
					pOwnerLast = person.getName().getLastName();
					if (person.getBroker() == null) {
						pBroker = "";
					} else if ((person.getBroker().getType()).equals("E")) {
						pBroker = "Expert Broker";
					} else if ((person.getBroker().getType()).equals("J")) {
						pBroker = "Junior Broker";
					}
					pEmailAddresses = person.getEmailAddresses();
					pAddress = person.getAddress();
				}
				if (beneficiaryCode.equals(person.getPersonCode())) {
					pBeneficiaryFirst = person.getName().getFirstName();
					pBeneficiaryLast = person.getName().getLastName();
					pEmailAddressesB = person.getEmailAddresses();
					pAddressB = person.getAddress();
				}
				if (managerCode.equals(person.getPersonCode())) {
					pManagerFirst = person.getName().getFirstName();
					pManagerLast = person.getName().getLastName();

					if ((person.getBroker().getType()).equals("E")) {
						pFees = 0.0;
						pCommissions = 0.0375 * tAnnualReturn;
						totalFees = totalFees + pFees;
						totalCommissions = totalCommissions + pCommissions;
					} else if ((person.getBroker().getType()).equals("J")) {
						for (String j : port.getAssets()) {
							if (!j.isBlank()) {
								pFees += 75;
							}
						}
						

						pCommissions = 0.0125 * tAnnualReturn;
						totalFees = totalFees + pFees;
						totalCommissions = totalCommissions + pCommissions;
					}
				}
				if (beneficiaryCode.equals(person.getPersonCode())) {
					pBeneficiaryFirst = person.getName().getFirstName();
					pBeneficiaryLast = person.getName().getLastName();
				}
			}

			pValue = tValuePerPort;
			pReturns = tAnnualReturn;
			if (tValuePerPort == 0.0) {
				pRisk = 0.0;
			} else {
				pRisk = weightedRisk / tValuePerPort;
			}
			totalValue += tValuePerPort;
			totalAnnualReturn = totalAnnualReturn + tAnnualReturn;
			


			PortBeneficiaryString portfolioReport = new PortBeneficiaryString(pCode, pOwnerFirst, pOwnerLast,
					pManagerFirst, pManagerLast, pBeneficiaryFirst, pBeneficiaryLast, pFees, pCommissions, pRisk,
					pReturns, pValue, pEmailAddresses, pAddress, pBroker, pEmailAddressesB, pAddressB, pAssetString);
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
			if (!x.getpBroker().isBlank()) {
				System.out.printf("%s\n", x.getpBroker());
			}
			System.out.println(x.getpEmailAddresses());
			System.out.printf("%s\n", x.getpAddress().getStreet());
			System.out.printf("%s, %s %s %s\n", x.getpAddress().getCity(), x.getpAddress().getState(),
					x.getpAddress().getCountry(), x.getpAddress().getZip());
			System.out.println("Manager:");
			System.out.printf("%s, %s\n", x.getpManagerLast(), x.getpManagerFirst());
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

			System.out.println("Assets");
			System.out.println(
					"Code       Asset                           Return Rate          Risk          Annual Return      Value");
			for (AssetString y : x.getpAssetString()) {
				if (y.getsValue() != 0) {
					System.out.printf("%-11s", y.getsAssetCode());
					System.out.printf("%-39s", y.getsTitle());
					System.out.printf("%-15.2f", y.getsReturnRate());
					System.out.printf("%-15.2f  $", y.getsRisk());
					System.out.printf("%-13.2f  $", y.getsAnnualReturn());
					System.out.printf("%-13.2f\n", y.getsValue());
				}
			}
			System.out.println(
					"                                                        --------------------------------------------");
			System.out.printf(
					"                                                         Totals %-13.4f  $%-13.2f  $%-13.2f\n",
					x.getpRisk(), x.getpReturns(), x.getpValue());
			System.out.printf("\n");
		}
	}
}
