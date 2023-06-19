package main.dal.vendor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.dao.vendor.VendorsDAO;
import main.dto.vendor.VendorDto;
import main.models.vendorModels.Vendor;

@Component
public class VendorsDAL implements VendorsDAO{

	@PersistenceContext
	private EntityManager entityManager;

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

	@Transactional
	public List<Vendor> getAllVendors() {
		List<Vendor> l = entityManager.createQuery("SELECT v FROM Vendor v").getResultList();
//		for (Vendor v : l) {
//			System.out.println(v.toString());
//		}
		return l;
	}

	@Transactional
	public Vendor getVendorData(VendorDto v) {
		Vendor getVendor = entityManager.find(Vendor.class, v.getVendorId());
		return getVendor;
	}

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

	 @Transactional
	 public Vendor deleteVendor(Vendor vendor) {
		 System.out.println("Inside delete vendoe dao");
		 Vendor existingVendor = entityManager.find(Vendor.class, vendor.getVendorId());
	     existingVendor.setStatus("Inactive");
	     entityManager.merge(existingVendor);
		 return  existingVendor;
	
	 }

}
