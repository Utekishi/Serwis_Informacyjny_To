package org.pk.to.projekt.artykul;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.pk.to.projekt.uzytkownik.Uzytkownik;
import org.pk.to.projekt.uzytkownik.UzytkownikBean;

@ManagedBean(name = "artykulBean", eager = true)
@SessionScoped
public class ArtykulBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Artykul wybranyArtykul, artykulAutora;
	private List<Artykul> wybraneArtykuly; 
	private List<Artykul> artykulyAutora; 
	private FacesContext context = FacesContext.getCurrentInstance();
	int kategoriaId=0;
		
	@ManagedProperty("#{uzytkownikBean}")
	private UzytkownikBean uzytkownikBeanSession;
	


	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		String url = "jdbc:mariadb://localhost/serwis_informacyjny_to";
		String user = "root";
		String password = "";
		try {
			con = DriverManager.getConnection(url, user, password);
		//	System.err.println("Connection Artykul");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
		}
		return con;
	}

	public Artykul getWybranyArtykul() {
		return wybranyArtykul;
	}

	public void setWybranyArtykul(Artykul wybranyArtykul) {
		this.wybranyArtykul = wybranyArtykul;
	}
	
	public Artykul getArtykulAutora() {
		return artykulAutora;
	}

	public void setArtykulAutora(Artykul artykulAutora) {
		this.artykulAutora = artykulAutora;
	}

	public List<Artykul> getWybraneArtykuly() {
		return wybraneArtykuly;
	}

	public void setWybraneArtykuly(List<Artykul> wybraneArtykuly) {
		this.wybraneArtykuly = wybraneArtykuly;
	}

	public UzytkownikBean getUzytkownikBeanSession() {
		return uzytkownikBeanSession;
	}

	public void setUzytkownikBeanSession(UzytkownikBean uzytkownikBeanSession) {
		this.uzytkownikBeanSession = uzytkownikBeanSession;
	}

	public List<Artykul> getArtykulyAutora() {
		return artykulyAutora;
	}

	public void setArtykulyAutora(List<Artykul> artykulyAutora) {
		this.artykulyAutora = artykulyAutora;
	}
	
	
	

}