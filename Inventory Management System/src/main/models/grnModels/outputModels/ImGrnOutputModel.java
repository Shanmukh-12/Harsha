package main.models.grnModels.outputModels;

import java.time.LocalDate;

public class ImGrnOutputModel {

	private int grnId;
	private int vendor_id;

	private int purchase_order_id;
	private LocalDate grnDate;
	private double grnAmount;

	public ImGrnOutputModel() {
		super();
	}

	public ImGrnOutputModel(int grnId, int vendor_id, int purchase_order_id, LocalDate grnDate, double grnAmount) {
		super();
		this.grnId = grnId;
		this.vendor_id = vendor_id;
		this.purchase_order_id = purchase_order_id;
		this.grnDate = grnDate;
		this.grnAmount = grnAmount;
	}

	public int getGrnId() {
		return grnId;
	}

	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public LocalDate getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(LocalDate grnDate) {
		this.grnDate = grnDate;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public int getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(int purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public double getGrnAmount() {
		return grnAmount;
	}

	public void setGrnAmount(double grnAmount) {
		this.grnAmount = grnAmount;
	}

	@Override
	public String toString() {
		return "ImGrnOutputModel [grnId=" + grnId + ", vendor_id=" + vendor_id + ", purchase_order_id="
				+ purchase_order_id + ", grnDate=" + grnDate + ", grnAmount=" + grnAmount + "]";
	}

}
