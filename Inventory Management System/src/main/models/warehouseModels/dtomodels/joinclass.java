package main.models.warehouseModels.dtomodels;

import main.models.productModels.entities.im_products;
import main.models.productModels.entities.im_products_stock;

public class joinclass {
	private im_products_stock stock;
	private im_products product;

	public im_products_stock getStock() {
		return stock;
	}

	public void setStock(im_products_stock stock) {
		this.stock = stock;
	}

	public im_products getProduct() {
		return product;
	}

	public void setProduct(im_products product) {
		this.product = product;
	}

	public joinclass(im_products_stock stock, im_products product) {
		super();
		this.stock = stock;
		this.product = product;
	}

	public joinclass() {
		super();
	}

}
