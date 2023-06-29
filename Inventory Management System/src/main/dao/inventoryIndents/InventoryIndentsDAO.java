package main.dao.inventoryIndents;

import java.util.List;

import main.models.indentModels.entities.InventoryIndentsList;
import main.models.indentModels.inputModels.FilterInput;
import main.models.indentModels.outputModels.FilteredIndent;
import main.models.indentModels.outputModels.InventoryIndentProductListData;
import main.models.storeModels.inputmodels.IndentId;

public interface InventoryIndentsDAO {

	public boolean saveInventoryIndent(InventoryIndentsList inventoryIndentsList) throws Exception;
	public List<InventoryIndentsList> getAllIndents() throws Exception ;
	
	public List<InventoryIndentProductListData> getInventoryIndentProductsList(IndentId indentid) throws Exception;
	public List<FilteredIndent> getfilterIndents(FilterInput filterInput) throws Exception;
}