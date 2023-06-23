package main.models.indentModels.entities;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "im_Indents")
public class IndentsList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indents_id")
	int indentID;

	@Column(name = "indents_date")
	LocalDate d = LocalDate.now();

	@Column(name = "indents_status")
	String indentsStatus = "Active";


	public int getIndentID() {
		return indentID;
	}

	public void setIndentID(int indentID) {
		this.indentID = indentID;
	}


	public LocalDate getD() {
		return d;
	}

	public void setD(LocalDate d) {
		this.d = d;
	}

	public String getIndentsStatus() {
		return indentsStatus;
	}

	public void setIndentsStatus(String indentsStatus) {
		this.indentsStatus = indentsStatus;
	}

	@Override
	public String toString() {
		return "ProcurementIndentsList [indentID=" + indentID + ", d=" + d + ", indentsStatus=" + indentsStatus + "]";
	}



}
