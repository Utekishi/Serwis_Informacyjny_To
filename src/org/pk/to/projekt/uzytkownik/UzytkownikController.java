package org.pk.to.projekt.uzytkownik;

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

@ManagedBean(name = "uzytkownikController", eager = true)
@RequestScoped
public class UzytkownikController implements Serializable {

	private static final long serialVersionUID = 1L;

	public String informacjaNaglowek = "Brak sesji";
	public int poziomDostepu = -1;
	public Uzytkownik uzytkownikZFormLogowania = new Uzytkownik();
	public Uzytkownik uzytkownikZFormRejestrowania = new Uzytkownik();

	public UzytkownikController() {
		Uzytkownik uzytkownikSesji = new Uzytkownik();
		FacesContext context = FacesContext.getCurrentInstance();
		if (!context.getExternalContext().getSessionMap().containsKey("uzytkownikSesji")) {

			uzytkownikSesji.typKonta = 0;
			context.getExternalContext().getSessionMap().put("uzytkownikSesji", uzytkownikSesji);
			informacjaNaglowek = "Witaj, goœæ";
		}
		uzytkownikSesji = (Uzytkownik) context.getExternalContext().getSessionMap().get("uzytkownikSesji");

		if (uzytkownikSesji.typKonta == 0) {
			informacjaNaglowek = "Witaj, goœæ";
		} else if (uzytkownikSesji.typKonta == 1) {
			informacjaNaglowek = "Witaj u¿ytkowniku, " + uzytkownikSesji.imie;
		} else if (uzytkownikSesji.typKonta == 2) {
			informacjaNaglowek = "Witaj moderatorze, " + uzytkownikSesji.imie;
		} else if (uzytkownikSesji.typKonta == 3) {
			informacjaNaglowek = "Witaj administratorze, <name>" + uzytkownikSesji.imie;
		}
		poziomDostepu = uzytkownikSesji.typKonta;
	}

	public String getInformacjaNaglowek() {
		return informacjaNaglowek;
	}

	public String getZaloguj() {
		System.err.println("Z log" + uzytkownikZFormLogowania);
		if (getSprawdzCzyUzytkonikIstnieje(uzytkownikZFormLogowania.getLogin(), uzytkownikZFormLogowania.getHaslo())) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("uzytkownikSesji",
					getUzytkownik(uzytkownikZFormLogowania.getLogin(), uzytkownikZFormLogowania.getHaslo()));
			System.err.println(
					"Z bazy" + getUzytkownik(uzytkownikZFormLogowania.getLogin(), uzytkownikZFormLogowania.getHaslo()));
			System.err.println("Z con" + context.getExternalContext().getSessionMap().get("uzytkownikSesji"));

			return "Zalogowano";
		}
		return "Nie Zalogowano";

	}

	public String getZarejestruj() {
		return setUzytkownik(uzytkownikZFormRejestrowania);
	}

	public void setInformacjaNaglowek(String informacjaNaglowek) {
		this.informacjaNaglowek = informacjaNaglowek;
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

	private String setUzytkownik(Uzytkownik pUzytkownik){
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    Connection con = UzytkownikBean.getConnection();
	    String stm = "INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta, Data_Utworzenia) VALUES(?, ?, ?, ?, 1, 1, NOW());";
	String wynik;
	    try {   
	        pst = con.prepareStatement(stm);
	        pst.setString(1,pUzytkownik.imie);
	        pst.setString(2,pUzytkownik.nazwisko);
	        pst.setString(3,pUzytkownik.login);
	        pst.setString(4,pUzytkownik.haslo);
	        pst.execute();
	    
	    } catch (SQLException e) {
	       e.printStackTrace();
	       wynik="Blad podczas dodawania do bazy danych";
	    }
	    wynik="Zarejestrowano poprwanie";
	    return wynik;
	 }

	public Uzytkownik getUzytkownikZFormRejestrowania() {
		return uzytkownikZFormRejestrowania;
	}

	public void setUzytkownikZFormRejestrowania(Uzytkownik uzytkownikZFormRejestrowania) {
		this.uzytkownikZFormRejestrowania = uzytkownikZFormRejestrowania;
	}

	public int getPoziomDostepu() {
		System.err.println("poziomDostepu: " + poziomDostepu);
		return poziomDostepu;

	}

	public void setPoziomDostepu(int poziomDostepu) {
		this.poziomDostepu = poziomDostepu;
	}

}