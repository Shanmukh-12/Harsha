package main.models.adjustmentsModels.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_adjustments")
public class AdjustmentsFilter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adjs_id")
	int adjustmentID;

	@Column(name = "adjs_date")
	LocalDate adjustmentDate = LocalDate.now();

	public int getAdjustmentID() {
		return adjustmentID;
	}

	@Override
	public String toString() {
		return "AdjustmentsFilter [adjustmentID=" + adjustmentID + ", adjustmentDate=" + adjustmentDate + "]";
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

}