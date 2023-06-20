package main.models.storeModels.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="im_Store_Indents")
public class StoreIndentData {

	@Column(name="Store_ID")
	int storeId;
	@Id
	@Column(name="Indents_ID")
	int indentId;
	@Column(name="Indents_Date")
	LocalDate date = LocalDate.now();
	@Column(name="Indents_Status")
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
