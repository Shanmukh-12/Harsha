package main.models.storeModels.inputmodels;

public class StoreInfo {
	int storeId;
	String storeName;
	String storeAddress;
	String storeContactNumber;
	String status;

	public StoreInfo() {
		super();
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreContactNumber() {
		return storeContactNumber;
	}

	public void setStoreContactNumber(String storeContactNumber) {
		this.storeContactNumber = storeContactNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
