package main.models.storeModels.inputmodels;

import java.time.LocalDate;

public class StoreFilters {

	int storeId;
	String indentStatus;
	LocalDate fromDate;
	LocalDate toDate;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getIndentStatus() {
		return indentStatus;
	}
	public void setIndentStatus(String indentStatus) {
		this.indentStatus = indentStatus;
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
		return "StoreFilters [storeId=" + storeId + ", indentStatus=" + indentStatus + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	
	
}
