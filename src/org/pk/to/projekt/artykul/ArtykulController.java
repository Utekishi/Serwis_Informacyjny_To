package org.pk.to.projekt.artykul;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.pk.to.projekt.uzytkownik.Uzytkownik;

@ManagedBean(name = "artykulController", eager = true)
@RequestScoped
public class ArtykulController implements Serializable {

	private static final long serialVersionUID = 1L;


	public int idUzytkownika = -1;
	public Artykul artykyulZFormNowego = new Artykul();

	public ArtykulController() {
		Uzytkownik uzytkownikSesji = new Uzytkownik();
		FacesContext context = FacesContext.getCurrentInstance();
		uzytkownikSesji = (Uzytkownik) context.getExternalContext().getSessionMap().get("uzytkownikSesji");
		idUzytkownika = uzytkownikSesji.getId();
	}

	public String getZapisz() {
		return setArtykul(artykyulZFormNowego);
	}

	private String setArtykul(Artykul pArtykul){
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    Connection con = ArtykulBean.getConnection();
		String wynik;
	/*    String stm = "INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta, Data_Utworzenia) VALUES(?, ?, ?, ?, 1, 1, NOW());";
	    try {   
	        pst = con.prepareStatement(stm);
	        pst.setString(1,pUzytkownik.imie);
	        pst.setString(2,pUzytkownik.nazwisko);
	        pst.setString(3,pUzytkownik.login);
	        pst.setString(4,pUzytkownik.haslo);
	        pst.execute();
	  
	    } catch (SQLException e) {
	       e.printStackTrace();
	       wynik="Blad podczas dodawania do bazy danych";
	    }
	    */
	    wynik="Zarejestrowano poprwanie";
	    return wynik;
	 }

	public Artykul getArtykyulZFormNowego() {
		return artykyulZFormNowego;
	}

	public void setArtykyulZFormNowego(Artykul artykyulZFormNowego) {
		this.artykyulZFormNowego = artykyulZFormNowego;
	}



}