package org.pk.to.projekt.artykul;

import java.sql.Date;

public class Artykul {
	private int id;
	private int autor;
	private String tytul;
	private String tresc = "";
	private int status_artykulu;
	private int kategoria;
	private String obrazek;
	private Date data_publikacji;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = autor;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public int getStatus_artykulu() {
		return status_artykulu;
	}

	public void setStatus_artykulu(int status_artykulu) {
		this.status_artykulu = status_artykulu;
	}

	public int getKategoria() {
		return kategoria;
	}

	public void setKategoria(int kategoria) {
		this.kategoria = kategoria;
	}

	public String getObrazek() {
		return obrazek;
	}

	public void setObrazek(String obrazek) {
		this.obrazek = obrazek;
	}

	public Date getData_publikacji() {
		return data_publikacji;
	}

	public void setData_publikacji(Date data_publikacji) {
		this.data_publikacji = data_publikacji;
	}

}
