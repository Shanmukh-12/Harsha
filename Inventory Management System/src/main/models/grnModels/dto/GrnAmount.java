package main.models.grnModels.dto;

public class GrnAmount {

	double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public GrnAmount(double amount) {
		super();
		this.amount = amount;
	}

	public GrnAmount() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GrnAmount [amount=" + amount + "]";
	}

}
