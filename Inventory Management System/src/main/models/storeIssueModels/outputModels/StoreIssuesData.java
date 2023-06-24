package main.models.storeIssueModels.outputModels;

import java.time.LocalDate;

public class StoreIssuesData {
	int storeIssueId;
	LocalDate storeIssueDate;
	double amount;
	String storeIssueStatus;
	int storeId;

	public StoreIssuesData() {
		super();
	}

	public StoreIssuesData(int storeIssueId, LocalDate storeIssueDate, double amount, String storeIssueStatus,
			int storeId) {
		super();
		this.storeIssueId = storeIssueId;
		this.storeIssueDate = storeIssueDate;
		this.amount = amount;
		this.storeIssueStatus = storeIssueStatus;
		this.storeId = storeId;
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

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "StoreIssuesData [storeIssueId=" + storeIssueId + ", storeIssueDate=" + storeIssueDate + ", amount="
				+ amount + ", storeIssueStatus=" + storeIssueStatus + ", storeId=" + storeId + "]";
	}

}
