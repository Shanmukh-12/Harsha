package main.models.grnModels.inputModels;

import java.time.LocalDate;

public class GrnInputFilters {
	private int vendor_id;
	private double grn_amount;

	private String grnFromDate = null;
	private String grnToDate = null;

	public GrnInputFilters() {
		super();
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public double getGrn_amount() {
		return grn_amount;
	}

	public void setGrn_amount(double grn_amount) {
		this.grn_amount = grn_amount;
	}

	public String getGrnFromDate() {
		return grnFromDate;
	}

	public void setGrnFromDate(String grnFromDate) {
		if (!grnFromDate.isEmpty())
			this.grnFromDate = grnFromDate;
	}

	public String getGrnToDate() {
		return String.valueOf(LocalDate.now());

	}

	public void setGrnToDate(String grnToDate) {
		if (!grnToDate.isEmpty())
			this.grnToDate = grnToDate;
	}

	public GrnInputFilters(int vendor_id, double grn_amount, String grnFromDate, String grnToDate) {
		super();
		this.vendor_id = vendor_id;
		this.grn_amount = grn_amount;
		this.grnFromDate = grnFromDate;
		this.grnToDate = grnToDate;
	}

	@Override
	public String toString() {
		return "GrnInputFilters [vendor_id=" + vendor_id + ", grn_amount=" + grn_amount + ", grnFromDate=" + grnFromDate
				+ ", grnToDate=" + grnToDate + "]";
	}

}
