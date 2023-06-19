package main.models.pricereviewModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PriceReview_Items")

public class PriceReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pr_id;
	private int product_id;
	private int batch_No;
	private int old_Price;
	private int new_price;
	private String review_desc;

	public PriceReview(int pr_id, int product_id, int batch_No, int old_Price, int new_price, String review_desc) {
		super();
		this.pr_id = pr_id;
		this.product_id = product_id;
		this.batch_No = batch_No;
		this.old_Price = old_Price;
		this.new_price = new_price;
		this.review_desc = review_desc;
	}

	public int getPr_id() {
		return pr_id;
	}

	public void setPr_id(int pr_id) {
		this.pr_id = pr_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getBatch_No() {
		return batch_No;
	}

	public void setBatch_No(int batch_No) {
		this.batch_No = batch_No;
	}

	public int getOld_Price() {
		return old_Price;
	}

	public void setOld_Price(int old_Price) {
		this.old_Price = old_Price;
	}

	public int getNew_price() {
		return new_price;
	}

	public void setNew_price(int new_price) {
		this.new_price = new_price;
	}

	public String getReview_desc() {
		return review_desc;
	}

	public void setReview_desc(String review_desc) {
		this.review_desc = review_desc;
	}

	@Override
	public String toString() {
		return "PriceReview [pr_id=" + pr_id + ", product_id=" + product_id + ", batch_No=" + batch_No + ", old_Price="
				+ old_Price + ", new_price=" + new_price + ", review_desc=" + review_desc + "]";
	}

}

