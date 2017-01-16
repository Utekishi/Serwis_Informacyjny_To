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
import org.pk.to.projekt.uzytkownik.UzytkownikBean;
import org.pk.to.projekt.uzytkownik.UzytkownikController;

@ManagedBean(name = "artykulController", eager = true)
@RequestScoped



public class ArtykulController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{artykulBean}")
	private ArtykulBean artykulBeanSession;
	
	@ManagedProperty("#{uzytkownikBean}")
	private UzytkownikBean uzytkownikBeanSession;

	public String loginUzytkownika = "";
	public Artykul artykulZFormNowego = new Artykul();
	private FacesContext context = FacesContext.getCurrentInstance();
	
	
	public ArtykulController() {

	}

	public String getZapisz() {
		return setArtykul(artykulZFormNowego);
	}
	
	public void pobierzArtykuly(int kategoriaId) {
			ResultSet rs = null;
			PreparedStatement pst = null;
			Connection con = ArtykulBean.getConnection();

			String stm = "Select * from artykul";
			if(kategoriaId>0)
				stm+=" where kategoria = ?";
			
			List<Artykul> records = new ArrayList<Artykul>();
			try {
				pst = con.prepareStatement(stm);
				if(kategoriaId>0)
				pst.setInt(1, kategoriaId);	
				pst.execute();
				rs = pst.getResultSet();

				while (rs.next()) {
					Artykul artykul = new Artykul();
					artykul.setId(rs.getInt(1));
					artykul.setAutor(rs.getString(2));
					artykul.setTytul(rs.getString(3));
					artykul.setTresc(rs.getString(4));
					artykul.setStatus_artykulu(rs.getInt(5));
					artykul.setKategoria(rs.getInt(6));
					artykul.setObrazek(rs.getString(7));
					artykul.setData_publikacji(rs.getDate(8));
					records.add(artykul);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.err.println("kategoriaId: " + kategoriaId);
			System.err.println("Zapytanie: " + stm);
			System.err.println("Pobrane artyku³y: " + records);
			artykulBeanSession.setWybraneArtykuly(records);
		}
	
	public void pobierzArtykul(int artykulId) {
		FacesContext context = FacesContext.getCurrentInstance();


			ResultSet rs = null;
			PreparedStatement pst = null;
			Connection con = UzytkownikBean.getConnection();
			String stm = "Select * from artykul where id = ? ";
			
			try {
				pst = con.prepareStatement(stm);
				pst.setInt(1, artykulId);
				pst.execute();
				rs = pst.getResultSet();
				Artykul artykul = new Artykul();
				if (rs.next()) {
					artykul.setId(rs.getInt(1));
					artykul.setAutor(rs.getString(2));
					artykul.setTytul(rs.getString(3));
					artykul.setTresc(rs.getString(4));
					artykul.setStatus_artykulu(rs.getInt(5));
					artykul.setKategoria(rs.getInt(6));
					artykul.setObrazek(rs.getString(7));
					artykul.setData_publikacji(rs.getDate(8));
				}
				artykulBeanSession.setWybranyArtykul(artykul);;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	


	}
	
	public void pobierzArtykulyAutora() {
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = ArtykulBean.getConnection();

		String stm = "Select * from artykul where autor =?";
		
		
		List<Artykul> records = new ArrayList<Artykul>();
		try {
			pst = con.prepareStatement(stm);
	
			pst.setInt(1, uzytkownikBeanSession.getUzytkownikZalogowany().getId());	
			pst.execute();
			rs = pst.getResultSet();

			while (rs.next()) {
				Artykul artykul = new Artykul();
				artykul.setId(rs.getInt(1));
				artykul.setAutor(rs.getString(2));
				artykul.setTytul(rs.getString(3));
				artykul.setTresc(rs.getString(4));
				artykul.setStatus_artykulu(rs.getInt(5));
				artykul.setKategoria(rs.getInt(6));
				artykul.setObrazek(rs.getString(7));
				artykul.setData_publikacji(rs.getDate(8));
				records.add(artykul);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("AutorId: " + uzytkownikBeanSession.getUzytkownikZalogowany().getId());
		System.err.println("Zapytanie: " + stm);
		System.err.println("Pobrane artyku³y: " + records);
		artykulBeanSession.setWybraneArtykuly(records);

	}
	

	private String setArtykul(Artykul pArtykul){
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    Connection con = ArtykulBean.getConnection();
		String wynik;
	    String stm = "INSERT INTO Artykul(Autor, Tytul, Tresc, Status_artykulu, Kategoria, Obrazek, Data_Publikacji) VALUES(?, ?, ?, 1, ?, ?, NOW());";
	    try {   
	        pst = con.prepareStatement(stm);
	        pst.setString(1,uzytkownikBeanSession.getUzytkownikZalogowany().getLogin());
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

	public ArtykulBean getArtykulBeanSession() {
		return artykulBeanSession;
	}

	public void setArtykulBeanSession(ArtykulBean artykulBeanSession) {
		this.artykulBeanSession = artykulBeanSession;
	}

	public UzytkownikBean getUzytkownikBeanSession() {
		return uzytkownikBeanSession;
	}

	public void setUzytkownikBeanSession(UzytkownikBean uzytkownikBeanSession) {
		this.uzytkownikBeanSession = uzytkownikBeanSession;
	}



}