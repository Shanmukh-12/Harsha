package main.models.storeIssueModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_storeIssues_products")
public class StoreIssueProduct implements Serializable {
	@Id
	@Column(name = "storeIssue_id")
	int storeIssueId;
	@Id
	@Column(name = "product_id")
	int productId;
	@Id
	@Column(name = "batch_no")
	int batchNo;

	@Column(name = "quantity")
	int issuedQuantity;

	public StoreIssueProduct() {
		super();
	}

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
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

	public int getIssuedQuantity() {
		return issuedQuantity;
	}

	public void setIssuedQuantity(int issuedQuantity) {
		this.issuedQuantity = issuedQuantity;
	}

	@Override
	public String toString() {
		return "StoreIssueProduct [storeIssueId=" + storeIssueId + ", productId=" + productId + ", batchNo=" + batchNo
				+ ", issuedQuantity=" + issuedQuantity + "]";
	}

}
