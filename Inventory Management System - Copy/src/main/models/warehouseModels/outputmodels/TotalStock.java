package main.models.warehouseModels.outputmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="im_products_stock")
public class TotalStock {
    @Id
	@Column(name = "product_stock", table = "im_products_stock")
	private Integer product_stock;

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(Integer product_stock) {
		this.product_stock = product_stock;
	}

	public TotalStock() {
		super();
	}

	public TotalStock(Integer product_stock) {
		super();
		this.product_stock = product_stock;
	}
	
}
