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
	
	//Method to return value of stock
	public double getValue(double numStock) {
		return (numStock * this.sharePrice);
	}
	//Method to return the Annual Return for Stocks
	public double getAnnualReturn(double numStock) {
		return ((this.baseRateOfReturn/100) * this.sharePrice * numStock) + (4 * this.baseQuarterlyDividend * numStock);
	}
	//Method to return risk
	public double getRisk() {
		return this.beta;
	}
}
