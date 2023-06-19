package main.models.vendorModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "im_vendor")
@NamedQuery(name = "Vendor.findById", query = "SELECT v FROM Vendor v")
public class VendorData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private int vendorId;
	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "status")
	private String Status;

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "VendorData [vendorId=" + vendorId + ", vendorName=" + vendorName + ", Status=" + Status + "]";
	}

	// other fields, getters, setters, etc.
}
