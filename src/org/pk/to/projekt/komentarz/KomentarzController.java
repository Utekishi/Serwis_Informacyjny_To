package org.pk.to.projekt.komentarz;

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

import org.pk.to.projekt.artykul.Artykul;
import org.pk.to.projekt.artykul.ArtykulBean;
import org.pk.to.projekt.uzytkownik.Uzytkownik;
import org.pk.to.projekt.uzytkownik.UzytkownikBean;

@ManagedBean(name = "komentarzController", eager = true)
@RequestScoped
public class KomentarzController implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	public Komentarz komentarzZFormNowego = new Komentarz();

	@ManagedProperty("#{uzytkownikBean}")
	private UzytkownikBean uzytkownikBeanSession;
	
	@ManagedProperty("#{komentarzBean}")
	private KomentarzBean komentarzBeanSession;

	@ManagedProperty("#{artykulBean}")
	private ArtykulBean artykulBeanSession;
	
	public String getZapiszKomentarz() {
		return setKomentarz(komentarzZFormNowego);
	}

	private String setKomentarz(Komentarz pKomentarz) {
		System.err.println("Dodaj komentarz: " +pKomentarz);
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = KomentarzBean.getConnection();
		String wynik;
		String stm = "INSERT INTO Komentarze(Tresc, Autor_Login, Artykul_Id, Data_Utworzenia) VALUES(?, ?, ?, NOW());";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pKomentarz.getTresc());
			pst.setString(2, uzytkownikBeanSession.getUzytkownikZalogowany().getImie()+" ("+ uzytkownikBeanSession.getUzytkownikZalogowany().getLogin()+") "+uzytkownikBeanSession.getUzytkownikZalogowany().getNazwisko());
			pst.setInt(3, artykulBeanSession.getWybranyArtykul().getId());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			wynik = "Blad podczas dodawania do bazy danych";
		}

		wynik = "";
		return wynik;
	}
	
	public void pobierzKomentarze(int artykulId) {
		List<Komentarz> records = new ArrayList<Komentarz>();

		FacesContext context = FacesContext.getCurrentInstance();
	

					ResultSet rs = null;
			PreparedStatement pst = null;
			Connection con = KomentarzBean.getConnection();
			String stm = "Select * from komentarze where Artykul_Id = ? ";

			try {
				pst = con.prepareStatement(stm);
				pst.setInt(1, artykulId);
				pst.execute();
				rs = pst.getResultSet();

				while (rs.next()) {
					Komentarz komentarz = new Komentarz();
					komentarz.setId(rs.getInt(1));
					komentarz.setTresc(rs.getString(2));
					komentarz.setAutorLogin(rs.getString(3));
					komentarz.setArtykulId(rs.getInt(4));
					komentarz.setDataUtworzenia(rs.getDate(5));

					records.add(komentarz);
				}
				komentarzBeanSession.setWybraneKomentarze(records);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	
	}

	public Komentarz getKomentarzZFormNowego() {
		return komentarzZFormNowego;
	}

	public void setKomentarzZFormNowego(Komentarz komentarzZFormNowego) {
		this.komentarzZFormNowego = komentarzZFormNowego;
	}

	public UzytkownikBean getUzytkownikBeanSession() {
		return uzytkownikBeanSession;
	}

	public void setUzytkownikBeanSession(UzytkownikBean uzytkownikBeanSession) {
		this.uzytkownikBeanSession = uzytkownikBeanSession;
	}

	public KomentarzBean getKomentarzBeanSession() {
		return komentarzBeanSession;
	}

	public void setKomentarzBeanSession(KomentarzBean komentarzBeanSession) {
		this.komentarzBeanSession = komentarzBeanSession;
	}

	public ArtykulBean getArtykulBeanSession() {
		return artykulBeanSession;
	}

	public void setArtykulBeanSession(ArtykulBean artykulBeanSession) {
		this.artykulBeanSession = artykulBeanSession;
	}


	
	
	

}