package main.dao.vendor;

import java.util.List;

import main.models.vendorModels.entities.Vendor;
import main.models.vendorModels.inputModels.VendorId;
import main.models.vendorModels.inputModels.VendorStatus;

public interface VendorsDAO {
	
	// Saving Vendor Information
	public Vendor saveVendor(Vendor vendor);
   
	// Getting Vendor Data Based on Vendor Id
	public Vendor getVendorData(VendorId v);

	// Updating the Vendor Information
	public Vendor updateVendor(Vendor vendor);
	
	// Soft deleting the Vendor i.e. Making status Inactive
	public Vendor deleteVendor(VendorId vendor);
	
   //Getting all Vendors Information
	public List<Vendor> getAllVendors();
	
   //Filter Vendor Information Based on Vendor Status
	public List<Vendor> getVendorStatus(VendorStatus vendorStatus);
}
