package main.models.storeReturnsModels.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="im_store_returns")
public class StoreReturnsData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="store_return_id")
	int returnId;
	
	@Column(name="store_return_date")
	LocalDate date;
	
	@Column(name="storeissue_id")
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
		return "StoreReturnsList [returnId=" + returnId + ", date=" + date + ", storeIssueId=" + storeIssueId
				+ "]";
	}
	
}
