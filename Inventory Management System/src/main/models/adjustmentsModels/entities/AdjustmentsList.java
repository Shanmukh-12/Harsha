package main.models.adjustmentsModels.entities;

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
@Table(name = "im_adjustments")
public class AdjustmentsList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adjs_id")
	int adjustmentID;

	@Column(name = "adjs_date")
	LocalDate adjustmentDate = LocalDate.now();

	@OneToMany(mappedBy = "al")
	List<AdjustmentsProductsList> productsList;

	public int getAdjustmentID() {
		return adjustmentID;
	}

	public void setAdjustmentID(int adjustmentID) {
		this.adjustmentID = adjustmentID;
	}

	public LocalDate getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(LocalDate adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

	public List<AdjustmentsProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<AdjustmentsProductsList> productsList) {
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "AdjustmentsList [adjustmentID=" + adjustmentID + ", adjustmentDate=" + adjustmentDate
				+ ", productsList=" + productsList + "]";
	}

}
