package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for AssetString that hold information for each asset
 * 28 February 2020
 */


public class AssetString {
	//Initialize information about individual asset including 
	//asset code, name, return rate, risk, annual return and value
	private String sAssetCode;
	private String sTitle;
	private double sReturnRate;
	private double sRisk;
	private double sAnnualReturn;
	private double sValue;

	//Constructor for assetString class
	public AssetString(String sAssetCode, String sTitle, double sReturnRate, double sRisk, double sAnnualReturn,
			double sValue) {

		this.sAssetCode = sAssetCode;
		this.sTitle = sTitle;
		this.sReturnRate = sReturnRate;
		this.sRisk = sRisk;
		this.sAnnualReturn = sAnnualReturn;
		this.sValue = sValue;

	}

	//Getters for AssetString class
	public String getsAssetCode() {
		return sAssetCode;
	}

	public String getsTitle() {
		return sTitle;
	}

	public double getsReturnRate() {
		return sReturnRate;
	}

	public double getsRisk() {
		return sRisk;
	}

	public double getsAnnualReturn() {
		return sAnnualReturn;
	}

	public double getsValue() {
		return sValue;
	}
	

}
