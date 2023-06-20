package main.models.productModels.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="im_Products_Category")
public class ProductsCategory {
     @Id
     @Column(name=" Product_Category_ID")
     private int productCategoryId;
     @Column(name=" Product_Category_Name")
     private String productCategoryName;
     
     
	public ProductsCategory() {
	}
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
	@Override
	public String toString() {
		return "ProductsCategory [productCategoryId=" + productCategoryId + ", productCategoryName="
				+ productCategoryName + "]";
	}
     
}
