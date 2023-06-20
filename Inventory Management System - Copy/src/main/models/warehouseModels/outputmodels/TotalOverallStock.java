package main.models.warehouseModels.outputmodels;

import org.springframework.stereotype.Component;

@Component
public class TotalOverallStock {

	private int total_product_stock;

	public int getTotal_product_stock() {
		return total_product_stock;
	}

	public void setTotal_product_stock(int total_product_stock) {
		this.total_product_stock = total_product_stock;
	}

	public TotalOverallStock(int total_product_stock) {
		super();
		this.total_product_stock = total_product_stock;
	}

	public TotalOverallStock() {
		super();
	}


	
}