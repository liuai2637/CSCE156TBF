package com.tbf;

import java.util.ArrayList;
import java.util.List;

public class PortBeneficiaryString extends PortfolioString {
	private String pBeneficiaryFirst = "";
	private String pBeneficiaryLast = "";
	private List<String> pEmailAddressesB = new ArrayList<>();
	private Address pAddressB;

	public PortBeneficiaryString(String pCode, String pOwnerFirst, String pOwnerLast, String pManagerFirst,
			String pManagerLast, String pBeneficiaryFirst, String pBeneficiaryLast, double pFees, double pCommissions,
			double pRisk, double pReturns, double pValue, List<String> pEmailAddresses, Address pAddress,
			String pBroker, List<String> pEmailAddressesB, Address pAddressB, List<AssetString> pAssetString) {
		super(pCode, pOwnerFirst, pOwnerLast, pManagerFirst, pManagerLast, pFees,
				pCommissions, pRisk, pReturns, pValue, pEmailAddresses, pAddress, pBroker, 
				pAssetString);
		this.pBeneficiaryFirst = pBeneficiaryFirst;
		this.pBeneficiaryLast = pBeneficiaryLast;
		this.pEmailAddressesB = pEmailAddressesB;
		this.pAddressB = pAddressB;
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
