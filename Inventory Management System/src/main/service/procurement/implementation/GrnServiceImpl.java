package main.service.procurement.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.procurement.GrnDAO;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.outputModels.ImGrnOutputModel;
import main.service.procurement.interfaces.GrnService;

@Component
public class GrnServiceImpl implements GrnService {

	@Autowired
	GrnDAO grndao;

	@Override
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters grnInputFilter) {
		List<ImGrnOutputModel> grnList = null;
		if (grnInputFilter.getVendor_id() != 0) {
			if (grnInputFilter.getGrnFromDate() != null) {
				return grndao.getGrnListByIdFrom(grnInputFilter);
			} else {
				return grndao.getGrnListById(grnInputFilter);
			}
		} else {
			if (grnInputFilter.getGrnFromDate() != null) {
				return grndao.getGrnListByFrom(grnInputFilter);
			} else {
				return grndao.getGrnListByTo(grnInputFilter);
			}
		}
	}

}
