package main.models.priceReviewModels.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_priceReview")

public class PriceReviewFilter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pr_id")
	int priceReviewId;

	@Column(name = "pr_date")
	LocalDate priceReviewDate = LocalDate.now();

	public int getPriceReviewId() {
		return priceReviewId;
	}

	public void setPriceReviewId(int priceReviewId) {
		this.priceReviewId = priceReviewId;
	}

	public LocalDate getPriceReviewDate() {
		return priceReviewDate;
	}

	public void setPriceReviewDate(LocalDate priceReviewDate) {
		this.priceReviewDate = priceReviewDate;
	}

	@Override
	public String toString() {
		return "PriceReviewFilter [priceReviewId=" + priceReviewId + ", priceReviewDate=" + priceReviewDate + "]";
	}

}
