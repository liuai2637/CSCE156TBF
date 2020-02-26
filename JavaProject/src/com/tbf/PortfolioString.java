package com.tbf;


public class PortfolioString {
	 
	private String pCode;
	private String pOwnerFirst;
	private String pOwnerLast;
	private String pManagerFirst;
	private String pManagerLast;
	private String pBeneficiaryFirst;
	private String pBeneficiaryLast;
	private double pFees;
	private double pCommissions;
	private double pRisk;
	private double pReturns;
	private double pValue;
	
	public PortfolioString (String pCode, String pOwnerFirst, String pOwnerLast, String pManagerFirst,
			String pManagerLast, String pBeneficiaryFirst, String pBeneficiaryLast, double pFees, 
			double pCommissions, double pRisk, double pReturns, double pValue) {
		this.pCode = pCode;
		this.pOwnerFirst = pOwnerFirst;
		this.pOwnerLast = pOwnerLast;
		this.pManagerFirst= pManagerFirst;
		this.pManagerLast= pManagerLast;
		this.pBeneficiaryFirst = pBeneficiaryFirst;
		this.pBeneficiaryLast = pBeneficiaryLast;
		this.pFees = pFees;
		this.pRisk = pRisk;
		this.pReturns = pReturns;
		this.pValue = pValue;
	}
	
	public String getpCode() {
		return pCode;
	}

	public String getpOwnerFirst() {
		return pOwnerFirst;
	}

	public String getpOwnerLast() {
		return pOwnerLast;
	}
	
	public String getpManagerFirst() {
		return pManagerFirst;
	}

	public String getpManagerLast() {
		return pManagerLast;
	}
	
	public String getpBeneficiaryFirst() {
		return pBeneficiaryFirst;
	}
	
	public String getpBeneficiaryLast() {
		return pBeneficiaryLast;
	}
	
	public double getpFees() {
		return pFees;
	}

	public double getpCommissions() {
		return pCommissions;
	}

	public double getpRisk() {
		return pRisk;
	}

	public double getpReturns() {
		return pReturns;
	}

	public double getpValue() {
		return pValue;
	}

	
	
}
