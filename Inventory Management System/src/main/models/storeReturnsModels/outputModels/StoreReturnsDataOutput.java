package main.models.storeReturnsModels.outputModels;

import java.time.LocalDate;

public class StoreReturnsDataOutput {
	int returnId;

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

	@Override
	public String toString() {
		return "StoreReturnsList [returnId=" + returnId + ", date=" + date + ", storeIssueId=" + storeIssueId + "]";
	}

}
