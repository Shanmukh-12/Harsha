package main.models.storeModels.entities;

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
@Table(name = "im_Store_Indents")
public class StoreIndentsList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indents_id")
	int indentID;

	@Column(name = "store_id")
	int storeID;

	@Column(name = "indents_date")
	LocalDate d = LocalDate.now();

	@Column(name = "indents_status")
	String indentsStatus = "Active";

	@OneToMany(mappedBy = "sil")
	List<StoreIndentProductsList> productsList;

	public int getIndentID() {
		return indentID;
	}

	public void setIndentID(int indentID) {
		this.indentID = indentID;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public List<StoreIndentProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<StoreIndentProductsList> productsList) {
		this.productsList = productsList;
	}

	public LocalDate getD() {
		return d;
	}

	public void setD(LocalDate d) {
		this.d = d;
	}

	public String getIndentsStatus() {
		return indentsStatus;
	}

	public void setIndentsStatus(String indentsStatus) {
		this.indentsStatus = indentsStatus;
	}

	@Override
	public String toString() {
		return "StoreIndentsList [indentID=" + indentID + ", storeID=" + storeID + ", d=" + d + ", indentsStatus="
				+ indentsStatus + ",\n productsList=" + productsList + "]";
	}

}
