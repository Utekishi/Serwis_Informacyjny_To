package org.pk.to.projekt.uzytkownik;

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

@ManagedBean(name = "uzytkownikBean", eager = true)
@SessionScoped
public class UzytkownikBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Uzytkownik> getUzytkownicy() {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = getConnection();
		String stm = "Select * from uzytkownik";
		List<Uzytkownik> records = new ArrayList<Uzytkownik>();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();

			while (rs.next()) {
				Uzytkownik uzytkownik = new Uzytkownik();
				uzytkownik.setId(rs.getInt(1));
				uzytkownik.setImie(rs.getString(2));
				uzytkownik.setNazwisko(rs.getString(3));
				uzytkownik.setLogin(rs.getString(4));
				uzytkownik.setHaslo(rs.getString(5));
				uzytkownik.setTypKonta(rs.getInt(6));
				uzytkownik.setStatusKonta(rs.getInt(7));
				uzytkownik.setDataUtworzenia(rs.getDate(8));
				uzytkownik.setDataZbanowania(rs.getDate(9));
				records.add(uzytkownik);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
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
			System.err.println("Connection Uzytkownik");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
		}
		return con;
	}
}