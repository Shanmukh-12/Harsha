package main.dal.procurement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import main.dao.procurement.GrnDAO;
import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.entities.ImGrnProducts;
import main.models.grnModels.inputModels.ProductInfo;
import main.models.grnModels.inputModels.ProductInfoMapping;
import main.models.productModels.entities.im_products_stock;

@Component
public class GrnDAOImpl implements GrnDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean saveGrn(ImGrn imGrn) {
		em.persist(imGrn);

		List<ImGrnProducts> l = imGrn.getProductsList();
		System.out.println(l);
		for (ImGrnProducts i : l) {
			System.out.println(i);
			System.out.println(imGrn.getGrnId());
			i.setGrn_id(imGrn.getGrnId());

			em.persist(i);
		}
		return true;
	}
	@Transactional
	public void updateStock(ProductInfoMapping pi)
	{
	
			List<ProductInfo> l=pi.getProductsList();
		
		for(ProductInfo r:l)
	  {
		try {
		im_products_stock ips=(im_products_stock) em.createQuery("select s from im_products_stock s where s.product_id=:x and s.batch_no=:y").setParameter("x", r.getProduct_id()).setParameter("y", r.getBatch_no()).getSingleResult();
		ips.setProduct_stock(ips.getProduct_stock()+r.getProduct_stock());

		}
		
		catch(Exception e) {
		
			ModelMapper mp=new ModelMapper();
			im_products_stock is=mp.map(r,im_products_stock.class);
			System.out.println(is.toString());
			em.persist(is);
	   }
		}
	

}
}
