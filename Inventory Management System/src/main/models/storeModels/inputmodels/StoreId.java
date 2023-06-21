package main.models.storeModels.inputmodels;

public class StoreId {
	int storeId;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "StoreId [storeId=" + storeId + "]";
	}

	public StoreId() {
		super();
	}

	public StoreId(int storeId) {
		super();
		this.storeId = storeId;
	}

}
