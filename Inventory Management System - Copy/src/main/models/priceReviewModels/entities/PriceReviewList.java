package main.models.priceReviewModels.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "im_priceReview")
public class PriceReviewList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pr_id")
	int priceReviewId;

	@Column(name = "pr_date")
	LocalDate priceReviewDate = LocalDate.now();

	@OneToMany(mappedBy = "prl")
	List<PriceReviewProductsList> productsList;

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

	public List<PriceReviewProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<PriceReviewProductsList> productsList) {
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "PriceReviewList [priceReviewId=" + priceReviewId + ", priceReviewDate=" + priceReviewDate + "]";
	}

}
