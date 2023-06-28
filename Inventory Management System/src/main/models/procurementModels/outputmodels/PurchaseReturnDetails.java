package main.models.procurementModels.outputmodels;


import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseReturnDetails {
    private int purchase_return_id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")

    private Date purchase_return_date;
    private int grn_no;
    private BigDecimal grn_cost;
    private int vendor_id;
    private String purchase_return_description;
	public int getPurchase_return_id() {
		return purchase_return_id;
	}
	public void setPurchase_return_id(int purchase_return_id) {
		this.purchase_return_id = purchase_return_id;
	}
	public Date getPurchase_return_date() {
		return purchase_return_date;
	}
	public void setPurchase_return_date(Date purchase_return_date) {
		this.purchase_return_date = purchase_return_date;
	}
	public int getGrn_no() {
		return grn_no;
	}
	public void setGrn_no(int grn_no) {
		this.grn_no = grn_no;
	}
	public BigDecimal getGrn_cost() {
		return grn_cost;
	}
	public void setGrn_cost(BigDecimal grn_cost) {
		this.grn_cost = grn_cost;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getPurchase_return_description() {
		return purchase_return_description;
	}
	public void setPurchase_return_description(String purchase_return_description) {
		this.purchase_return_description = purchase_return_description;
	}
	public PurchaseReturnDetails(int purchase_return_id, Date purchase_return_date, int grn_no, BigDecimal grn_cost,
			int vendor_id, String purchase_return_description) {
		super();
		this.purchase_return_id = purchase_return_id;
		this.purchase_return_date = purchase_return_date;
		this.grn_no = grn_no;
		this.grn_cost = grn_cost;
		this.vendor_id = vendor_id;
		this.purchase_return_description = purchase_return_description;
	}
	public PurchaseReturnDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PurchaseReturnOutput [purchase_return_id=" + purchase_return_id + ", purchase_return_date="
				+ purchase_return_date + ", grn_no=" + grn_no + ", grn_cost=" + grn_cost + ", vendor_id=" + vendor_id
				+ ", purchase_return_description=" + purchase_return_description + "]";
	}
	

   
}
