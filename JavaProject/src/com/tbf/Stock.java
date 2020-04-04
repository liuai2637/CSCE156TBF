package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Stock
 * 14 February 2020
 */
public class Stock extends Asset{
	private double baseQuarterlyDividend;
	private double baseRateOfReturn;
	private String stockSymbol;
	private double sharePrice;
	private double beta;
	private double numOfShare;
	private double annualReturn;
	private double value;
	
	//Stock Constructor
	public Stock(String code, String label, String type, double baseQuarterlyDividend, 
			     double baseRateOfReturn, double beta, String stockSymbol, 
			     double sharePrice) {
		super(code, label, type);
		this.baseQuarterlyDividend = baseQuarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.beta = beta;
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
	}
	
	//Getters for all fields
	public double getBaseQuarterlyDividend() {
		return baseQuarterlyDividend;
	}
	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public double getBeta() {
		return beta;
	}
	
	//Method to return value of stock
	public double getValue() {
		if(value != 0) {
			return value;
		} else {
		this.value = numOfShare * this.sharePrice;	//Only calculates once for every Stock
		return value;
		}
	}
	
	//Method to return the Annual Return for Stocks
	public double getAnnualReturn() {
		this.annualReturn = ((this.baseRateOfReturn/100) * this.sharePrice * numOfShare) + (4 * this.baseQuarterlyDividend * numOfShare);
		return annualReturn;
	}
	
	//Method to return risk
	public double getRisk() {
		return this.beta * this.getValue();
	}

	//Method to return the number of shares
	public double getNumOfShare() {
		return numOfShare;
	}

	//Setter which sets this Stock's number of shares (from portfolio input value)
	public void setNumAsset(double numOfShare) {
		this.numOfShare = numOfShare;
	}


	
}
