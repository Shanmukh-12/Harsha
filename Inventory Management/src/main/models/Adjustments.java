package main.models.adjustmentModels;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Adjustments")

public class Adjustments {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adjs_id;
	private String adjs_date;
	private String adjs_desc;
	
	public Adjustments()
	{
		
	}
	
	public int getAdjs_id() {
		return adjs_id;
	}
	public void setAdjs_id(int adjs_id) {
		this.adjs_id = adjs_id;
	}
	public String getAdjs_date() {
		return adjs_date;
	}
	public void setAdjs_date(String adjs_date) {
		this.adjs_date = adjs_date;
	}
	public String getAdjs_desc() {
		return adjs_desc;
	}
	public void setAdjs_desc(String adjs_desc) {
		this.adjs_desc = adjs_desc;
	}
	
	

	
	

}
