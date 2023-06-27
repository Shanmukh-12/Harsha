package main.models.warehouseModels.dtomodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.warehouseModels.dtomodels.*;

import main.models.warehouseModels.outputmodels.*;
@Component
@Scope("prototype")

public class ProductDetailsDTO {
	private ProductQuantity stock;
	private ProductName product;

	public ProductQuantity getStock() {
		return stock;
	}

	public void setStock(ProductQuantity stock) {
		this.stock = stock;
	}

	public ProductName getProduct() {
		return product;
	}

	public void setProduct(ProductName product) {
		this.product = product;
	}

	public ProductDetailsDTO(ProductQuantity stock, ProductName product) {
		super();
		this.stock = stock;
		this.product = product;
	}

	public ProductDetailsDTO() {
		super();
	}


}
