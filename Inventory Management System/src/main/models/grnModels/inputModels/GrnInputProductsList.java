package main.models.grnModels.inputModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GrnInputProductsList {

	int product_id;
	int batch_no;
	int quantity;
	int bonus;

	public GrnInputProductsList() {
	}

	public GrnInputProductsList(int product_id, int batch_no, int quantity, int bonus) {
		super();
		this.product_id = product_id;
		this.batch_no = batch_no;
		this.quantity = quantity;
		this.bonus = bonus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public int getQuantity_received() {
		return quantity;
	}

	public void setQuantity_received(int quantity_received) {
		this.quantity = quantity_received;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "GrnInputProductsList [product_id=" + product_id + ", batch_no=" + batch_no + ", quantity_received="
				+ quantity + ", bonus=" + bonus + "]";
	}

}
