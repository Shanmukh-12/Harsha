package main.models.adjustmentsModels.outputModels;

import java.time.LocalDate;

public class AdjustmentsFilterOutput {

	private int adjs_id;
	private LocalDate adjs_date;

	public AdjustmentsFilterOutput(int adjs_id, LocalDate adjs_date) {
		super();
		this.adjs_id = adjs_id;
		this.adjs_date = adjs_date;
	}

	public int getAdjs_id() {
		return adjs_id;
	}

	public void setAdjs_id(int adjs_id) {
		this.adjs_id = adjs_id;
	}

	public LocalDate getAdjs_date() {
		return adjs_date;
	}

	public void setAdjs_date(LocalDate adjs_date) {
		this.adjs_date = adjs_date;
	}

	@Override
	public String toString() {
		return "AdjustmentsFilterOutput [adjs_id=" + adjs_id + ", adjs_date=" + adjs_date + "]";
	}

}
