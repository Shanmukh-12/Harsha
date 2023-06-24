package main.bll.procurement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.products.ProductsDAO;
import main.models.grnModels.dto.GrnAmount;
import main.models.grnModels.inputModels.GrnInputList;
import main.models.grnModels.inputModels.GrnInputProductsList;
import main.models.productModels.dto.ProductPrice;
import main.models.productModels.dto.ProductProfit;
import main.models.productModels.dto.SalePrice;
import main.models.productModels.inputModels.ProductsProductIdInputModel;

@Component
public class GrnBll {

	@Autowired
	ProductsDAO pd;

	public SalePrice getProductSalePrice(ProductPrice pp) {

		ProductsProductIdInputModel p = new ProductsProductIdInputModel(pp.getProductId());
		ProductProfit productProfit = pd.getProfitPercentage(p);
		double unitPrice = pp.getCostPrice() / pp.getQuantity();

		double salePrice = unitPrice + ((unitPrice * productProfit.getProfitPercentage()) / 100);

		SalePrice s = new SalePrice(salePrice);
		return s;

	}

	public GrnAmount getGrnAmount(GrnInputList grnInputList) {
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();
		double amount = 0;
		for (GrnInputProductsList product : productsList)
			amount += product.getTotalPrice();
		GrnAmount g = new GrnAmount(amount);
		return g;
	}
}
