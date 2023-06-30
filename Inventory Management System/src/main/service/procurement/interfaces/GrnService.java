package main.service.procurement.interfaces;

import java.util.List;

import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.outputModels.ImGrnOutputModel;

public interface GrnService {

	// This interface defines the contract for a GRN (Goods Received Note) service.

	/**
	 * Retrieves a list of GRN output models based on the provided input filters.
	 *
	 * @param grnInputFilter The input filters to apply for retrieving GRN list.
	 * @return A list of GRN output models that match the specified filters.
	 */
	List<ImGrnOutputModel> getGrnList(GrnInputFilters grnInputFilter);
}
