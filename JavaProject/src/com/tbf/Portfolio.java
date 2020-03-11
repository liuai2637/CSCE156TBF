package com.tbf;

import java.util.List;

/**
 * 
 * @author Sunny Liu and Bryce Yong Constructor class for one individual
 *         portfolio 28 February 2020
 */

public class Portfolio {
	// a portfolio holding portfolio code, owner code, manager code, beneficiary code and a list of assets
	private String portfolioCode;
	private Person owner;
	private Person manager;
	private Person beneficiary;
	private List<Asset> assets;
	private double portReturn = 0;
	private double portValue = 0;
	private double portRisk = 0;
	private double portFee = 0;
	private double portCommision = 0;

	// Constructor used if portfolio HAS NO assets
	public Portfolio(String portfolioCode, Person owner, Person manager, Person beneficiary) {
		this.portfolioCode = portfolioCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
	}
	
	// Constructor used if portfolio HAS assets
	public Portfolio(String portfolioCode, Person owner, Person manager, Person beneficiary, List<Asset> assets) {
		this.portfolioCode = portfolioCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.assets = assets;
	}

	// getters and setters for all String information
	public String getPortfolioCode() {
		return portfolioCode;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public Person getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Person beneficiary) {
		this.beneficiary = beneficiary;
	}

	//Method to return the sum of Annual Return of all assets in this portfolio
	public double getPortReturn() {
		if(portReturn == 0) { //Ensures this is only calculated once
			for (Asset a : this.assets) {
				portReturn += a.getAnnualReturn();
			}
		}
		
		return portReturn;
	}

	//Method to return the sum of Value of all assets in this portfolio
	public double getPortValue() {
		if(portValue == 0) {
			for (Asset a : this.assets) {
				portValue += a.getValue();
			}
		}
		
		return portValue;
	}

	//Method to return the sum of Risk of all assets in this portfolio
	public double getPortRisk() {
		for (Asset a : this.assets) {
			portRisk += (a.getRisk()/this.getPortValue());
		}
		
		return portRisk;
	}

	//Method to return the sum of Fees of all assets in this portfolio
	public double getFee() {
		if ((manager.getBroker().getType()).equals("E")) {
			portFee = 0.0;
		} else if ((manager.getBroker().getType()).equals("J")) {
			portFee = 75 * this.getAssets().size();
		}
		return portFee;
	}

	//Method to return the sum of Commissions of all assets in this portfolio
	public double getComission() {
		if ((manager.getBroker().getType()).equals("E")) {
			portCommision = 0.0375 * this.getPortReturn();
		} else if ((manager.getBroker().getType()).equals("J")) {
			portCommision = 0.0125 * this.getPortReturn();
		}
		return portCommision;
	}

	//Method to return the list of assets in this portfolio
	public List<Asset> getAssets() {
		return assets;
	}
	
	//Method to print out the 2nd section of the report
	public void printPortfolio() {
		
			System.out.printf("Portfolio %s", this.portfolioCode);
			System.out.printf("------------------------------------------\n");
			
			// Print information about owner
			System.out.printf("Owner:\n");
			System.out.printf("%s, %s\n", this.owner.getName().getLastName(), this.owner.getName().getFirstName());
			if(!(this.owner.getBroker() == null)) {
				if (this.owner.getBroker().getType().equals("E")) {
					System.out.println("Expert Broker");
				} else if (this.owner.getBroker().getType().equals("E")) {
					System.out.println("Junior Broker");
				}
			}
			System.out.println(this.owner.getEmailAddresses());
			System.out.printf("%s\n", this.owner.getAddress().getStreet());
			System.out.printf("%s, %s %s %s\n", this.owner.getAddress().getCity(), this.owner.getAddress().getState(),
					this.owner.getAddress().getCountry(), this.owner.getAddress().getZip());
			
			// print information about manager
			System.out.println("Manager:");
			System.out.printf("%s, %s\n", this.manager.getName().getLastName(), this.manager.getName().getFirstName());
			
			// print information about beneficiary
			System.out.println("Beneficiary:");
			if (this.beneficiary == null) {
				System.out.println("none");
			} else {
				System.out.printf("%s, %s\n", this.beneficiary.getName().getLastName(),this.beneficiary.getName().getFirstName());
				System.out.println(this.beneficiary.getEmailAddresses());
				System.out.printf("%s\n", this.beneficiary.getAddress().getStreet());
				System.out.printf("%s, %s %s %s\n", this.beneficiary.getAddress().getCity(),this.beneficiary.getAddress().getState(),
						this.beneficiary.getAddress().getCountry(), this.beneficiary.getAddress().getZip());
			}
			
			// print asset list and corresponding amount for each asset
			System.out.println("Assets");
			System.out.println(
					"Code       Asset                           Return Rate          Risk          Annual Return      Value");
			for (Asset y : this.getAssets()) {
				if (y.getValue() != 0) {
					System.out.printf("%-11s", y.getCode());
					System.out.printf("%-39s", y.getLabel());
					System.out.printf("%-15.2f", y.getAnnualReturn()/y.getValue());
					System.out.printf("%-15.2f%%", y.getRisk() / y.getValue());
					System.out.printf("$  %-13.2f", y.getAnnualReturn());
					System.out.printf("$  %-13.2f\n", y.getValue());
				}
			}
			System.out.println(
					"                                                        --------------------------------------------");
			System.out.printf(
					"                                                         Totals %-13.4f%%  $  %-13.2f  $  %-13.2f\n",
					this.portRisk, this.portReturn, this.portValue);
			System.out.printf("\n");
		}
	}

