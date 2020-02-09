
public class DepositAccount {
	//if using asset class, put code into asset class
	private String code;
	private String label;
	private double apr;
	
	public String getCode() {
		return code;
	}
	public String getLabel() {
		return label;
	}
	public Double getApr() {
		return apr;
	}
	
	public DepositAccount(String code, String label, Double apr) {
		this.code = code;
		this.label = label;
		this.apr = apr;
	}
}
