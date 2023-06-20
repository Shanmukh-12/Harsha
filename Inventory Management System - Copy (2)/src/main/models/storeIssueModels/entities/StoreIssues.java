package main.models.storeIssueModels.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import main.models.purchaseOrder.entityModels.Im_Purchase_Order_Products;

@Entity
@Table(name="im_storeissues")
public class StoreIssues {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="storeissue_id")
	int storeIssueId;
	
	@Column(name="store_Id")
	int storeId;
	
	@Column(name="storeIssue_date")
	LocalDate date = LocalDate.now();
	
	@Column(name="amount")
	double amount;
	
	@Column(name="storeissue_status")
	String status;

	@OneToMany(mappedBy = "storeIssues", fetch = FetchType.LAZY)
	private List<StoreIssueProducts> storeProducts;

	public List<StoreIssueProducts> getStoreProducts() {
		return storeProducts;
	}

	public void setStoreProducts(List<StoreIssueProducts> storeProducts) {
		this.storeProducts = storeProducts;
	}

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StoreIssues [storeIssueId=" + storeIssueId + ", date=" + date + ", amount=" + amount + ", status="
				+ status + ", storeProducts=" + storeProducts + "]";
	}
	
	
}
