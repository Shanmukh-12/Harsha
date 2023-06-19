package main.models.warehouseModels.outputmodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.productModels.entities.im_products_stock;

@Component
@Scope("prototype")
public class productquant {
	private int product_stock;

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}

	public productquant(im_products_stock s) {
		super();
		this.product_stock = s.getProduct_stock();
	}

	@Override
	public String toString() {
		return "productquant [product_stock=" + product_stock + "]";
	}

	public productquant() {
		super();
	}

}
