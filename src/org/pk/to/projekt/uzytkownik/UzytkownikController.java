package org.pk.to.projekt.uzytkownik;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.pk.to.projekt.artykul.ArtykulBean;

@ManagedBean(name = "uzytkownikController", eager = true)
@RequestScoped
public class UzytkownikController implements Serializable {

	private static final long serialVersionUID = 1L;

	

	public Date czyZbanowany = null;
	public Uzytkownik uzytkownikZFormLogowania = new Uzytkownik();
	public Uzytkownik uzytkownikZFormRejestrowania = new Uzytkownik();
	public Uzytkownik uzytkownikZUpdate = new Uzytkownik();
	public Uzytkownik uzytkownikBan = new Uzytkownik();
	
	@ManagedProperty("#{uzytkownikBean}")
	private UzytkownikBean uzytkownikBeanSession;



	public void zainicjalizuj(){
		
		if (uzytkownikBeanSession == null) 
			uzytkownikBeanSession=new UzytkownikBean();
		
			if (uzytkownikBeanSession.getUzytkownikZalogowany()==null) {	
			Uzytkownik uzytkownikNowy = new Uzytkownik();
			uzytkownikNowy.setTypKonta(0);
			uzytkownikBeanSession.setUzytkownikZalogowany(uzytkownikNowy);
			uzytkownikBeanSession.setInformacjaNaglowek("Witaj, goœæ");
			}

		System.err.println("Zalogowany: " + uzytkownikBeanSession.getUzytkownikZalogowany());
		if (uzytkownikBeanSession.getUzytkownikZalogowany().getTypKonta() == 0) {
			uzytkownikBeanSession.setInformacjaNaglowek("Witaj, goœæ");
		} else if (uzytkownikBeanSession.getUzytkownikZalogowany().getTypKonta() == 1) {
			uzytkownikBeanSession.setInformacjaNaglowek("Witaj u¿ytkowniku, " + uzytkownikBeanSession.getUzytkownikZalogowany().getImie());
			uzytkownikBeanSession.setPanelNazwa("Panel U¿ytkownika");
		} else if (uzytkownikBeanSession.getUzytkownikZalogowany().getTypKonta() == 2) {
			uzytkownikBeanSession.setInformacjaNaglowek("Witaj moderatorze, " + uzytkownikBeanSession.getUzytkownikZalogowany().getImie());
			uzytkownikBeanSession.setPanelNazwa("Panel Moderatora");
		} else if (uzytkownikBeanSession.getUzytkownikZalogowany().getTypKonta() == 3) {
			uzytkownikBeanSession.setInformacjaNaglowek("Witaj redaktorze, " + uzytkownikBeanSession.getUzytkownikZalogowany().getImie());
		} else if (uzytkownikBeanSession.getUzytkownikZalogowany().getTypKonta() == 4) {
			uzytkownikBeanSession.setInformacjaNaglowek("Witaj administratorze, " + uzytkownikBeanSession.getUzytkownikZalogowany().getImie());
			uzytkownikBeanSession.setPanelNazwa("Panel Administratora");
		}
		
	}


	public void zaloguj() {
		if (getSprawdzCzyUzytkonikIstnieje(uzytkownikZFormLogowania.getLogin(), uzytkownikZFormLogowania.getHaslo())) {
			uzytkownikBeanSession.setUzytkownikZalogowany(getUzytkownik(uzytkownikZFormLogowania.getLogin(), uzytkownikZFormLogowania.getHaslo()));
			System.err.println("Zalogowano: " + uzytkownikBeanSession.getUzytkownikZalogowany());
		}else{
		System.err.println("Niezalgowano");
		}
	}

	public String getZarejestruj() {
		return setUzytkownik(uzytkownikZFormRejestrowania);
		
	}

	public Uzytkownik getUzytkownikBan() {
		return uzytkownikBan;
	}

	public void setUzytkownikBan(Uzytkownik uzytkownikBan) {
		this.uzytkownikBan = uzytkownikBan;
	}

	public String getUpdatePanel() {
		return setUpdateUzytkownik(uzytkownikZUpdate);

	}

	public String getBanuj() {
		return setBan(uzytkownikBan);

	}


	public Uzytkownik getUzytkownikZFormLogowania() {
		return uzytkownikZFormLogowania;
	}

	public void setUzytkownikZFormLogowania(Uzytkownik uzytkownikZFormLogowania) {
		this.uzytkownikZFormLogowania = uzytkownikZFormLogowania;
	}

