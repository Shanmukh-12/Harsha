package main.models.adminModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_products_category")
public class ProductsCategory {
     @Id
     @Column(name = "product_category_id")
     private int productCategoryId;
     
     @Column(name = "product_category_name")
     private String productCategoryName;
     
     

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
