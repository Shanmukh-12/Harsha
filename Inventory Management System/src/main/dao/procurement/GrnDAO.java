package main.dao.procurement;

import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.inputModels.ProductInfoMapping;

public interface GrnDAO {

	public boolean saveGrn(ImGrn imGrn);
	public void updateStock(ProductInfoMapping pi);

}
