package org.pk.to.projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="indexBean")
@SessionScoped
public class IndexBean {
	
private String welcomeText = "Witaj Œwiat";
private String inputText;
private String imie;
private String nazwisko;
private int plec;
private String kolor;
private List<String> wybrItems; 
private List<String> dostItems;
	
	public String getWelcomeText() {
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText) {
		this.welcomeText = welcomeText;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
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

	public int getPlec() {
		return plec;
	}

	public void setPlec(int plec) {
		this.plec = plec;
	}

	public String getKolor() {
		return kolor;
	}

	public void setKolor(String kolor) {
		this.kolor = kolor;
	}

	public List<String> getWybrItems() {
		return wybrItems;
	}

	public void setWybrItems(List<String> wybrItems) {
		this.wybrItems = wybrItems;
	}

	public List<String> getDostItems() {
		return dostItems;
	}

	public void setDostItems(List<String> dostItems) {
		this.dostItems = dostItems;
	}
	


	@PostConstruct
	public void init() {
		dostItems = Arrays.asList("Herbate", "Miecze", "Kable", "Smoki", "Poduszki");
	}

	
	
	

}
