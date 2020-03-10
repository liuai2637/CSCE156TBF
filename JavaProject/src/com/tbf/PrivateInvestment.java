package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong 
 * Constructor class for PrivateInvestment 
 * 14 February 2020
 */
public class PrivateInvestment extends Asset {

	private double baseQuarterlyDividend;
	private double baseRateOfReturn;
	private double totalAmount;
	private double omega;
	private double percentStake;
	private double annualReturn;
	private double value;
	private double risk;
	//TODO: include input

	// PrivateInvestment Constructor
	public PrivateInvestment(String code, String label, double baseQuarterlyDividend, double baseRateOfReturn,
			double omega, double totalAmount) {
		super(code, label);
		this.baseQuarterlyDividend = baseQuarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.omega = omega;
		this.totalAmount = totalAmount;
	}

	// Getters for all fields
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

	// Method to return the value
	public double getValue() {
		if(value != 0) {
			return value;
		} else {
		this.value = (percentStake / 100 * this.totalAmount);
		return value;
		}
	}

	// Method to return the Annual Return for Private Investments (input ranges
	// [0,1])
	public double getAnnualReturn() {
		this.annualReturn = ((this.baseRateOfReturn / 100) * this.totalAmount * percentStake / 100)
				+ (4 * this.baseQuarterlyDividend * percentStake / 100);
		return annualReturn;
	}

	// Method to return risk
	public double getRisk() {
		if (this.totalAmount == 0.0) {
			risk = 0.0;
		} else {
			risk = ( this.omega + Math.exp(-125500 / this.totalAmount)) * this.getValue();
		}
		return risk;
	}

	public double getPerecentStake() {
		return percentStake;
		
	}

	public void setNumAsset(double percentStake) {
		this.percentStake = percentStake;
	}

	
}
