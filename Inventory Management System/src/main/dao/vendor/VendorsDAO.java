package main.dao.vendor;

import java.util.List;

import main.models.vendorModels.entities.Vendor;
import main.models.vendorModels.inputModels.VendorId;
import main.models.vendorModels.inputModels.VendorStatus;

public interface VendorsDAO {
	public Vendor saveVendor(Vendor vendor);

	public Vendor getVendorData(VendorId v);

	public Vendor updateVendor(Vendor vendor);

	public Vendor deleteVendor(VendorId vendor);

	public List<Vendor> getAllVendors();

	public List<Vendor> getActiveVendors(VendorStatus vendorStatus);
}
