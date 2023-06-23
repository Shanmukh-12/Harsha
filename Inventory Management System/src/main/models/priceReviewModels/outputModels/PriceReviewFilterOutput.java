package main.models.priceReviewModels.outputModels;

import java.time.LocalDate;

public class PriceReviewFilterOutput {

	private int pr_id;
	private LocalDate pr_date;

	public PriceReviewFilterOutput(int pr_id, LocalDate pr_date) {
		super();
		this.pr_id = pr_id;
		this.pr_date = pr_date;
	}

	public int getPr_id() {
		return pr_id;
	}

	public void setPr_id(int pr_id) {
		this.pr_id = pr_id;
	}

	public LocalDate getPr_date() {
		return pr_date;
	}

	public void setPr_date(LocalDate pr_date) {
		this.pr_date = pr_date;
	}

	@Override
	public String toString() {
		return "PriceReviewFilterOutput [pr_id=" + pr_id + ", pr_date=" + pr_date + "]";
	}

}
