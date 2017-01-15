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

	//Geters/Seters
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