package main.dal.procurement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import main.dao.procurement.GrnDAO;
import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.entities.ImGrnProducts;
import main.models.grnModels.inputModels.GrnIdInput;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.inputModels.ProductInfo;
import main.models.grnModels.inputModels.ProductInfoMapping;
import main.models.grnModels.outputModels.GrnListProductsOutputModel;
import main.models.grnModels.outputModels.ImGrnOutputModel;
import main.models.productModels.entities.im_products_stock;

@Component
public class GrnDAOImpl implements GrnDAO {

	@PersistenceContext
	EntityManager manager;

	@Transactional
	public boolean saveGrn(ImGrn imGrn) {
		manager.persist(imGrn);

		List<ImGrnProducts> l = imGrn.getProductsList();
		System.out.println(l);
		for (ImGrnProducts i : l) {
			System.out.println(i);
			System.out.println(imGrn.getGrnId());
			i.setGrn_id(imGrn.getGrnId());

			manager.persist(i);
		}
		return true;
	}

	@Transactional
	public void updateStock(ProductInfoMapping pi) {

		List<ProductInfo> l = pi.getProductsList();

		for (ProductInfo r : l) {
			try {
				im_products_stock ips = (im_products_stock) manager
						.createQuery("select s from im_products_stock s where s.product_id=:x and s.batch_no=:y")
						.setParameter("x", r.getProduct_id()).setParameter("y", r.getBatch_no()).getSingleResult();
				ips.setProduct_stock(ips.getProduct_stock() + r.getProduct_stock());

			}

			catch (Exception e) {

				ModelMapper mp = new ModelMapper();
				im_products_stock is = (im_products_stock) mp.map(r, im_products_stock.class);
				System.out.println(is.toString());
				manager.persist(is);
			}
		}

	}

	@Transactional
	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput gid) {
		ModelMapper mapper = new ModelMapper();
		// public GrnListProductsOutputModel(int grn_id, int product_id, int batch_no, int quantity, double price, int
		// bonus,
		// int totalQuantity)
		List<GrnListProductsOutputModel> a = new ArrayList<>();
		ImGrn imGrn = (ImGrn) manager.createQuery("select s from ImGrn s where s.grnId=:x ")
				.setParameter("x", gid.getGrnId()).getSingleResult();

		List<ImGrnProducts> l = imGrn.getProductsList();
		for (ImGrnProducts i : l) {
			System.out.println(i.toString());
			GrnListProductsOutputModel io = mapper.map(i, GrnListProductsOutputModel.class);
			io.setTotalQuantity(i.getQuantity() + i.getBonus());
			a.add(io);
		}
		return a;
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters g) {
		System.out.println(
				g.getGrn_amount() + " " + g.getGrnFromDate() + " " + g.getGrnToDate() + " " + g.getVendor_id());

		// System.out.println(g.toString());
		System.out.println(g.getGrnToDate());
		if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0.0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("1");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchase_order_id=p.purchase_order_id where s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("2");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchase_order_id=p.purchase_order_id WHERE p.vendor_id =:v and s.grnDate<=:d",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("3");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchase_order_id=p.purchase_order_id  WHERE p.vendor_id = :v AND s.grnDate >= :d and s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("4");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchase_order_id=p.purchase_order_id  where p.vendor_id = :v AND s.grnDate>= :d and s.grnDate<=:t AND s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("5");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchase_order_id=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate >=:d AND s.grnAmount = :a AND s.grnDate <= :t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", g.getGrnToDate()).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("6");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchase_order_id=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate<=: and s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id()).setParameter("a", g.getGrn_amount())
					.getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("6");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchase_order_id=p.purchase_order_id WHERE s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("a", g.getGrn_amount()).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else {
			System.out.println("7");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModel.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchase_order_id, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchase_order_id=p.purchase_order_id where s.grnDate>=:v and s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("v", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		}
	}
}
