package main.models.storeModels.outputmodels;

public class StoreIds {
	
	private int storeId;
	private String storeName;
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "StoreIds [storeId=" + storeId + ", storeName=" + storeName + "]";
	}

}
