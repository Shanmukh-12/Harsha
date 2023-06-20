package main.models.priceReviewModels.inputModels;

public class PriceReviewProductsInputList {

	private int product_id;
	private int batch_no;
	private int old_Price;
	private int new_price;
	private String review_desc;

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

	@Override
	public String toString() {
		return "PriceReviewProductsInputList [product_id=" + product_id + ", batch_No=" + batch_no + ", old_Price="
				+ old_Price + ", new_price=" + new_price + ", review_desc=" + review_desc + "]";
	}

}
