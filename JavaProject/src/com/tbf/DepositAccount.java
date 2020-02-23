package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for DepositAccount
 * 14 February 2020
 */
public class DepositAccount extends Asset{
	private double apr;
	
	//DepositAccount Constructor
	public DepositAccount(String code, String label, double apr) {
		super(code, label);
		this.apr = apr;
	}

	//Getters for all fields
	public Double getApr() {
		return apr;
	}
}
