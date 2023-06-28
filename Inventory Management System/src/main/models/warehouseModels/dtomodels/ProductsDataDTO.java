package main.models.warehouseModels.dtomodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.models.productModels.entities.ProductsEntity;
import main.models.productModels.entities.ProductsStockEntity;


@Component
@Scope("prototype")
public class ProductsDataDTO {
	private ProductsStockEntity stock;
	private ProductsEntity product;
	public ProductsStockEntity getStock() {
		return stock;
	}
	public void setStock(ProductsStockEntity stock) {
		this.stock = stock;
	}
	public ProductsEntity getProduct() {
		return product;
	}
	public void setProduct(ProductsEntity product) {
		this.product = product;
	}
	public ProductsDataDTO(ProductsStockEntity stock, ProductsEntity product) {
		super();
		this.stock = stock;
		this.product = product;
	}
	public ProductsDataDTO() {
		super();
	}
	

}
