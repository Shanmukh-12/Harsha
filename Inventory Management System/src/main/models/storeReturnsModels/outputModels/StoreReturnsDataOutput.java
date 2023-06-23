package main.models.storeReturnsModels.outputModels;

import java.time.LocalDate;

public class StoreReturnsDataOutput {
	int returnId;
	int storeId;
	LocalDate date = LocalDate.now();

	int storeIssueId;

	public int getReturnId() {
		return returnId;
	}

	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public StoreReturnsDataOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreReturnsDataOutput(int returnId, int storeId, LocalDate date, int storeIssueId) {
		super();
		this.returnId = returnId;
		this.storeId = storeId;
		this.date = date;
		this.storeIssueId = storeIssueId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "StoreReturnsDataOutput [returnId=" + returnId + ", storeId=" + storeId + ", date=" + date
				+ ", storeIssueId=" + storeIssueId + "]";
	}

}
