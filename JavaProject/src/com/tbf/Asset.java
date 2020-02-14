package com.tbf;

public class Asset {
	
	private DepositAccount depositAccount;
	private Stock stock;
	private PrivateInvestment privateInvestment;
	
	//Constructors
	public Asset(PrivateInvestment privateInvestment) {
		this.privateInvestment = privateInvestment;
	}

	public Asset(Stock stock) {
		this.stock = stock;
	}

	public Asset(DepositAccount depositAccount) {
		this.depositAccount = depositAccount;
	}

	//Getters
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
