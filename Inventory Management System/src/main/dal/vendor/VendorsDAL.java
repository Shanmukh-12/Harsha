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
			Vendor mergedVendor = entityManager.merge(vendor);
			System.out.println(mergedVendor);
			return mergedVendor;
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Vendor with the same unique key already exists.");
		}
	}

	// Getting all Vendors
	@Transactional
	public List<Vendor> getAllVendors() {
		List<Vendor> l = entityManager.createQuery("SELECT v FROM Vendor v").getResultList();
		// for (Vendor v : l) {
		// System.out.println(v.toString());
		// }
		return l;
	}

	// Getting Vendor Data Based on Id
	@Transactional
	public Vendor getVendorData(VendorId v) {
		Vendor getVendor = entityManager.find(Vendor.class, v.getVendorId());
		return getVendor;
	}

	// Updating the Vendor Information
	@Transactional
	public Vendor updateVendor(Vendor vendor) {
		Vendor existingVendor = entityManager.find(Vendor.class, vendor.getVendorId());
		if (existingVendor != null) {
			existingVendor.setVendorName(vendor.getVendorName());
			existingVendor.setVendorAddress(vendor.getVendorAddress());
			existingVendor.setVendorPhone(vendor.getVendorPhone());
			entityManager.merge(existingVendor);
			return existingVendor;
		} else {
			return null;
		}
	}

	// Soft deleting the Vendor
	@Transactional
	public Vendor deleteVendor(VendorId vendor) {
		System.out.println("Inside delete vendoe dao");
		Vendor existingVendor = entityManager.find(Vendor.class, vendor.getVendorId());
		existingVendor.setStatus("Inactive");
		entityManager.merge(existingVendor);
		return existingVendor;

	}

	@Override
	public List<Vendor> getActiveVendors(VendorStatus vendorStatus) {
		String x = vendorStatus.getVendorStatus();
		List<Vendor> l = entityManager.createQuery("SELECT v FROM Vendor v where v.status=:x")
				.setParameter("x", vendorStatus.getVendorStatus()).getResultList();
		for (Vendor v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

}
