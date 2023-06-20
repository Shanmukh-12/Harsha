package main.models.storeIssueModels.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="im_storeissues_products")
public class StoreIssueProducts {

	@Id
	@Column(name="storeIssue_id")
	int storeIssueId;
	
	@Column(name="product_id")
	int productId;
	
	@Column(name="quantity")
	int quantity;

	@ManyToOne()
	@JoinColumn(name="storeIssue_id",referencedColumnName="storeIssue_id", insertable = false, updatable = false)
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

	@Override
	public String toString() {
		return "StoreIssueProducts [storeIssueId=" + storeIssueId + ", productId=" + productId + ", quantity="
				+ quantity + ", storeIssues=" + storeIssues + "]";
	}
	
}
