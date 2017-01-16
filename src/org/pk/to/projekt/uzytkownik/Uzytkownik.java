package org.pk.to.projekt.uzytkownik;

import java.sql.Date;

public class Uzytkownik {
	private int id;
	private String imie;
	private String nazwisko;
	private String login;
	private String haslo;
	private int typKonta = -1;
	private int statusKonta;
	private Date dataUtworzenia;
	private Date dataZbanowania;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public int getTypKonta() {
		return typKonta;
	}

	public void setTypKonta(int typKonta) {
		this.typKonta = typKonta;
	}

	public int getStatusKonta() {
		return statusKonta;
	}

	public void setStatusKonta(int statusKonta) {
		this.statusKonta = statusKonta;
	}

	public Date getDataUtworzenia() {
		return dataUtworzenia;
	}

	public void setDataUtworzenia(Date dataUtworzenia) {
		this.dataUtworzenia = dataUtworzenia;
	}

	public Date getDataZbanowania() {
		return dataZbanowania;
	}

	public void setDataZbanowania(Date dataZbanowania) {
		this.dataZbanowania = dataZbanowania;
	}

	@Override
	public String toString() {
		return "Uzytkownik [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", login=" + login + ", haslo="
				+ haslo + ", typKonta=" + typKonta + ", statusKonta=" + statusKonta + ", dataUtworzenia="
				+ dataUtworzenia + ", dataZbanowania=" + dataZbanowania + "]";
	}

}
