package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.statistics.StatisticsDao;
import main.models.statisticsModels.Products;

@Controller
public class StockStatistics {

	@Autowired
	StatisticsDao statisticsDao;

	@PostMapping("getWareHouseData")
	public @ResponseBody List<Products> getdata() {
		return statisticsDao.getProducts();
	}

}