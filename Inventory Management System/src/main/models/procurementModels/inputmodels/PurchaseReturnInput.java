package main.models.procurementModels.inputmodels;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.models.purchaseReturns.entityModels.PurchaseReturnProducts;


@JsonIgnoreProperties(ignoreUnknown = true)

public class PurchaseReturnInput {
	private String vendor_id;
	private String grn_no;
	private BigDecimal grn_cost;
	private String purchase_return_description;

	public BigDecimal getGrn_cost() {
		return grn_cost;
	}

	public void setGrn_cost(BigDecimal grn_cost) {
		this.grn_cost = grn_cost;
	}

	private List<PurchaseReturnProducts> purchaseReturnProducts;

	// Constructors, getters, and setters

	public PurchaseReturnInput() {
	}

	@Override
	public String toString() {
		return "PurchaseReturnInput [vendor_id=" + vendor_id + ", grn_no=" + grn_no + ", grn_cost=" + grn_cost
				+ ", purchase_return_description=" + purchase_return_description + ", purchaseReturnProducts="
				+ purchaseReturnProducts + "]";
	}

	public PurchaseReturnInput(String vendor_id, String grn_no, BigDecimal grn_cost, String purchase_return_description,
			List<PurchaseReturnProducts> purchaseReturnProducts) {
		super();
		this.vendor_id = vendor_id;
		this.grn_no = grn_no;
		this.grn_cost = grn_cost;
		this.purchase_return_description = purchase_return_description;
		this.purchaseReturnProducts = purchaseReturnProducts;
	}

	public String getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getGrn_no() {
		return grn_no;
	}

	public void setGrn_no(String grn_no) {
		this.grn_no = grn_no;
	}

	public List<PurchaseReturnProducts> getPurchaseReturnProducts() {
		return purchaseReturnProducts;
	}

	public void setPurchaseReturnProducts(List<PurchaseReturnProducts> purchaseReturnProducts) {
		this.purchaseReturnProducts = purchaseReturnProducts;
	}

	public String getPurchase_return_description() {
		return purchase_return_description;
	}

	public void setPurchase_return_description(String purchase_return_description) {
		this.purchase_return_description = purchase_return_description;
	}

}
