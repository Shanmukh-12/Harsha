package main.models.storeIndentModels.outputmodels;

import java.time.LocalDate;

public class StoreIndentDataOutput {

	int storeId;
	int indentId;
	LocalDate date = LocalDate.now();
	String indentStatus;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getIndentId() {
		return indentId;
	}

	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getIndentStatus() {
		return indentStatus;
	}

	public void setIndentStatus(String indentStatus) {
		this.indentStatus = indentStatus;
	}

	@Override
	public String toString() {
		return "StoreIndentData [storeId=" + storeId + ", indentId=" + indentId + ", date=" + date + ", indentStatus="
				+ indentStatus + "]";
	}
}