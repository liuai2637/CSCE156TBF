
public class PrivateInvestment {

	private String code;
	private String label;
	private double baseQuarterlyDividend;
	private double baseRateOfReturn;
	private double totalAmount;
	private double omega;
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
	public double getTotalAmount() {
		return totalAmount;
	}
	public double getOmega() {
		return omega;
	}
	public PrivateInvestment(String code, String label, double baseQuarterlyDividend, double baseRateOfReturn,
			double totalAmount, double omega) {
		this.code = code;
		this.label = label;
		this.baseQuarterlyDividend = baseQuarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.totalAmount = totalAmount;
		this.omega = omega;
	}
}
