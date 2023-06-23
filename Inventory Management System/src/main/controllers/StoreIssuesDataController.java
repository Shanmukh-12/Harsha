package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.storeIssues.StoreIssueDao;
import main.models.storeIssueModels.entities.StoreIssueData;
import main.models.storeIssueModels.outputModels.StoreIssueIds;
import main.models.storeModels.inputmodels.StoreId;

@Controller
public class StoreIssuesDataController {

	@Autowired
	StoreIssueDao storeIssueDao;

	@Autowired
	ModelMapper modelMapper;

	@RequestMapping(value = "/getStoreIssueIds", method = RequestMethod.POST)
	public @ResponseBody List<StoreIssueIds> getStoreIssueIds(StoreId storeId, Model m) {
		System.out.println("Inside");
		List<StoreIssueData> data = storeIssueDao.getStoreIds(storeId);
		List<StoreIssueIds> res = new ArrayList<>();
		for (StoreIssueData s : data)
			res.add(modelMapper.map(s, StoreIssueIds.class));
		return res;
	}
}
