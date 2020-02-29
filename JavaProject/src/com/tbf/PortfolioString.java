package com.tbf;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for PortfolioString that hold information for each portfolio
 * 28 February 2020
 */

public class PortfolioString {
	
	//initialize information of owners
	private String pCode;
	private String pOwnerFirst;
	private String pOwnerLast;
	private List<String> pEmailAddresses = new ArrayList<>();
	private Address pAddress;
	private String pBroker;
	//information of managers and the corresponding fees and commissions
	private String pManagerFirst;
	private String pManagerLast;
	private double pFees;
	private double pCommissions;
	//information of total risk, return and value for the portfolio 
	private double pRisk;
	private double pReturns;
	private double pValue;
	//List of information  of assets in format of AssetString
	private List<AssetString> pAssetString= new ArrayList<>();
	//Information about beneficiary
	private String pBeneficiaryFirst = "";
	private String pBeneficiaryLast = "";
	private List<String> pEmailAddressesB = new ArrayList<>();
	private Address pAddressB;

	//Constructor for portfolioString
	public PortfolioString(String pCode, String pOwnerFirst, String pOwnerLast, String pManagerFirst,
			String pManagerLast, String pBeneficiaryFirst, String pBeneficiaryLast, double pFees, double pCommissions,
			double pRisk, double pReturns, double pValue, List<String> pEmailAddresses, Address pAddress,
			String pBroker, List<String> pEmailAddressesB, Address pAddressB, List<AssetString> pAssetString) {
		super();
		this.pCode = pCode;
		this.pOwnerFirst = pOwnerFirst;
		this.pOwnerLast = pOwnerLast;
		this.pManagerFirst = pManagerFirst;
		this.pManagerLast = pManagerLast;
		this.pFees = pFees;
		this.pCommissions = pCommissions;
		this.pRisk = pRisk;
		this.pReturns = pReturns;
		this.pValue = pValue;
		this.pEmailAddresses = pEmailAddresses;
		this.pAddress = pAddress;
		this.pBroker = pBroker;
		this.pAssetString = pAssetString;
		this.pBeneficiaryFirst = pBeneficiaryFirst;
		this.pBeneficiaryLast = pBeneficiaryLast;
		this.pEmailAddressesB = pEmailAddressesB;
		this.pAddressB = pAddressB;
	}

	//Getter for portfolioString
	public List<AssetString> getpAssetString() {
		return pAssetString;
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

	public List<String> getpEmailAddresses() {
		return pEmailAddresses;
	}

	public Address getpAddress() {
		return pAddress;
	}

	public String getpBroker() {
		return pBroker;
	}
	
	public String getpBeneficiaryFirst() {
		return pBeneficiaryFirst;
	}

	public String getpBeneficiaryLast() {
		return pBeneficiaryLast;
	}

	public List<String> getpEmailAddressesB() {
		return pEmailAddressesB;
	}

	public Address getpAddressB() {
		return pAddressB;
	}
}
