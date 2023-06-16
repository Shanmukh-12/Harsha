package main.dao.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import main.models.statisticsModels.Products;

@Component
public class StatisticsDao {

	public List<Products> getProducts() {
		List<Products> p = new ArrayList<>();
		p.add(new Products("phone", 100));
		p.add(new Products("biscuits", 200));
		p.add(new Products("chocolate", 250));
		p.add(new Products("charger", 10));
		p.add(new Products("kurkure", 150));
		p.add(new Products("phone", 100));
		p.add(new Products("biscuits", 200));
		p.add(new Products("chocolate", 250));
		p.add(new Products("charger", 10));
		p.add(new Products("kurkure", 150));
		return p;
	}

}
