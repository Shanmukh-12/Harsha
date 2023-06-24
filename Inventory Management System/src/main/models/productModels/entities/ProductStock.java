package main.models.productModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_Products_Stock")
public class ProductStock implements Serializable {

	@Id
	@Column(name = "product_Id")
	private int productId;

	@Id
	@Column(name = "Batch_NO")
	private int batchNo;

	@Column(name = "Product_Stock")
	private int productStock;

	@Column(name = "Product_Sale_Price")
	private double productSalePrice;

	// unit price
	@Column(name = "Product_Cost")
	private double productCost;

	@Column(name = "Product_MRP")
	private double productMrp;

	@ManyToOne
	@JoinColumn(name = "Product_ID", referencedColumnName = "Product_id", insertable = false, updatable = false)
	private Products product;

	public ProductStock() {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public double getProductSalePrice() {
		return productSalePrice;
	}

	public void setProductSalePrice(double productSalePrice) {
		this.productSalePrice = productSalePrice;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public double getProductMrp() {
		return productMrp;
	}

	public void setProductMrp(double productMrp) {
		this.productMrp = productMrp;
	}

	@Override
	public String toString() {
		return "ProductStock [productId=" + productId + ", batchNo=" + batchNo + ", productStock=" + productStock
				+ ", productSalePrice=" + productSalePrice + ", productCost=" + productCost + ", productMrp="
				+ productMrp + ", product=" + product + "]";
	}
}
