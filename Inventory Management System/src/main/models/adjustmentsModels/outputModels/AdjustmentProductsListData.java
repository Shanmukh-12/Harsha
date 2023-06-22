package main.models.adjustmentsModels.outputModels;

public class AdjustmentProductsListData {

	private int productId;
	private String productName;
	private String productCategoryName;
	private int batch_no;
	private int current_stock;
	private int updated_stock;
	private String adjs_desc;

	public AdjustmentProductsListData(int productId, String productName, String productCategoryName, int batch_no,
			int current_stock, int updated_stock, String adjs_desc) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategoryName = productCategoryName;
		this.batch_no = batch_no;
		this.current_stock = current_stock;
		this.updated_stock = updated_stock;
		this.adjs_desc = adjs_desc;
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

	public int getCurrent_stock() {
		return current_stock;
	}

	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}

	public int getUpdated_stock() {
		return updated_stock;
	}

	public void setUpdated_stock(int updated_stock) {
		this.updated_stock = updated_stock;
	}

	public String getAdjs_desc() {
		return adjs_desc;
	}

	public void setAdjs_desc(String adjs_desc) {
		this.adjs_desc = adjs_desc;
	}

	@Override
	public String toString() {
		return "AdjustmentProductsListData [productId=" + productId + ", productName=" + productName
				+ ", productCategoryName=" + productCategoryName + ", batch_no=" + batch_no + ", current_stock="
				+ current_stock + ", updated_stock=" + updated_stock + ", adjs_desc=" + adjs_desc + "]";
	}

}
