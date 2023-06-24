package main.models.storeIssueModels.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_storeIssues")
public class StoreIssue {
	@Id
	@Column(name = "storeissue_id")
	int storeIssueId;
	@Column(name = "storeissue_date")
	LocalDate storeIssueDate;
	@Column(name = "amount")
	double amount;
	@Column(name = "storeissue_status")
	String storeIssueStatus;
	@Column(name = "last_updated_user")
	String lastUpdatedUser;
	@Column(name = "last_updated_date")
	Date lastUpdatedDate;
	@Column(name = "store_id")
	int storeId;

	public StoreIssue() {
		super();
	}

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public LocalDate getStoreIssueDate() {
		return storeIssueDate;
	}

	public void setStoreIssueDate(LocalDate storeIssueDate) {
		this.storeIssueDate = storeIssueDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStoreIssueStatus() {
		return storeIssueStatus;
	}

	public void setStoreIssueStatus(String storeIssueStatus) {
		this.storeIssueStatus = storeIssueStatus;
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

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "StoreIssue [storeIssueId=" + storeIssueId + ", storeIssueDate=" + storeIssueDate + ", amount=" + amount
				+ ", storeIssueStatus=" + storeIssueStatus + ", lastUpdatedUser=" + lastUpdatedUser
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", storeId=" + storeId + "]";
	}

}
