package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for DepositAccount
 * 14 February 2020
 */
public class DepositAccount {
	
	private String code;
	private String label;
	private double apr;
	
	//DepositAccount Constructor
	public DepositAccount(String code, String label, Double apr) {
		this.code = code;
		this.label = label;
		this.apr = apr;
	}
	
	//Getters for all fields
	public String getCode() {
		return code;
	}
	public String getLabel() {
		return label;
	}
	public Double getApr() {
		return apr;
	}
	
}
