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
import javax.faces.context.FacesContext;

import org.pk.to.projekt.artykul.Artykul;

@ManagedBean(name = "uzytkownikBean", eager = true)
@SessionScoped
public class UzytkownikBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Uzytkownik uzytkownikPanelPokaz, uzytkownikPanelUstaw;
	private Uzytkownik uzytkownikZalogowany= null;
	public String informacjaNaglowek = "Brak sesji";
	public String panelNazwa = "Brak panelu";

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
	


	public String getPobierzUzytkownika() {
	
		int idUzytkownika = uzytkownikZalogowany.getId();
		//if (context.getExternalContext().getSessionMap().containsKey("artykulAutora")) {

			ResultSet rs = null;
			PreparedStatement pst = null;
			Connection con = UzytkownikBean.getConnection();
			String stm = "Select * from uzytkownik where Id = ? ";
			try {
				pst = con.prepareStatement(stm);
				pst.setInt(1, idUzytkownika);
				pst.execute();
				rs = pst.getResultSet();
				Uzytkownik uzytkownik = new Uzytkownik();
				if (rs.next()) {
					uzytkownik.setImie(rs.getString(2));
					uzytkownik.setNazwisko(rs.getString(3));
					uzytkownik.setLogin(rs.getString(4));
				}
				uzytkownikPanelPokaz = uzytkownik;

				//return "";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		//}

		return "";

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
		//	System.err.println("Connection Uzytkownik");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
		}
		return con;
	}
	
	public Uzytkownik getUzytkownikPanelPokaz() {
		return uzytkownikPanelPokaz;
	}

	public void setUzytkownikPanelPokaz(Uzytkownik uzytkownikPanelPokaz) {
		this.uzytkownikPanelPokaz = uzytkownikPanelPokaz;
	}

	public Uzytkownik getUzytkownikPanelUstaw() {
		return uzytkownikPanelUstaw;
	}

	public void setUzytkownikPanelUstaw(Uzytkownik uzytkownikPanelUstaw) {
		this.uzytkownikPanelUstaw = uzytkownikPanelUstaw;
	}



	public Uzytkownik getUzytkownikZalogowany() {
		return uzytkownikZalogowany;
	}



	public void setUzytkownikZalogowany(Uzytkownik uzytkownikZalogowany) {
		this.uzytkownikZalogowany = uzytkownikZalogowany;
	}



	public String getInformacjaNaglowek() {
		return informacjaNaglowek;
	}



	public void setInformacjaNaglowek(String informacjaNaglowek) {
		this.informacjaNaglowek = informacjaNaglowek;
	}



	public String getPanelNazwa() {
		return panelNazwa;
	}



	public void setPanelNazwa(String panelNazwa) {
		this.panelNazwa = panelNazwa;
	}
	
	
}