package main.dao.adjustments;

import java.util.List;

import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsFilterInput;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;
import main.models.adjustmentsModels.outputModels.AdjustmentProductsListData;
import main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput;

public interface AdjustmentsDAO {

	public List<AdjustmentsList> getAdjustments();

	public boolean saveAdjustments(AdjustmentsList adjustmentsList);

	public List<AdjustmentProductsListData> getAdjustmentProductsList(AdjustmentsInputList adjustmentid);

	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductIdFrom(
			AdjustmentsFilterInput adjustmentsFilterInput);

	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductId(
			AdjustmentsFilterInput adjustmentsFilterInput);

	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdFrom(AdjustmentsFilterInput adjustmentsFilterInput);

	public List<AdjustmentsFilterOutput> getFilterDataByCategoryId(AdjustmentsFilterInput adjustmentsFilterInput);

	public List<AdjustmentsFilterOutput> getFilterDataByFrom(AdjustmentsFilterInput adjustmentsFilterInput);

	public List<AdjustmentsFilterOutput> getFilterDataByTo(AdjustmentsFilterInput adjustmentsFilterInput);

}
