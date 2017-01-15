package org.pk.to.projekt.komentarz;

import java.sql.Clob;
import java.sql.Date;

public class Komentarz {
	int id;
	Clob tresc;
	int autor;

	public int getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = autor;
	}

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

}