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


	public String loginUzytkownika = "";
	public Artykul artykulZFormNowego = new Artykul();

	public ArtykulController() {
		Uzytkownik uzytkownikSesji = new Uzytkownik();
		FacesContext context = FacesContext.getCurrentInstance();
		uzytkownikSesji = (Uzytkownik) context.getExternalContext().getSessionMap().get("uzytkownikSesji");
		loginUzytkownika = uzytkownikSesji.getLogin();
	}

	public String getZapisz() {
		return setArtykul(artykulZFormNowego);
	}

	private String setArtykul(Artykul pArtykul){
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    Connection con = ArtykulBean.getConnection();
		String wynik;
	    String stm = "INSERT INTO Artykul(Autor, Tytul, Tresc, Status_artykulu, Kategoria, Obrazek, Data_Publikacji) VALUES(?, ?, ?, 1, ?, ?, NOW());";
	    try {   
	        pst = con.prepareStatement(stm);
	        pst.setString(1,loginUzytkownika);
	        pst.setString(2,pArtykul.getTytul());
	        pst.setString(3,pArtykul.getTresc());
	        pst.setInt(4,pArtykul.getKategoria());
	        pst.setString(5,pArtykul.getObrazek());
	        pst.execute();
	  
	    } catch (SQLException e) {
	       e.printStackTrace();
	       wynik="Blad podczas dodawania do bazy danych";
	    }
	    
	    wynik="Dodano poprawnie";
	    return wynik;
	 }

	public Artykul getArtykulZFormNowego() {
		return artykulZFormNowego;
	}

	public void setArtykulZFormNowego(Artykul artykulZFormNowego) {
		this.artykulZFormNowego = artykulZFormNowego;
	}



}