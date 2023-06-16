package main.models.adminModels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products {
   @Id
	private int productId;
    private String productName;
    private int batchNo;
    private int stock;
    private double purchasePrice;
    private double salePrice;
    private String productCategory;
	public int getProductId() {
		return productId;
	}
	
	public Products(int productId, String productName, int batchNo, int stock, double purchasePrice,
			double salePrice,String productCategory) {
		this.productId = productId;
		this.productName = productName;
		this.batchNo = batchNo;
		this.stock = stock;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.productCategory=productCategory;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

    
}
