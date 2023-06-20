package main.bll.procurement;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import main.models.warehouseModels.dtomodels.*;
import main.models.warehouseModels.outputmodels.*;

@Component
public class TotalStockBLL {

	public int getTotalStockQuantity(ArrayList<TotalStock> s) {
		int sum = 0;
		for (TotalStock x : s) {
			sum += x.getProduct_stock();

		}
		return sum;
	}

}
