package main.models.productModels.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_products_stock")
public class im_products_stock {

	@Id
	@Column(name = "Product_ID")
	private int product_id;
	private int batch_no;
	private int product_stock;
	private int product_mrp;
	private int product_cost;
	private int product_sale_price;
	private String last_updated_user;
	private Date last_updated_date;

	@OneToOne
	@JoinColumn(name = "Product_ID", referencedColumnName = "Product_ID")
	private im_products product;

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

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
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

	public im_products_stock() {
		super();
	}

}
