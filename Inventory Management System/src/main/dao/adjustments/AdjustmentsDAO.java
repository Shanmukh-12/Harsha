package main.dao.adjustments;

import java.util.List;

import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;
import main.models.adjustmentsModels.outputModels.AdjustmentProductsListData;

public interface AdjustmentsDAO {

	public List<AdjustmentsList> getAdjustments();

	public boolean saveAdjustments(AdjustmentsList adjustmentsList);

	public List<AdjustmentProductsListData> getAdjustmentProductsList(AdjustmentsInputList adjustmentid);

}
