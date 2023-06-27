package main.models.productModels.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name = "product_image")
	private String productImage;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "product_category_id")
	private int category;

	@Column(name = "product_hsn_code")
	private int productHsnCode;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_reorder_level")
	private int productReorderLevel;

	@Column(name = "ecommerce_reorder_level")
	private int ecommerceReorderLevel;

	@Column(name = "product_status")
	private String productStatus;

	@Column(name = "profit_percentage")
	private double profitPercentage=0;

	// Fields from im_Products_Stock table
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ProductStock> productStocks;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getProfitPercentage() {
		return profitPercentage;
	}

	public void setProfitPercentage(double profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getProductHsnCode() {
		return productHsnCode;
	}

	public void setProductHsnCode(int productHsnCode) {
		this.productHsnCode = productHsnCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getProductReorderLevel() {
		return productReorderLevel;
	}

	public void setProductReorderLevel(int productReorderLevel) {
		this.productReorderLevel = productReorderLevel;
	}

	public int getEcommerceReorderLevel() {
		return ecommerceReorderLevel;
	}

	public void setEcommerceReorderLevel(int ecommerceReorderLevel) {
		this.ecommerceReorderLevel = ecommerceReorderLevel;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public List<ProductStock> getProductStocks() {
		return productStocks;
	}

	public void setProductStocks(List<ProductStock> productStocks) {
		this.productStocks = productStocks;
	}

}
