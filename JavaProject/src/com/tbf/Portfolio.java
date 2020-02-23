package com.tbf;

import java.util.List;

public class Portfolio {

	private String portfolioCode;
	private String ownerCode;
	private String managerCode;
	private String beneficiaryCode;
	private List<String> assets;
		
	public Portfolio(String portfolioCode, String ownerCode, String managerCode, String beneficiaryCode,
			List<String> assets) {
		this.portfolioCode = portfolioCode;
		this.ownerCode = ownerCode;
		this.managerCode = managerCode;
		this.beneficiaryCode = beneficiaryCode;
		this.assets = assets;
	}

	public String getPortfolioCode() {
		return portfolioCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}

	public List<String> getAssets() {
		return assets;
	}

	
	
	
}
