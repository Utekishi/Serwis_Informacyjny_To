package org.pk.to.projekt.komentarz;

import java.sql.Date;

public class Komentarz {
	private int id;
	private String tresc;
	private String autorLogin;
	private int artykulId;

	Date dataUtworzenia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Date getDataUtworzenia() {
		return dataUtworzenia;
	}

	public void setDataUtworzenia(Date dataUtworzenia) {
		this.dataUtworzenia = dataUtworzenia;
	}

	public String getAutorLogin() {
		return autorLogin;
	}

	public void setAutorLogin(String autorLogin) {
		this.autorLogin = autorLogin;
	}

	public int getArtykulId() {
		return artykulId;
	}

	public void setArtykulId(int artykulId) {
		this.artykulId = artykulId;
	}

	@Override
	public String toString() {
		return "Komentarz [id=" + id + ", tresc=" + tresc + ", autorLogin=" + autorLogin + ", artykulId=" + artykulId
				+ ", dataUtworzenia=" + dataUtworzenia + "]";
	}
	
	

}