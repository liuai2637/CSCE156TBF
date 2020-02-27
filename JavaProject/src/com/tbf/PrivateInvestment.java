package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for PrivateInvestment
 * 14 February 2020
 */
public class PrivateInvestment extends Asset{

	private double baseQuarterlyDividend;
	private double baseRateOfReturn;
	private double totalAmount;
	private double omega;
	
	//PrivateInvestment Constructor
	public PrivateInvestment(String code, String label, double baseQuarterlyDividend, double baseRateOfReturn, double omega, double totalAmount) {
		super(code, label);
		this.baseQuarterlyDividend = baseQuarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.omega = omega;
		this.totalAmount = totalAmount;
	}
	
	//Getters for all fields
	public double getBaseQuarterlyDividend() {
		return baseQuarterlyDividend;
	}
	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public double getOmega() {
		return omega;
	}
	
	//Method to return the Annual Return for Private Investments (input ranges [0,1])
	public double getAnnualReturn(double input) {
		return ((this.baseRateOfReturn/100) * this.totalAmount * input) + (4 * this.baseQuarterlyDividend * input);
	}
	
}
