package main.dao.vendor;

import java.util.List;

import main.dto.vendor.VendorDto;
import main.models.vendorModels.Vendor;

public interface VendorsDAO {
	public Vendor saveVendor(Vendor vendor);
	public List<Vendor> getAllVendors();
	public Vendor getVendorData(VendorDto v);
	public Vendor updateVendor(Vendor vendor);
	 public Vendor deleteVendor(Vendor vendor);
}
