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
	private double numOfShares;
	
	//Stock Constructor
	public Stock(String code, String label, double baseQuarterlyDividend, double baseRateOfReturn, double beta, String stockSymbol, double sharePrice) {
		super(code, label);
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
	
	//Maybe implement the setter for Number of Shares?
	public void setInput(double numOfShares) {
		this.numOfShares = numOfShares;
	}
	
	//Method to return the Annual Return for Stocks
	public double getAnnualReturn() {
		return ((this.baseRateOfReturn/100) * this.sharePrice * this.numOfShares) + (4 * this.baseQuarterlyDividend * this.numOfShares);
	}
}
