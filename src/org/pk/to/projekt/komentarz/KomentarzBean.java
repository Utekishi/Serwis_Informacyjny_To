package org.pk.to.projekt.komentarz;

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

import org.pk.to.projekt.artykul.ArtykulBean;

@ManagedBean(name = "komentarzBean", eager = true)
@SessionScoped
public class KomentarzBean implements Serializable {

	private static final long serialVersionUID = 1L;
	List<Komentarz> wybraneKomentarze;

	
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
		//	System.err.println("Connection Komentarz");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
		}
		return con;
	}

	public List<Komentarz> getWybraneKomentarze() {
		return wybraneKomentarze;
	}

	public void setWybraneKomentarze(List<Komentarz> wybraneKomentarze) {
		this.wybraneKomentarze = wybraneKomentarze;
	}

	

}