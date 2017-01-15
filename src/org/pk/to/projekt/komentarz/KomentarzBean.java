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
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "komentarzBean", eager = true)
@SessionScoped
public class KomentarzBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Komentarz> getKomentarze() {
		System.err.println(" List<Komentarz> getKomentarze(");
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = getConnection();
		String stm = "Select * from komentarze";
		List<Komentarz> records = new ArrayList<Komentarz>();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();

			while (rs.next()) {
				Komentarz komentarz = new Komentarz();
				komentarz.setId(rs.getInt(1));
				komentarz.setTresc(rs.getClob(2));
				komentarz.setAutor(rs.getInt(3));
				komentarz.setDataUtworzenia(rs.getDate(4));

				records.add(komentarz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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