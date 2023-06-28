package main.service.procurement.interfaces;

import java.util.List;

import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.outputModels.ImGrnOutputModel;

public interface GrnService {

	List<ImGrnOutputModel> getGrnList(GrnInputFilters grnInputFilter);
}
