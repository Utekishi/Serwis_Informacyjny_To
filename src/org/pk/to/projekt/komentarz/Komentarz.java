package org.pk.to.projekt.komentarz;

import java.sql.Clob;
import java.sql.Date;

public class Komentarz {
	int id;
	Clob tresc;
	int autorId;
	int artykulId;
	
	Date dataUtworzenia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clob getTresc() {
		return tresc;
	}

	public void setTresc(Clob tresc) {
		this.tresc = tresc;
	}

	public Date getDataUtworzenia() {
		return dataUtworzenia;
	}

	public void setDataUtworzenia(Date dataUtworzenia) {
		this.dataUtworzenia = dataUtworzenia;
	}

	public int getAutorId() {
		return autorId;
	}

	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}

	public int getArtykulId() {
		return artykulId;
	}

	public void setArtykulId(int artykulId) {
		this.artykulId = artykulId;
	}
	
	

}