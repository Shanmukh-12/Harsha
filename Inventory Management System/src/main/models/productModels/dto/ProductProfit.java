package main.models.productModels.dto;

public class ProductProfit {

	private double profitPercentage;

	public ProductProfit(double profitPercentage) {
		super();
		this.profitPercentage = profitPercentage;
	}

	public double getProfitPercentage() {
		return profitPercentage;
	}

	public void setProfitPercentage(double profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public ProductProfit() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductProfit [profitPercentage=" + profitPercentage + "]";
	}

}
