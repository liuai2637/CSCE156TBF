package com.tbf;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for one individual portfolio
 * 28 February 2020
 */

public class Portfolio {
	//a portfolio holding portfolio code, owner code, manager code, 
	//beneficiary code and a list of assets
	private String portfolioCode;
	private String ownerCode;
	private String managerCode;
	private String beneficiaryCode;
	private List<String> assets;
	//Constructor of portfolio class
	public Portfolio(String portfolioCode, String ownerCode, String managerCode, String beneficiaryCode,
			List<String> assets) {
		this.portfolioCode = portfolioCode;
		this.ownerCode = ownerCode;
		this.managerCode = managerCode;
		this.beneficiaryCode = beneficiaryCode;
		this.assets = assets;
	}
	//getters for portfolio code, owner code, beneficiary code and list of assets
	public String getPortfolioCode() {
		return portfolioCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}

	public List<String> getAssets() {
		return assets;
	}

	//This method calculate value of portfolioStringArray for each portfolio 
	//and each total amount including total fees, total commission and total return.
	public static void calculation(ArrayList<PortfolioString> portfolioStringArray, double[] totalAmount) {
		double totalFee = 0.0;
		double totalComission = 0.0;
		double totalReturn = 0.0;
		double totalValue = 0.0;
		
		// iterate through the Portfolios.dat file to record each portfolio.
		for (Portfolio port : DataLoader.portReadIn("data/Portfolios.dat")) {
			String ownerCode = port.getOwnerCode();
			String managerCode = port.getManagerCode();
			String beneficiaryCode = port.getBeneficiaryCode();
			String pCode = port.getPortfolioCode();
			// initialized information required for calculation that include other files
			// such as Assets.dat and Persons.dat
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
			// check if a beneficiary in included and initialize.
			if (beneficiaryCode.isBlank()) {
				pBeneficiaryFirst = "";
				pBeneficiaryLast = "";
			}
			// for each loop that iterate through asset lists for each portfolio and
			// calculate total amount for each portfolio.
			for (String assetStringList : port.getAssets()) {
				String info[] = assetStringList.split(":", -1);
				// iterate through Assets.dat file to find the corresponding asset for each
				// asset of a portfolio.
				for (Asset asset : DataLoader.assetReadIn("data/Assets.dat")) {
					String assetGetCode = asset.getCode();
					if (info[0].equals(assetGetCode)) {
						// get value from the asset class
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
				// create assetString to hold asset informations for each portfolio and to an
				// arrayList.
				AssetString assetString = new AssetString(sAssetCode, sTitle, sReturnRate, sRisk, sAnnualReturn,
						sValue);
				pAssetString.add(assetString);
			}
			// Iterate through Persons.dat file to find corresponding person for each
			// portfolio
			for (Person person : DataLoader.peopleReadIn("data/Persons.dat")) {
				// collect information according to the person class
				if (ownerCode.equals(person.getPersonCode())) {
					pOwnerFirst = person.getName().getFirstName();
					pOwnerLast = person.getName().getLastName();
					// Check for if the owner is a broker or not
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
				// find corresponding beneficiary and collect information
				if (beneficiaryCode.equals(person.getPersonCode())) {
					pBeneficiaryFirst = person.getName().getFirstName();
					pBeneficiaryLast = person.getName().getLastName();
					pEmailAddressesB = person.getEmailAddresses();
					pAddressB = person.getAddress();
				}
				// find corresponding names and broker type of manager
				if (managerCode.equals(person.getPersonCode())) {
					pManagerFirst = person.getName().getFirstName();
					pManagerLast = person.getName().getLastName();
					// calculate fees and commissions according to the broker type of managers
					if ((person.getBroker().getType()).equals("E")) {
						pFees = 0.0;
						pCommissions = 0.0375 * tAnnualReturn;
						totalFee += pFees;
						totalAmount[2] += pCommissions;
					} else if ((person.getBroker().getType()).equals("J")) {
						for (String j : port.getAssets()) {
							if (!j.isBlank()) {
								pFees += 75;
							}
						}
						pCommissions = 0.0125 * tAnnualReturn;
						totalFee += pFees;
						totalComission += pCommissions;
					}
				}
			}
			// record total value and return for each portfolio and calculate total risk
			pValue = tValuePerPort;
			pReturns = tAnnualReturn;
			totalValue += pValue;
			if (tValuePerPort == 0.0) {
				pRisk = 0.0;
			} else {
				pRisk = weightedRisk / tValuePerPort;
			}
			totalReturn += tAnnualReturn;

			// record information for each portfolio
			PortfolioString portfolioReport = new PortfolioString(pCode, pOwnerFirst, pOwnerLast, pManagerFirst,
					pManagerLast, pBeneficiaryFirst, pBeneficiaryLast, pFees, pCommissions, pRisk, pReturns, pValue,
					pEmailAddresses, pAddress, pBroker, pEmailAddressesB, pAddressB, pAssetString);
			portfolioStringArray.add(portfolioReport);
		}
		//record all total amounts to an array to execute
		totalAmount[0] = totalValue; 
		totalAmount[1] = totalFee; 
		totalAmount[2] = totalComission;
		totalAmount[3] = totalReturn;
	}
}
