package main.models.storeModels;

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

	@Override
	public String toString() {
		return "StoreIndentsList [indentID=" + indentID + ", storeID=" + storeID + ",\n productsList=" + productsList
				+ "]";
	}

}
