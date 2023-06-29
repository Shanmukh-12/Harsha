package main.bll.procurement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(GrnBll.class);

	@Autowired
	ProductsDAO productsDAO;

	/**
	 * Calculates the sale price of a product based on the cost price and profit percentage.
	 *
	 * @param productPrice The product price information.
	 * @return The calculated sale price.
	 */
	public SalePrice getProductSalePrice(ProductPrice productPrice) {
		ProductsProductIdInputModel productsProductIdInputModel = new ProductsProductIdInputModel(
				productPrice.getProductId());
		logger.info("entered into the getProductSalePrice()");
		ProductProfit productProfit = productsDAO.getProfitPercentage(productsProductIdInputModel);
		double unitPrice = productPrice.getCostPrice() / productPrice.getQuantity();

		double salesPrice = unitPrice + ((unitPrice * productProfit.getProfitPercentage()) / 100);

		SalePrice salePrice = new SalePrice(salesPrice);
		logger.debug("Calculated sale price for product: productId={}, salePrice={}", productPrice.getProductId(),
				salePrice.getSalePrice());
		return salePrice;
	}

	/**
	 * Calculates the total amount for a GRN (Goods Received Note) based on the list of products received.
	 *
	 * @param grnInputList The GRN input list containing product information.
	 * @return The calculated GRN amount.
	 */
	public GrnAmount getGrnAmount(GrnInputList grnInputList) {
		logger.info("entered into the getGrnAmount()");
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();
		double amount = 0;
		for (GrnInputProductsList product : productsList) {
			amount += product.getTotalPrice();
		}
		GrnAmount grnAmount = new GrnAmount(amount);
		logger.info("Calculated GRN amount: amount={}", grnAmount.getAmount());
		return grnAmount;
	}
}
