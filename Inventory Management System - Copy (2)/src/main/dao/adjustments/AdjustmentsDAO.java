package main.dao.adjustments;

import java.util.List;

import main.models.adjustmentsModels.entities.AdjustmentsList;

public interface AdjustmentsDAO {

	public List<AdjustmentsList> getAdjustments();

	public boolean saveAdjustments(AdjustmentsList adjustmentsList);

}
