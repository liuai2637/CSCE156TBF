
public class Stock {
	private String code;
	private String label;
	private double baseQuarterlyDividend;
	private double baseRateOfReturn;
	private String stockSymbol;
	private double sharePrice;
	private double beta;
	
	public Stock(String code, String label, double baseQuarterlyDividend, double baseRateOfReturn, double beta, String stockSymbol, double sharePrice) {
		this.code = code;
		this.label = label;
		this.baseQuarterlyDividend = baseQuarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.beta = beta;
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
	}
	
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
