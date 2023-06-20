package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.storeIssues.StoreIssueDao;
import main.models.storeIssueModels.entities.StoreIssues;
import main.models.storeIssueModels.outputModels.StoreIssueId;
import main.models.storeModels.inputmodels.StoreId;

@Controller
public class StoreIssuesDataController {

	@Autowired
	StoreIssueDao storeIssueDal;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/getStoreIssueIds")
	public @ResponseBody List<StoreIssueId> getStoreIssueIds(StoreId sid,Model m)
	{
		System.out.println("store Id is : "+sid.getStoreId());
		List<StoreIssues> data = storeIssueDal.getStoreIds(sid);
		List<StoreIssueId> res = new ArrayList<>();
		for(StoreIssues s :data)
			res.add(modelMapper.map(data,StoreIssueId.class));
		return res;
	}
}
