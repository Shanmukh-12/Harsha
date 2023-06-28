package main.dao.adjustments;

import java.util.List;

import main.dal.adjustments.AdjustmentsException;
import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsFilterInput;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;
import main.models.adjustmentsModels.outputModels.AdjustmentProductsListData;
import main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput;

public interface AdjustmentsDAO {

	// The getAdjustments method is used to retrieve a list of all the Adjustments
	public List<AdjustmentsList> getAdjustments() throws AdjustmentsException;

	/*
	 * The saveAdjustments method is responsible for persisting adjustments details (product category, product name,
	 * batch no, original stock, updated stock and reason) to the database
	 */
	public boolean saveAdjustments(AdjustmentsList adjustmentsList) throws AdjustmentsException;

	// The getAdjustmentProductsList method is for displaying the list of all the products in each Adjustment ID
	public List<AdjustmentProductsListData> getAdjustmentProductsList(AdjustmentsInputList adjustmentid)
			throws AdjustmentsException;

	// This method filters Adjustment ID's by product category Id, product Id and From date
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductIdFrom(
			AdjustmentsFilterInput adjustmentsFilterInput) throws AdjustmentsException;

	// This method filters Adjustment ID's by product category Id and product Id
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductId(
			AdjustmentsFilterInput adjustmentsFilterInput) throws AdjustmentsException;

	// This method filters Adjustment ID's by product category Id and From Date
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdFrom(AdjustmentsFilterInput adjustmentsFilterInput)
			throws AdjustmentsException;

	// This method filters Adjustment ID's by product category Id
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryId(AdjustmentsFilterInput adjustmentsFilterInput)
			throws AdjustmentsException;

	// This method filters Adjustment ID's by From date
	public List<AdjustmentsFilterOutput> getFilterDataByFrom(AdjustmentsFilterInput adjustmentsFilterInput)
			throws AdjustmentsException;

	// This method filters Adjustment ID's by To date
	public List<AdjustmentsFilterOutput> getFilterDataByTo(AdjustmentsFilterInput adjustmentsFilterInput)
			throws AdjustmentsException;

}
