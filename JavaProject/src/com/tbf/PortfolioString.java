package com.tbf;

import java.util.ArrayList;
import java.util.List;

public class PortfolioString {

	private String pCode;
	private String pOwnerFirst;
	private String pOwnerLast;
	private String pManagerFirst;
	private String pManagerLast;
	private double pFees;
	private double pCommissions;
	private double pRisk;
	private double pReturns;
	private double pValue;
	private List<String> pEmailAddresses = new ArrayList<>();
	private Address pAddress;
	private String pBroker;
	private List<AssetString> pAssetString= new ArrayList<>();

	
	public PortfolioString(String pCode, String pOwnerFirst, String pOwnerLast, String pManagerFirst,
			String pManagerLast, double pFees, double pCommissions,
			double pRisk, double pReturns, double pValue, List<String> pEmailAddresses, Address pAddress,
			String pBroker, List<AssetString> pAssetString) {
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
	}

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
}
