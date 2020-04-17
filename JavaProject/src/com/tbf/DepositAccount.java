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
	private double balance;
	private double annualReturn;
	
	//DepositAccount Constructor
	public DepositAccount(String code, String label, String type, double apr) {
		super(code, label, type);
		this.apr = apr;
	}

	//Method to return apr
	public double getApr() {
		return apr;
	}
	
	//Method to return value
	public double getValue() {
		return balance;
	}
	//Method to return the Annual Return for Deposit Accounts
	public double getAnnualReturn() {
		this.annualReturn = (Math.exp(this.apr) - 1) * balance;
		return annualReturn;
	}
	//Method to return risk
	public double getRisk() {
		return 0.0;
	}

	//Setter which sets this deposit account's balance (from portfolio input value)
	public void setNumAsset(double balance) {
		this.balance = balance;
	}



}
