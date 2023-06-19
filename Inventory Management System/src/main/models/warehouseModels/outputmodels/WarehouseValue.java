package main.models.warehouseModels.outputmodels;

public class WarehouseValue {

	private Double product_cost;

	public Double getProduct_cost() {
		return product_cost;
	}

	public void setProduct_cost(Double product_cost) {
		this.product_cost = product_cost;
	}

	@Override
	public String toString() {
		return "WarehouseValue [product_cost=" + product_cost + "]";
	}

	public WarehouseValue(Double product_cost) {
		super();
		this.product_cost = product_cost;
	}

	public WarehouseValue() {
		super();
	}
	
}
