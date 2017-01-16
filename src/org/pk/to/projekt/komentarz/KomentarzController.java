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
import org.pk.to.projekt.uzytkownik.Uzytkownik;

@ManagedBean(name = "komentarzController", eager = true)
@RequestScoped
public class KomentarzController implements Serializable {

	private static final long serialVersionUID = 1L;

	public int idUzytkownika = -1;
	public int idArtykulu = -1;
	public Komentarz komentarzZFormNowego = new Komentarz();

	public KomentarzController() {
		Uzytkownik uzytkownikSesji = new Uzytkownik();
		Artykul artykulSesji = new Artykul();
		FacesContext context = FacesContext.getCurrentInstance();
		uzytkownikSesji = (Uzytkownik) context.getExternalContext().getSessionMap().get("uzytkownikSesji");
		artykulSesji = (Artykul) context.getExternalContext().getSessionMap().get("artykulSesji");
		idUzytkownika = uzytkownikSesji.getId();
		idArtykulu = artykulSesji.getId();
	}

	public String getZapiszKomentarz() {
		return setKomentarz(komentarzZFormNowego);
	}

	private String setKomentarz(Komentarz pKomentarz) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = KomentarzBean.getConnection();
		String wynik;
		String stm = "INSERT INTO Komentarze(Tresc, Autor_Id, Artykul_Id, Data_Utworzenia) VALUES(?, ?, ?, ?, NOW());";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pKomentarz.getTresc());
			pst.setInt(2, idUzytkownika);
			pst.setInt(3, idArtykulu);
			pst.setDate(4, pKomentarz.getDataUtworzenia());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			wynik = "Blad podczas dodawania do bazy danych";
		}

		wynik = "";
		return wynik;
	}

	public Komentarz getKomentarzZFormNowego() {
		return komentarzZFormNowego;
	}

	public void setKomentarzZFormNowego(Komentarz komentarzZFormNowego) {
		this.komentarzZFormNowego = komentarzZFormNowego;
	}

}