package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Asset (abstract superclass of DepositAccount, Stock, And PrivateInvestment classes)
 * 14 February 2020
 */
public abstract class Asset {
	
	private String code;	//Every Asset has a unique assetCode
	private String label;	//Name of the Asset
	private String type;
	
	//Constructor
	public Asset(String code, String label, String type) {
		super();
		this.code = code;
		this.label = label;
		this.type = type;
	}

	//Getters
	public String getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	public String getLabel() {
		return label;
	}

	//abstract methods for calculation (delete input variable)
	public abstract void setNumAsset(double input);
	
	public abstract double getAnnualReturn();

	public abstract double getValue();

	public abstract double getRisk();
	
	//TODO: What does this method do?
	public double getTotalRiskPerAsset(double input) {
		return this.getRisk() * this.getValue();
	}
	
	
}
