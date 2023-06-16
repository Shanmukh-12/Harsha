package main.models.adminModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductsCategory {
     @Id
     private int productCategoryId;
     private String productCategoryName;
     
	public ProductsCategory(int productCategoryId, String productCategoryName) {
		
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
	}
	public int getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
     
}
