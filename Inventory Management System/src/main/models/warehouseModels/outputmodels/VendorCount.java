package main.models.warehouseModels.outputmodels;

import org.springframework.stereotype.Component;

@Component
public class VendorCount {
  private int vendorcount;

public int getVendorcount() {
	return vendorcount;
}

public void setVendorcount(int vendorcount) {
	this.vendorcount = vendorcount;
}

public VendorCount(Long vendorcount) {
	super();
	this.vendorcount = vendorcount.intValue();
}

public VendorCount() {
	super();
}

@Override
public String toString() {
	return "VendorCount [vendorcount=" + vendorcount + "]";
}
  
}
