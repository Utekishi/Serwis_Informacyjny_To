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

	public List<Reklama> getReklamy() {
		List<Reklama> records = new ArrayList<Reklama>();

		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getExternalContext().getSessionMap().containsKey("wybranyArtykulId")) {

			int artykulId = (int) context.getExternalContext().getSessionMap().get("wybranyArtykulId");

			System.err.println(" List<Komentarz> getKomentarze(");
			ResultSet rs = null;
			PreparedStatement pst = null;
			Connection con = getConnection();
			String stm = "Select * from reklamy";

			try {
				pst = con.prepareStatement(stm);
				pst.setInt(1, artykulId);
				pst.execute();
				rs = pst.getResultSet();

				while (rs.next()) {
					Reklama reklama = new Reklama();
					reklama.setId(rs.getInt(1));
					reklama.setData_dodania(rs.getDate(2));
					reklama.setDostawca(rs.getString(3));
					reklama.setLink(rs.getString(3));
					reklama.setKoszt(rs.getInt(4));

					records.add(reklama);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return records;
	}

	public Connection getConnection() {
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
			System.err.println("Connection Komentarz");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
		}
		return con;
	}
}