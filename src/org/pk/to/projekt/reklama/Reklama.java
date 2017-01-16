package org.pk.to.projekt.reklama;

import java.sql.Date;

public class Reklama {

	private int id;
	private Date data_dodania;
	private String dostawca;
	private String link;
	private int koszt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_dodania() {
		return data_dodania;
	}

	public void setData_dodania(Date data_dodania) {
		this.data_dodania = data_dodania;
	}

	public String getDostawca() {
		return dostawca;
	}

	public void setDostawca(String dostawca) {
		this.dostawca = dostawca;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getKoszt() {
		return koszt;
	}

	public void setKoszt(int koszt) {
		this.koszt = koszt;
	}

}