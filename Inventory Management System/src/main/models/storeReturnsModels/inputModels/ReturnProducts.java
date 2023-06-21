package main.models.storeReturnsModels.inputModels;

import java.util.List;

public class ReturnProducts {

	int storeID;
	int issueId;
	List<StoreReturnProducts> productsList;

	public static class StoreReturnProducts {

		int productId;
		int batchNo;
		int quantity;
		String reason;

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public int getBatchNo() {
			return batchNo;
		}

		public void setBatchNo(int batchNo) {
			this.batchNo = batchNo;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public StoreReturnProducts() {
			super();
			// TODO Auto-generated constructor stub
		}

		public StoreReturnProducts(int productId, int batchNo, int quantity, String reason) {
			super();
			this.productId = productId;
			this.batchNo = batchNo;
			this.quantity = quantity;
			this.reason = reason;
		}

		@Override
		public String toString() {
			return "StoreReturnProducts [productId=" + productId + ", batchNo=" + batchNo + ", quantity=" + quantity
					+ ", reason=" + reason + "]";
		}
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public List<StoreReturnProducts> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<StoreReturnProducts> productsList) {
		this.productsList = productsList;
	}

	public ReturnProducts(int storeID, int issueId, List<StoreReturnProducts> productsList) {
		super();
		this.storeID = storeID;
		this.issueId = issueId;
		this.productsList = productsList;
	}

	public ReturnProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ReturnProducts [storeID=" + storeID + ", issueId=" + issueId + ", productsList=" + productsList + "]";
	}

}
