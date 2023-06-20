package main.models.procurementModels.outputmodels;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
public class ImPurchaseOrderOutput {
	
	private int purchase_order_id;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date purchase_order_date;
	
	private BigDecimal purchase_order_amount;
	
	private int vendor_id;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date purchase_order_expected_date ;
	
	

	public ImPurchaseOrderOutput(int purchase_order_id, Date purchase_order_date, BigDecimal purchase_order_amount,
			int vendor_id, Date purchase_order_expected_date) {
		super();
		this.purchase_order_id = purchase_order_id;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_amount = purchase_order_amount;
		this.vendor_id = vendor_id;
		this.purchase_order_expected_date = purchase_order_expected_date;
		
	}

	public ImPurchaseOrderOutput() {
		super();
	}

	public int getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(int purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public Date getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(Date purchase_order_date) {
		this.purchase_order_date = purchase_order_date;
	}

	public BigDecimal getPurchase_order_amount() {
		return purchase_order_amount;
	}

	public void setPurchase_order_amount(BigDecimal purchase_order_amount) {
		this.purchase_order_amount = purchase_order_amount;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public Date getPurchase_order_expected_date() {
		return purchase_order_expected_date;
	}

	public void setPurchase_order_expected_date(Date purchase_order_expected_date) {
		this.purchase_order_expected_date = purchase_order_expected_date;
	}

	@Override
	public String toString() {
		return "ImPurchaseOrderOutput [purchase_order_id=" + purchase_order_id + ", purchase_order_date="
				+ purchase_order_date + ", purchase_order_amount=" + purchase_order_amount + ", vendor_id=" + vendor_id
				+ ", purchase_order_expected_date=" + purchase_order_expected_date + "]";
	}

	

	

	

}


