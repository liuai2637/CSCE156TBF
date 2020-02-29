package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Asset (superclass of DepositAccount, Stock, And PrivateInvestment classes)
 * 14 February 2020
 */
public abstract class Asset {
	
	private String code;
	private String label;
	
	//Constructor
	public Asset(String code, String label) {
		super();
		this.code = code;
		this.label = label;
	}

	//Getters
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	//abstract method for calculation
	public abstract double getAnnualReturn(double input);

	public abstract double getValue(double input);

	public abstract double getRisk();
}
