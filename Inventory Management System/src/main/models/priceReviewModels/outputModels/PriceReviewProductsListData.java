package main.models.priceReviewModels.outputModels;

public class PriceReviewProductsListData {

	private int productId;
	private String productName;
	private String productCategoryName;
	private int batch_no;
	private int old_price;
	private int new_price;
	private String review_desc;

	public PriceReviewProductsListData(int productId, String productName, String productCategoryName, int batch_no,
			int old_price, int new_price, String review_desc) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategoryName = productCategoryName;
		this.batch_no = batch_no;
		this.old_price = old_price;
		this.new_price = new_price;
		this.review_desc = review_desc;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}

	public int getOld_price() {
		return old_price;
	}

	public void setOld_price(int old_price) {
		this.old_price = old_price;
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
		return "PriceReviewProductsListData [productId=" + productId + ", productName=" + productName
				+ ", productCategoryName=" + productCategoryName + ", batch_no=" + batch_no + ", old_price=" + old_price
				+ ", new_price=" + new_price + ", review_desc=" + review_desc + "]";
	}

}
