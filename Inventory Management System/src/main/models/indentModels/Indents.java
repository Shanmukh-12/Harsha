package main.models.indentModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_indents")
public class Indents {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indents_id")
	private int indentId;
	@Column(name = "indents_date")
	private String indentDate;

	@Column(name = "indents_status")
	private String indentStatus;

	public Indents() {

	}

	public int getIndentId() {
		return indentId;
	}

	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}

	public String getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(String indentDate) {
		this.indentDate = indentDate;
	}

	public String getIndentStatus() {
		return indentStatus;
	}

	public void setIndentStatus(String indentStatus) {
		this.indentStatus = indentStatus;
	}

	@Override
	public String toString() {
		return "Indents [indentId=" + indentId + ", indentDate=" + indentDate + ", indentStatus=" + indentStatus + "]";
	}

}
