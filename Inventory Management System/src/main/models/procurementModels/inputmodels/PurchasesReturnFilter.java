package main.models.procurementModels.inputmodels;

import java.math.BigDecimal;

public class PurchasesReturnFilter {

	private String purchase_return_date = null;
	private String purchase_return_date1 = null;;
	private int vendor_id = 0;
	private double grn_value=0;

	

	public String getPurchase_return_date() {
		return purchase_return_date;
	}

	public void setPurchase_return_date(String purchase_return_date) {
		if(purchase_return_date!="")
		this.purchase_return_date = purchase_return_date;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		
		this.vendor_id = vendor_id;
	}

	public double getGrn_value() {
		return grn_value;
	}

	public void setGrn_value(double grn_value) {
		
		this.grn_value = grn_value;
	}

	

	

	public PurchasesReturnFilter() {
		super();
	}

	public String getPurchase_return_date1() {
		
		return purchase_return_date1;
	}

	public void setPurchase_return_date1(String purchase_return_date1) {
		if(purchase_return_date1!="")

		this.purchase_return_date1 = purchase_return_date1;
	}

	public PurchasesReturnFilter(String purchase_return_date, String purchase_return_date1, int vendor_id,
			double grn_value) {
		super();
		this.purchase_return_date = purchase_return_date;
		this.purchase_return_date1 = purchase_return_date1;
		this.vendor_id = vendor_id;
		this.grn_value = grn_value;
	}

	@Override
	public String toString() {
		return "PurchasesReturnFilter [purchase_return_date=" + purchase_return_date + ", purchase_return_date1="
				+ purchase_return_date1 + ", vendor_id=" + vendor_id + ", grn_value=" + grn_value + "]";
	}



	

}
