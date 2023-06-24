package main.models.storeIssueModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_storeissues_products")
public class StoreIssueProducts implements Serializable {

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
	int quantity;

	@ManyToOne()
	@JoinColumn(name = "storeIssue_id", referencedColumnName = "storeIssue_id", insertable = false, updatable = false)
	private StoreIssues storeIssues;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public StoreIssues getStoreIssues() {
		return storeIssues;
	}

	public void setStoreIssues(StoreIssues storeIssues) {
		this.storeIssues = storeIssues;
	}

	public StoreIssueProducts(int storeIssueId, int productId, int batchNo, int quantity, StoreIssues storeIssues) {
		super();
		this.storeIssueId = storeIssueId;
		this.productId = productId;
		this.batchNo = batchNo;
		this.quantity = quantity;
		this.storeIssues = storeIssues;
	}

	public StoreIssueProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

}
