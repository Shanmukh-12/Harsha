package main.models.storeModels.inputmodels;

public class StoreStatus {
	String storeStatus;

	public StoreStatus() {
		super();
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	@Override
	public String toString() {
		return "StoreStatus [storeStatus=" + storeStatus + "]";
	}

}
