package org.pk.to.projekt.reklama;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.pk.to.projekt.artykul.Artykul;
import org.pk.to.projekt.uzytkownik.Uzytkownik;

@ManagedBean(name = "reklamaController", eager = true)
@RequestScoped
public class ReklamaController implements Serializable {

	private static final long serialVersionUID = 1L;

	public int idUzytkownika = -1;
	public int idArtykulu = -1;
	public Reklama reklamaZFormNowego = new Reklama();

	public ReklamaController() {
		Uzytkownik uzytkownikSesji = new Uzytkownik();
		Artykul artykulSesji = new Artykul();
		FacesContext context = FacesContext.getCurrentInstance();
		uzytkownikSesji = (Uzytkownik) context.getExternalContext().getSessionMap().get("uzytkownikSesji");
		 int wybranyArtykulId = (int) context.getExternalContext().getSessionMap().get("wybranyArtykulId");
		idUzytkownika = uzytkownikSesji.getId();
		System.err.println("artykulSesji.getId()"+wybranyArtykulId);
		idArtykulu = wybranyArtykulId;
	}

	public String getZapiszReklama() {
		return setReklama(reklamaZFormNowego);
	}

	private String setReklama(Reklama pReklama) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = ReklamaBean.getConnection();
		String wynik;
		String stm = "UPDATE Reklamy SET Data_Dodania = NOW(), Dostawca = ?, Link = ?, Koszt = ? WHERE Id = 1;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pReklama.getDostawca());
			pst.setString(2, pReklama.getLink());
			pst.setInt(3, pReklama.getKoszt());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			wynik = "Blad podczas dodawania do bazy danych";
		}

		wynik = "";
		return wynik;
	}

	public Reklama getReklamaZFormNowego() {
		return reklamaZFormNowego;
	}

	public void setReklamaZFormNowego(Reklama reklamaZFormNowego) {
		this.reklamaZFormNowego = reklamaZFormNowego;
	}

}