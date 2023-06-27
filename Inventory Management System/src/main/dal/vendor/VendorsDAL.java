package main.dal.vendor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.dao.vendor.VendorsDAO;
import main.models.vendorModels.entities.Vendor;
import main.models.vendorModels.inputModels.VendorId;
import main.models.vendorModels.inputModels.VendorStatus;

@Component
public class VendorsDAL implements VendorsDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	// Saving Vendor Information
	@Transactional
	public Vendor saveVendor(Vendor vendor) {
		try {
			Vendor mergedVendor = entityManager.merge(vendor); // Save the Vendor object in the database and get the merged instance
			return mergedVendor; // Return the merged Vendor object
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Vendor with the same unique key already exists.");
		}
	}
	
	// Getting all Vendors
	@Transactional
	public List<Vendor> getAllVendors() {
		List<Vendor> listVendors = entityManager.createQuery("SELECT v FROM Vendor v").getResultList(); // Retrieve all Vendor objects from the database
		return listVendors; // Return the list of Vendor objects
	}
	
	// Getting Vendor Data Based on Id
	@Transactional
	public Vendor getVendorData(VendorId vendorId) {
		Vendor getVendor = entityManager.find(Vendor.class, vendorId.getVendorId()); // Find the Vendor object with the given vendorId
		return getVendor; // Return the found Vendor object
	}
	
	// Updating the Vendor Information
	@Transactional
	public Vendor updateVendor(Vendor vendor) {
		Vendor existingVendor = entityManager.find(Vendor.class, vendor.getVendorId()); // Find the Vendor object with the given vendorId
		if (existingVendor != null) {
			existingVendor.setVendorName(vendor.getVendorName()); // Update the vendorName field of the existing Vendor object
			existingVendor.setVendorAddress(vendor.getVendorAddress()); // Update the vendorAddress field of the existing Vendor object
			existingVendor.setVendorPhone(vendor.getVendorPhone()); // Update the vendorPhone field of the existing Vendor object
			entityManager.merge(existingVendor); // Merge the changes into the database
			return existingVendor; // Return the updated Vendor object
		} else {
			return null;
		}
	}
	
	// Soft deleting the Vendor
	@Transactional
	public Vendor deleteVendor(VendorId vendor) {
		Vendor existingVendor = entityManager.find(Vendor.class, vendor.getVendorId()); // Find the Vendor object with the given vendorId
		existingVendor.setStatus("Inactive"); // Set the status of the existing Vendor object to "Inactive"
		entityManager.merge(existingVendor); // Merge the changes into the database
		return existingVendor; // Return the updated Vendor object
	}
	
	// Getting all Active Vendors
	@Override
	public List<Vendor> getVendorStatus(VendorStatus vendorStatus) {
		String status = vendorStatus.getVendorStatus();
		List<Vendor> listVendors = entityManager.createQuery("SELECT v FROM Vendor v where v.status=:status")
				.setParameter("status", vendorStatus.getVendorStatus()).getResultList(); // Retrieve all Vendor objects with status equal to the given vendorStatus
		return listVendors; // Return the list of active Vendor objects
	}

}
