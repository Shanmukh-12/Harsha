package main.models.storeModels.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_stores")
public class Store {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private int storeId;
	@Column(name = "store_name")
	private String storeName;
	@Column(name = "store_address")
	private String storeAddress;
	@Column(name = "store_contact_number")
	private Long storeContactNumber;
	@Column(name = "status")
	private String status;
	public Store() {
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public Long getStoreContactNumber() {
		return storeContactNumber;
	}
	public void setStoreContactNumber(Long storeContactNumber) {
		this.storeContactNumber = storeContactNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", storeName=" + storeName + ", storeAddress=" + storeAddress
				+ ", storeContactNumber=" + storeContactNumber + ", status=" + status + "]";
	}
	
	

	
}
