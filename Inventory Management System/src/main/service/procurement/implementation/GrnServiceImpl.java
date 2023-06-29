package main.service.procurement.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.procurement.GrnDAO;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.outputModels.ImGrnOutputModel;
import main.service.procurement.interfaces.GrnService;

@Component
public class GrnServiceImpl implements GrnService {

	// Logger for logging purposes
	private static final Logger logger = LoggerFactory.getLogger(GrnServiceImpl.class);

	// Autowired dependency for GrnDAO
	@Autowired
	GrnDAO grndao;

	@Override
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters grnInputFilter) {
		logger.info("entered into the getGrnList()");
		List<ImGrnOutputModel> grnList = null;

		// Check if vendor_id is specified in the input filter
		if (grnInputFilter.getVendor_id() != 0) {
			// Check if grnFromDate is specified in the input filter
			if (grnInputFilter.getGrnFromDate() != null) {
				// Retrieve GRN list by ID and From Date using GrnDAO
				grnList = grndao.getGrnListByIdFrom(grnInputFilter);
				logger.debug("Retrieved GRN list by ID and From Date: grnCount={}", grnList.size());
			} else {
				// Retrieve GRN list by ID using GrnDAO
				grnList = grndao.getGrnListById(grnInputFilter);
				logger.info("Retrieved GRN list by ID: grnCount={}", grnList.size());
			}
		} else {
			// Check if grnFromDate is specified in the input filter
			if (grnInputFilter.getGrnFromDate() != null) {
				// Retrieve GRN list by From Date using GrnDAO
				grnList = grndao.getGrnListByFrom(grnInputFilter);
				logger.info("Retrieved GRN list by From Date: grnCount={}", grnList.size());
			} else {
				// Retrieve GRN list by To Date using GrnDAO
				grnList = grndao.getGrnListByTo(grnInputFilter);
				logger.info("Retrieved GRN list by To Date: grnCount={}", grnList.size());
			}
		}

		// Return the retrieved GRN list
		return grnList;
	}
}
