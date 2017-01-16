package org.pk.to.projekt;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.pk.to.projekt.artykul.ArtykulController;
import org.pk.to.projekt.komentarz.KomentarzController;
import org.pk.to.projekt.uzytkownik.UzytkownikController;

@ManagedBean(name = "nawigacjaController", eager = true)
@RequestScoped
public class NawigacjaController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Zmienne przekazywane z frontu
	@ManagedProperty(value = "#{param.artykulId}")
	private int artykulId=0;

	@ManagedProperty(value = "#{param.operacja}")
	private String operacja="";
	
	@ManagedProperty(value = "#{param.kategoriaId}")
	private int kategoriaId=0;
	
	@ManagedProperty("#{uzytkownikController}")
	private UzytkownikController uzytkownikControllerBeanSession;
	
	@ManagedProperty("#{artykulController}")
	private ArtykulController artykulControllerBeanSession;
	
	@ManagedProperty("#{komentarzController}")
	private KomentarzController komentarzControllerBeanSession;
	
	private FacesContext context = FacesContext.getCurrentInstance();
	
	private String przekierujNa="index";
	public String processOperacja() {
		System.err.println("WykonanaOperacja: " + operacja);
		
		
		
		switch(operacja)
		{
		    case "wybierzArtykul":
		    {
		    	komentarzControllerBeanSession.pobierzKomentarze(artykulId);
				artykulControllerBeanSession.pobierzArtykul(artykulId);
				przekierujNa= "artykul";
				break;
		    }
		    case "zaloguj":
			{
				uzytkownikControllerBeanSession.zaloguj();
				przekierujNa= "index";
				break;
			}
			case "wylogowanie":
			{
				context.getExternalContext().invalidateSession();
				przekierujNa= "/home.xhtml?faces-redirect=true";
				break;
			}
			case "panelAdministracyjny":
			{
				przekierujNa= "panel";
				artykulControllerBeanSession.pobierzArtykulyAutora();
				
				break;
			}
			
			case "wybierzKategorie":
			{
				artykulControllerBeanSession.pobierzArtykuly(kategoriaId);
				przekierujNa= "index";
				break;
			}
			case "rejestracja":
			{
				System.err.println(uzytkownikControllerBeanSession.getZarejestruj());
				przekierujNa= "index";
				break;
			}
			
					
			case "dodajKomentarz":
			{
				System.err.println(komentarzControllerBeanSession.getZapiszKomentarz());
				przekierujNa= "artykul";
				break;
			}
			
			case "dodajArtykul":
			{
				System.err.println(artykulControllerBeanSession.getZapisz());
				przekierujNa= "panel";
				break;
			}
			//Domyœlne operacje nie wymagaj¹ca dodatkowego obs³u¿enia, np pobrania danych z bazy
			default: 
			{
				przekierujNa= operacja;			
			}
		}
		// Po ka¿dej operacji odœwie¿amy status u¿ytkownika 
			uzytkownikControllerBeanSession.zainicjalizuj();
		
		return przekierujNa;
		
	}
	

	// Geters/Seters
	public int getArtykulId() {
		return artykulId;
	}

	public void setArtykulId(int artykulId) {
		this.artykulId = artykulId;
	}

	public String getOperacja() {
		return operacja;
	}

	public void setOperacja(String operacja) {
		this.operacja = operacja;
	}

	public int getKategoriaId() {
		return kategoriaId;
	}


	public void setKategoriaId(int kategoriaId) {
		this.kategoriaId = kategoriaId;
	}


	public UzytkownikController getUzytkownikControllerBeanSession() {
		return uzytkownikControllerBeanSession;
	}


	public void setUzytkownikControllerBeanSession(UzytkownikController uzytkownikControllerBeanSession) {
		this.uzytkownikControllerBeanSession = uzytkownikControllerBeanSession;
	}


	public ArtykulController getArtykulControllerBeanSession() {
		return artykulControllerBeanSession;
	}


	public void setArtykulControllerBeanSession(ArtykulController artykulControllerBeanSession) {
		this.artykulControllerBeanSession = artykulControllerBeanSession;
	}


	public KomentarzController getKomentarzControllerBeanSession() {
		return komentarzControllerBeanSession;
	}


	public void setKomentarzControllerBeanSession(KomentarzController komentarzControllerBeanSession) {
		this.komentarzControllerBeanSession = komentarzControllerBeanSession;
	}
	
	

}