package main.models.priceReviewModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_priceReview_Items")
public class PriceReviewProductsList implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pr_id")
	int pr_id;
	@Id
	@Column(name = "product_id")
	int product_id;
	@Id
	@Column(name = "batch_no")
	int batch_no;

	@Column(name = "old_Price")
	int old_Price;

	@Column(name = "new_price")
	int new_price;

	@Column(name = "review_desc")
	String review_desc;

	@ManyToOne
	@JoinColumn(name = "pr_id", referencedColumnName = "pr_id", insertable = false, updatable = false)
	PriceReviewList prl;

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

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
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

	public PriceReviewList getPrl() {
		return prl;
	}

	public void setPrl(PriceReviewList prl) {
		this.prl = prl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
