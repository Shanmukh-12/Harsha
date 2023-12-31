package main.models.warehouseModels.outputmodels;

import org.springframework.stereotype.Component;

@Component
public class TotalWarehouseVal {

	private double total_warehouse_value;

	public double getTotal_warehouse_value() {
		return total_warehouse_value;
	}

	public void setTotal_warehouse_value(Long total_warehouse_value) {
		this.total_warehouse_value = total_warehouse_value.doubleValue();
	}

	public TotalWarehouseVal(Long total_warehouse_value) {
		super();
		this.total_warehouse_value = total_warehouse_value.doubleValue();
	}

	public TotalWarehouseVal() {
		super();
	}

	@Override
	public String toString() {
		return "TotalWarehouseVal [total_warehouse_value=" + total_warehouse_value + "]";
	}
	
}
