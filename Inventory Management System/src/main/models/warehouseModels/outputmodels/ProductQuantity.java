package main.models.warehouseModels.outputmodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.productModels.entities.ProductsStockEntity;

@Component
@Scope("prototype")
public class ProductQuantity {
	private int product_stock;

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}

	public ProductQuantity(ProductsStockEntity s) {
		super();
		this.product_stock = s.getProduct_stock();
	}

	@Override
	public String toString() {
		return "productquant [product_stock=" + product_stock + "]";
	}

	public ProductQuantity() {
		super();
	}

}
