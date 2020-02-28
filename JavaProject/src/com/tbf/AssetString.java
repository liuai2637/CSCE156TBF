package com.tbf;

public class AssetString {

	private String sAssetCode;
	private String sTitle;
	private double sReturnRate;
	private double sRisk;
	private double sAnnualReturn;
	private double sValue;
	
	public AssetString(String sAssetCode, String sTitle, double sReturnRate, double sRisk, double sAnnualReturn,
			double sValue) {
		super();
		this.sAssetCode = sAssetCode;
		this.sTitle = sTitle;
		this.sReturnRate = sReturnRate;
		this.sRisk = sRisk;
		this.sAnnualReturn = sAnnualReturn;
		this.sValue = sValue;
	}

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
