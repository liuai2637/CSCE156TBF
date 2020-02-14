package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Asset (composed of DepositAccount, Stock, And PrivateInvestment classes)
 * 14 February 2020
 */
public class Asset {
	
	private DepositAccount depositAccount;
	private Stock stock;
	private PrivateInvestment privateInvestment;
	
	//Constructors for each object
	public Asset(PrivateInvestment privateInvestment) {
		this.privateInvestment = privateInvestment;
	}

	public Asset(Stock stock) {
		this.stock = stock;
	}

	public Asset(DepositAccount depositAccount) {
		this.depositAccount = depositAccount;
	}

	//Getters for each object
	public DepositAccount getDepositAccount() {
		return depositAccount;
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public PrivateInvestment getPrivateInvestment() {
		return privateInvestment;
	}
	
}
