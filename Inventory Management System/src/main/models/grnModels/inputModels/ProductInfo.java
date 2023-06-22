package main.models.grnModels.inputModels;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductInfo {
	int product_id;
	int batch_no;
	int product_mrp = 4250;
	int product_cost = 4000;
	int product_sale_price = 4200;
	private String last_updated_user;
	private Date last_updated_date;
	@JsonProperty("quantity")

	int product_stock;

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}

	public int getProduct_mrp() {
		return product_mrp;
	}

	public void setProduct_mrp(int product_mrp) {
		this.product_mrp = product_mrp;
	}

	public int getProduct_cost() {
		return product_cost;
	}

	public void setProduct_cost(int product_cost) {
		this.product_cost = product_cost;
	}

	public int getProduct_sale_price() {
		return product_sale_price;
	}

	public void setProduct_sale_price(int product_sale_price) {
		this.product_sale_price = product_sale_price;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getLast_updated_user() {
		return last_updated_user;
	}

	public void setLast_updated_user(String last_updated_user) {
		this.last_updated_user = last_updated_user;
	}

	public Date getLast_updated_date() {
		return last_updated_date;
	}

	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}

	public ProductInfo(int product_id, int batch_no, int product_mrp, int product_cost, int product_sale_price,
			String last_updated_user, Date last_updated_date, int product_stock) {
		super();
		this.product_id = product_id;
		this.batch_no = batch_no;
		this.product_mrp = product_mrp;
		this.product_cost = product_cost;
		this.product_sale_price = product_sale_price;
		this.last_updated_user = last_updated_user;
		this.last_updated_date = last_updated_date;
		this.product_stock = product_stock;
	}

	public ProductInfo() {
		super();
	}

	@Override
	public String toString() {
		return "ProductInfo [product_id=" + product_id + ", batch_no=" + batch_no + ", product_mrp=" + product_mrp
				+ ", product_cost=" + product_cost + ", product_sale_price=" + product_sale_price
				+ ", last_updated_user=" + last_updated_user + ", last_updated_date=" + last_updated_date
				+ ", product_stock=" + product_stock + "]";
	}

}
