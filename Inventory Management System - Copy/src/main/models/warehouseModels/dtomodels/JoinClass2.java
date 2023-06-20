package main.models.warehouseModels.dtomodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.warehouseModels.dtomodels.*;

import main.models.warehouseModels.outputmodels.*;
@Component
@Scope("prototype")

public class JoinClass2 {
	private productquant stock;
	private ProductName product;

	public productquant getStock() {
		return stock;
	}

	public void setStock(productquant stock) {
		this.stock = stock;
	}

	public ProductName getProduct() {
		return product;
	}

	public void setProduct(ProductName product) {
		this.product = product;
	}

	public JoinClass2(productquant stock, ProductName product) {
		super();
		this.stock = stock;
		this.product = product;
	}

	public JoinClass2() {
		super();
	}

}
