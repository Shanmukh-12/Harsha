package main.models.productModels.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "im_Products")
public class Products {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    private int productId;

    @Column(name = "Product_Name")
    private String productName;

    @Column(name = "product_category_id")
    private int category;
    
	// Fields from im_Products_Stock table
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductStock> productStocks;


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public Products() {
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}




	public List<ProductStock> getProductStocks() {
		return productStocks;
	}


	public void setProductStocks(List<ProductStock> productStocks) {
		this.productStocks = productStocks;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


    

    
    
}
