package org.pk.to.projekt.reklama;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "reklamaBean", eager = true)
@SessionScoped
public class ReklamaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reklama wybranaReklama;

	public String getPobierzReklamy() {

	
			ResultSet rs = null;
			PreparedStatement pst = null;
			Connection con = getConnection();
			String stm = "Select * from reklamy;";

			try {
				pst = con.prepareStatement(stm);
				pst.execute();
				rs = pst.getResultSet();

				while (rs.next()) {
					Reklama reklama = new Reklama();
					reklama.setId(rs.getInt(1));
					reklama.setData_dodania(rs.getDate(2));
					reklama.setDostawca(rs.getString(3));
					reklama.setLink(rs.getString(4));
					reklama.setKoszt(rs.getInt(5));

					wybranaReklama = reklama;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	//	System.err.println("wybrana reklama: " + wybranaReklama);
		return "";
	}

	public Reklama getWybranaReklama() {
		return wybranaReklama;
	}

	public void setWybranaReklama(Reklama wybranaReklama) {
		this.wybranaReklama = wybranaReklama;
	}

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
		
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
		}
		return con;
	}
}