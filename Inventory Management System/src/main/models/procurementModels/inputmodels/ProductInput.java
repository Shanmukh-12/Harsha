package main.models.procurementModels.inputmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductInput {
	private int product_id;
    private int batch_no;
    private int quantity;
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
	@Override
	public String toString() {
		return "ProductInput [product_id=" + product_id + ", batch_no=" + batch_no + ", quantity=" + quantity + "]";
	}
	public ProductInput(int product_id, int batch_no, int quantity) {
		super();
		this.product_id = product_id;
		this.batch_no = batch_no;
		this.quantity = quantity;
	}
	public ProductInput() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
