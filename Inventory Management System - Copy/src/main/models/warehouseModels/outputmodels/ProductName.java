package main.models.warehouseModels.outputmodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.productModels.entities.im_products;

@Component
@Scope("prototype")
public class ProductName {

	private String product_name;

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public ProductName(im_products s) {
		super();
		this.product_name = s.getProduct_name();
	}

	@Override
	public String toString() {
		return "ProductName [product_name=" + product_name + "]";
	}

	public ProductName() {
		super();
	}

}
