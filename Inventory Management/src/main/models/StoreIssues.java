package main.models.storeissuesModels;

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
@Table(name = "im_StoreIssues")
public class StoreIssues {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="StoreIssue_ID")
	private int storeIssueId;
	

	
	@Column(name="StoreIssue_Date")
	LocalDate d =  LocalDate.now();
	
	
	@Column(name="StoreIssue_Status")
	String storeIssueStatus = "Active";
	
	@OneToMany(mappedBy = "pil")
	List<StoreIssuesProducts> productsList;

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public LocalDate getD() {
		return d;
	}

	public void setD(LocalDate d) {
		this.d = d;
	}

	public String getStoreIssueStatus() {
		return storeIssueStatus;
	}

	public void setStoreIssueStatus(String storeIssueStatus) {
		this.storeIssueStatus = storeIssueStatus;
	}

	public List<StoreIssuesProducts> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<StoreIssuesProducts> productsList) {
		this.productsList = productsList;
	}
	
	


}
