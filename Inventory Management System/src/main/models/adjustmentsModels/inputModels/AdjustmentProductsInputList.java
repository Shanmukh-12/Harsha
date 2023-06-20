package main.models.adjustmentsModels.inputModels;

public class AdjustmentProductsInputList {

	private int product_id;
	private int batch_no;
	private int current_stock;
	private int updated_stock;
	private String adjs_desc;

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
		return "AdjustmentProductsInputList [product_id=" + product_id + ", batch_no=" + batch_no + ", current_stock="
				+ current_stock + ", updated_stock=" + updated_stock + ", adjs_desc=" + adjs_desc + "]";
	}

}
