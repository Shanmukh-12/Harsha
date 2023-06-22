package main.dao.procurement;

import java.util.List;

import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.inputModels.GrnIdInput;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.inputModels.ProductInfoMapping;
import main.models.grnModels.outputModels.GrnListProductsOutputModel;
import main.models.grnModels.outputModels.ImGrnOutputModel;

public interface GrnDAO {

	public boolean saveGrn(ImGrn imGrn);

	public void updateStock(ProductInfoMapping pi);

	public List<ImGrnOutputModel> getGrnList(GrnInputFilters g);

	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput gid);

}
