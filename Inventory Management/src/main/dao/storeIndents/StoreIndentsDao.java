package main.dao.storeIndents;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.models.storeModels.StoreIndentsList;

@Component
public class StoreIndentsDao {

	@Autowired
	EntityManager entityManager;
	public boolean saveStoreIndent(StoreIndentsList sil)
	{
		System.out.println("Inside storeIndentsDao");
		StoreIndentsList list = entityManager.merge(sil);
		System.out.println(list);
		return true;
	}
}
