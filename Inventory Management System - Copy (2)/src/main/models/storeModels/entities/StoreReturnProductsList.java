package main.models.storeModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="im_store_returns_products")
public class StoreReturnProductsList implements Serializable{

	@Id
	@Column(name="store_return_id")
	int returnId;
	
	@Id
	@Column(name="product_id")
	int productId;
	
	@Id
	@Column(name="batch_no")
	int batchNo;
	
	@Column(name="quantity")
	int quantity;
	
	@Column(name="return_des")
	String reason;

	@ManyToOne
	@JoinColumn(name="Store_return_id", referencedColumnName="Store_return_id",insertable=false, updatable=false )
	StoreReturnsList srl;
	
	public int getReturnId() {
		return returnId;
	}

	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "StoreReturnProductsList [returnId=" + returnId + ", productId=" + productId + ", batchNo=" + batchNo
				+ ", quantity=" + quantity + ", reason=" + reason + "]";
	}
	
}
