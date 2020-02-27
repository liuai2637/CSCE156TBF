package com.tbf;

public class AssetWrapper {
	
	private Asset asset;
	private double input;
	
	public AssetWrapper(Asset asset, double input) {
		this.asset = asset;
		this.input = input;
	}

	public Asset getAsset() {
		return asset;
	}

	public double getInput() {
		return input;
	}
	
}
