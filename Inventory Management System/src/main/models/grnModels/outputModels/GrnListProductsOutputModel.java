package main.models.grnModels.outputModels;

public class GrnListProductsOutputModel {

	private int grn_id;
	private int product_id;
	private int batch_no;
	private int quantity;
	private double price;
	private int bonus;
	private int totalQuantity;

	public GrnListProductsOutputModel() {
		super();
	}

	public GrnListProductsOutputModel(int grn_id, int product_id, int batch_no, int quantity, double price, int bonus,
			int totalQuantity) {
		super();
		this.grn_id = grn_id;
		this.product_id = product_id;
		this.batch_no = batch_no;
		this.quantity = quantity;
		this.price = price;
		this.bonus = bonus;
		this.totalQuantity = totalQuantity;
	}

	public int getGrn_id() {
		return grn_id;
	}

	public void setGrn_id(int grn_id) {
		this.grn_id = grn_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Override
	public String toString() {
		return "GrnListProductsOutputModel [grn_id=" + grn_id + ", product_id=" + product_id + ", batch_no=" + batch_no
				+ ", quantity=" + quantity + ", price=" + price + ", bonus=" + bonus + ", totalQuantity="
				+ totalQuantity + "]";
	}

}
