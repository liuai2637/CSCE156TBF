package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for Stock
 * 14 February 2020
 */
public class Stock {
	private String code;
	private String label;
	private double baseQuarterlyDividend;
	private double baseRateOfReturn;
	private String stockSymbol;
	private double sharePrice;
	private double beta;
	
	//Stock Constructor
	public Stock(String code, String label, double baseQuarterlyDividend, double baseRateOfReturn, double beta, String stockSymbol, double sharePrice) {
		this.code = code;
		this.label = label;
		this.baseQuarterlyDividend = baseQuarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.beta = beta;
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
	}
	
	//Getters for all fields
	public String getCode() {
		return code;
	}
	public String getLabel() {
		return label;
	}
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
	
	
}
