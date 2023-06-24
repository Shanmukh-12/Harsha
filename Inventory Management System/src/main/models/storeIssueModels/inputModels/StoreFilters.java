package main.models.storeIssueModels.inputModels;

import java.time.LocalDate;

public class StoreFilters {

	int storeId;
	String storeIssueStatus;
	LocalDate fromDate;
	LocalDate toDate;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreIssueStatus() {
		return storeIssueStatus;
	}

	public void setStoreIssueStatus(String storeIssueStatus) {
		this.storeIssueStatus = storeIssueStatus;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "StoreFilters [storeId=" + storeId + ", storeIssueStatus=" + storeIssueStatus + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}

}
