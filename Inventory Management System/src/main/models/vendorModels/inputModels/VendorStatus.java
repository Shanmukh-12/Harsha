package main.models.vendorModels.inputModels;

public class VendorStatus {
	String vendorStatus;

	public VendorStatus() {
		super();
	}

	public String getVendorStatus() {
		return vendorStatus;
	}

	public void setVendorStatus(String vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	@Override
	public String toString() {
		return "VendorStatus [vendorStatus=" + vendorStatus + "]";
	}

}
