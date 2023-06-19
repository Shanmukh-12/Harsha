package main.models.productModels.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="im_products_category")
public class ProductCategories {
    @Id
	private int product_category_id;
	private String product_category_name;
	private String last_updated_user;
	private Date last_updated_date;
	public ProductCategories() {
		super();
	}
	public int getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getProduct_category_name() {
		return product_category_name;
	}
	public void setProduct_category_name(String product_category_name) {
		this.product_category_name = product_category_name;
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
	public ProductCategories(int product_category_id, String product_category_name, String last_updated_user,
			Date last_updated_date) {
		super();
		this.product_category_id = product_category_id;
		this.product_category_name = product_category_name;
		this.last_updated_user = last_updated_user;
		this.last_updated_date = last_updated_date;
	}
	@Override
	public String toString() {
		return "ProductCategories [product_category_id=" + product_category_id + ", product_category_name="
				+ product_category_name + ", last_updated_user=" + last_updated_user + ", last_updated_date="
				+ last_updated_date + "]";
	}
	
}
