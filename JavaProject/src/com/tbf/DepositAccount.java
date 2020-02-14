package com.tbf;

public class DepositAccount {
	
	private String code;
	private String label;
	private double apr;
	
	public DepositAccount(String code, String label, Double apr) {
		this.code = code;
		this.label = label;
		this.apr = apr;
	}
	
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
