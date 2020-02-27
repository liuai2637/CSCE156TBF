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
	
	//Method to return the Annual Return for Deposit Accounts
	public double getAnnualReturn(double input) {
		return (Math.exp(this.apr) - 1) * input;
	}
}
