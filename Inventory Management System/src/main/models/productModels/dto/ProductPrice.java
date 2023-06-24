package main.models.productModels.dto;

public class ProductPrice {

	private int productId;
	private int quantity;
	private double costPrice;

	public ProductPrice(int productId, int quantity, double costPrice) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.costPrice = costPrice;
	}

	public ProductPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

}
