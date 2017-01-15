package org.pk.to.projekt.artykul;

import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;

public class Artykul {
	private int id;
	private int autor;
	private String tytul;
	private Clob tresc;
	private String trescJakoString= "";
	private int status;
	private int kategoria;
	private String obrazek;
	private Date dataPublikacji;

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

	public Clob getTresc() {
		return tresc;
	}

	public void setTresc(Clob tresc) {
		this.tresc = tresc;
	}
	
	public int getStatus() {
		return status;
	}

	public String getTrescJakoString() {
		return trescJakoString;
	}

	public void setTrescJakoString(String trescJakoString) {
		this.trescJakoString = trescJakoString;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getDataPublikacji() {
		return dataPublikacji;
	}

	public void setDataPublikacj(Date dataPublikacj) {
		this.dataPublikacji = dataPublikacj;
	}

}
