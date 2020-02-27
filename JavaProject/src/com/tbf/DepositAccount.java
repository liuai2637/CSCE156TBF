package com.tbf;

import java.lang.Math;

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
	public double getApr() {
		return apr;
	}
	
	//method to return value
	public double getValue(double balance) {
		return balance;
	}
	//Method to return the Annual Return for Deposit Accounts
	public double getAnnualReturn(double balance) {
		return (Math.exp(this.apr/100) - 1) * balance;
	}
	//Method to return risk
	public double getRisk() {
		return 0.0;
	}
}
