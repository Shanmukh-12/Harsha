package main.models.warehouseModels.outputmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;



public class TotalStockv2 {
    
	private Integer product_stock;

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(Integer product_stock) {
		this.product_stock = product_stock;
	}

	public TotalStockv2() {
		super();
	}

	public TotalStockv2(Integer product_stock) {
		super();
		this.product_stock = product_stock;
	}
	
}
