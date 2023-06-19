package main.models.productModels.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_products")
public class im_products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Product_ID")
	private int product_id;
	private String product_name;
	private String product_description;
	private int product_category_id;
	private int product_hsn_code;
	private String product_type;
	private int product_reorder_level;
	private int ecommerce_reorder_level;
	private String product_status;
	private String last_updated_user;
	private Date last_updated_date;
	@OneToOne(mappedBy = "product")
	private im_products_stock ps;

	public im_products() {
		super();
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public int getProduct_category_id() {
		return product_category_id;
	}

	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}

	public int getProduct_hsn_code() {
		return product_hsn_code;
	}

	public void setProduct_hsn_code(int product_hsn_code) {
		this.product_hsn_code = product_hsn_code;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public int getProduct_reorder_level() {
		return product_reorder_level;
	}

	public void setProduct_reorder_level(int product_reorder_level) {
		this.product_reorder_level = product_reorder_level;
	}

	public int getEcommerce_reorder_level() {
		return ecommerce_reorder_level;
	}

	public void setEcommerce_reorder_level(int ecommerce_reorder_level) {
		this.ecommerce_reorder_level = ecommerce_reorder_level;
	}

	public String getProduct_status() {
		return product_status;
	}

	public void setProduct_status(String product_status) {
		this.product_status = product_status;
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

}
