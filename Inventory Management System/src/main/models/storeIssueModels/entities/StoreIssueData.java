package main.models.storeIssueModels.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_storeissues")
public class StoreIssueData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "storeissue_id")
	int storeIssueId;

	@Column(name = "store_Id")
	int storeId;

	@Column(name = "storeIssue_date")
	LocalDate date = LocalDate.now();

	@Column(name = "amount")
	double amount;

	@Column(name = "storeissue_status")
	String status;

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
		return "StoreIssueIds [storeIssueId=" + storeIssueId + ", storeId=" + storeId + ", date=" + date + ", amount="
				+ amount + ", status=" + status + "]";
	}
}
