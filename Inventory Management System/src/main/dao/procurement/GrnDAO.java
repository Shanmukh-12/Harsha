package main.dao.procurement;

import java.util.List;

import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.inputModels.GrnIdInput;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.inputModels.GrnInputList;
import main.models.grnModels.outputModels.GrnListProductsOutputModel;
import main.models.grnModels.outputModels.ImGrnOutputModel;

public interface GrnDAO {

	/**
	 * Saves the GRN (Goods Received Note) entity to the database.
	 *
	 * @return true if the saving is successful, false otherwise.
	 */
	public boolean saveGrn(ImGrn imGrn);

	/**
	 * Updates the stock based on the received products in the GRN.
	 *
	 * @param grnInputList The GRN input list containing product information.
	 */
	public void updateStock(GrnInputList grnInputList);

	/**
	 * Updates the purchase order based on the received products in the GRN.
	 *
	 */
	public void updatePurchaseOrder(GrnInputList grnInputList);

	/**
	 * Retrieves a list of GRNs (Goods Received Notes) based on the specified filters.
	 *
	 * @return The list of GRNs matching the filters.
	 */
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters g);

	/**
	 * Retrieves a list of products included in a specific GRN (Goods Received Note).
	 *
	 * @return The list of products included in the GRN.
	 */
	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput gid);
}