	private boolean getSprawdzCzyUzytkonikIstnieje(String pLogin, String pHaslo) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = UzytkownikBean.getConnection();
		String stm = "Select * from uzytkownik where login = ? and haslo = ?";
		List<Uzytkownik> records = new ArrayList<Uzytkownik>();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pLogin);
			pst.setString(2, pHaslo);
			pst.execute();
			rs = pst.getResultSet();

			return rs.isBeforeFirst();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Uzytkownik getUzytkownik(String pLogin, String pHaslo) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = UzytkownikBean.getConnection();
		String stm = "Select * from uzytkownik where login = ? and haslo = ?";

		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pLogin);
			pst.setString(2, pHaslo);
			pst.execute();
			rs = pst.getResultSet();
			Uzytkownik uzytkownik = new Uzytkownik();
			if (rs.next()) {

				uzytkownik.setId(rs.getInt(1));
				uzytkownik.setImie(rs.getString(2));
				uzytkownik.setNazwisko(rs.getString(3));
				uzytkownik.setLogin(rs.getString(4));
				uzytkownik.setHaslo(rs.getString(5));
				uzytkownik.setTypKonta(rs.getInt(6));
				uzytkownik.setStatusKonta(rs.getInt(7));
				uzytkownik.setDataUtworzenia(rs.getDate(8));
				uzytkownik.setDataZbanowania(rs.getDate(9));
			}
			return uzytkownik;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String setUzytkownik(Uzytkownik pUzytkownik) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = UzytkownikBean.getConnection();
		String stm = "INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta, Data_Utworzenia) VALUES(?, ?, ?, ?, 1, 1, NOW());";
		String wynik;
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pUzytkownik.getImie());
			pst.setString(2, pUzytkownik.getNazwisko());
			pst.setString(3, pUzytkownik.getLogin());
			pst.setString(4, pUzytkownik.getHaslo());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			wynik = "Blad podczas dodawania do bazy danych";
		}
		wynik = "Zarejestrowano poprwanie";
		return wynik;
	}

	private String setUpdateUzytkownik(Uzytkownik pUzytkownik) {

		int idUzytkownika = uzytkownikBeanSession.getUzytkownikZalogowany().getId();

		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = UzytkownikBean.getConnection();
		String stm = "UPDATE Uzytkownik SET Imie = ?, Nazwisko = ?, Haslo = ? WHERE Id = ?;";
		String wynik;
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pUzytkownik.getImie());
			pst.setString(2, pUzytkownik.getNazwisko());
			pst.setString(3, pUzytkownik.getHaslo());
			pst.setInt(4, idUzytkownika);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			wynik = "Blad podczas dodawania do bazy danych";
		}
		wynik = "";
		return wynik;
	}

	private String setBan(Uzytkownik pUzytkownik) {
	
		int idUzytkownika = uzytkownikBeanSession.getUzytkownikZalogowany().getId();

		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = UzytkownikBean.getConnection();
		String stm = "UPDATE Uzytkownik SET Data_Zbanowania = NOW() WHERE Id = ?;";
		String wynik;
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, idUzytkownika);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			wynik = "Blad podczas dodawania do bazy danych";
		}
		wynik = "";
		return wynik;
	}

	public Uzytkownik getUzytkownikZFormRejestrowania() {
		return uzytkownikZFormRejestrowania;
	}

	public void setUzytkownikZFormRejestrowania(Uzytkownik uzytkownikZFormRejestrowania) {
		this.uzytkownikZFormRejestrowania = uzytkownikZFormRejestrowania;
	}

	public Uzytkownik getUzytkownikZUpdate() {
		return uzytkownikZUpdate;
	}

	public void setUzytkownikZUpdate(Uzytkownik uzytkownikZUpdate) {
		this.uzytkownikZUpdate = uzytkownikZUpdate;
	}

	public Date getCzyZbanowany() {
		return czyZbanowany;
	}

	public void setCzyZbanowany(Date czyZbanowany) {
		this.czyZbanowany = czyZbanowany;
	}


	public UzytkownikBean getUzytkownikBeanSession() {
		return uzytkownikBeanSession;
	}

	public void setUzytkownikBeanSession(UzytkownikBean uzytkownikBeanSession) {
		this.uzytkownikBeanSession = uzytkownikBeanSession;
	}
	
	
	
	

}