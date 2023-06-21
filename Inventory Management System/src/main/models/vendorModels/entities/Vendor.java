package main.models.vendorModels.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_vendor")
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private int vendorId;
	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "vendor_address")
	private String vendorAddress;
	@Column(name = "vendor_phonenumber")
	private Long vendorPhone;
	@Column(name = "status")
	private String status;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "last_updated_user")
	private String lastUpdatedUser;
	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;

	public Vendor() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

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

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public Long getVendorPhone() {
		return vendorPhone;
	}

	public void setVendorPhone(Long vendorPhone) {
		this.vendorPhone = vendorPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorAddress=" + vendorAddress
				+ ", vendorPhone=" + vendorPhone + ", status=" + status + ", userId=" + userId + ", lastUpdatedUser="
				+ lastUpdatedUser + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}

}
