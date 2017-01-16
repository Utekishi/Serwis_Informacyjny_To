package org.pk.to.projekt;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "nawigacjaController", eager = true)
@RequestScoped
public class NawigacjaController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Zmienne przekazywane z frontu
	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	@ManagedProperty(value = "#{param.artykulId}")
	private int artykulId;

	// Nawigacja po stronie

	public String processRejestracja() {
		return "rejestracja";
	}

	public String moveToRejestracja() {
		return "rejestracja";
	}

	public String processNowyArtykul() {
		return "nowyArtykul";
	}

	public String moveToNowyArtykul() {
		return "nowyArtykul";
	}

	public String processIndex() {
		return "index";
	}

	public String moveToIndex() {
		return "index";
	}

	public String moveToArtykul() {
		return "artykul";
	}

	public String processArtykul() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("wybranyArtykulId", artykulId);
	//	context.getExternalContext().getSessionMap().put("artykulSesji", artykulId);
		return "artykul";
	}

	public String moveToLogowanie() {
		return "logowanie";
	}

	public String processLogowanie() {
		return "logowanie";
	}

	public String processWylogowanie() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/home.xhtml?faces-redirect=true";
	}

	public String moveToPanel() {
		return "panel";
	}

	public String processPanel() {
		return "panel";
	}

	public String moveToEdycjaArtykulu() {
		return "edycjaArtykulu";
	}

	public String processEdycjaArtykulu() {
		return "edycjaArtykulu";
	}
	
	public String moveToIndexWiadomosci() {
		return "indexWiadomosci";
	}

	public String processIndexWiadomosci() {
		return "indexWiadomosci";
	}
	
	public String moveToIndexPolityka() {
		return "indexPolityka";
	}

	public String processIndexPolityka() {
		return "indexPolityka";
	}
	
	public String moveToEdycjaReklam() {
		return "edycjaReklam";
	}

	public String processEdycjaReklam() {
		return "edycjaReklam";
	}

	// Geters/Seters
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public int getArtykulId() {
		return artykulId;
	}

	public void setArtykulId(int artykulId) {
		this.artykulId = artykulId;
	}

}