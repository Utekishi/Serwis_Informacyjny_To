package org.pk.to.projekt.artykul;

import java.sql.Blob;
import java.sql.Date;


public class Artykul {
		   int id;
		   int autor;
		   String tytul;
		   Blob tresc;
		   int status;
		   int kategoria;
		   String obrazek;
		   Date dataPublikacji;
		   
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getAutor() {
			return autor;
		}
		public void setAutor(int autor) {
			this.autor = autor;
		}
		public String getTytul() {
			return tytul;
		}
		public void setTytul(String tytul) {
			this.tytul = tytul;
		}
		public Blob getTresc() {
			return tresc;
		}
		public void setTresc(Blob tresc) {
			this.tresc = tresc;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getKategoria() {
			return kategoria;
		}
		public void setKategoria(int kategoria) {
			this.kategoria = kategoria;
		}
		public String getObrazek() {
			return obrazek;
		}
		public void setObrazek(String obrazek) {
			this.obrazek = obrazek;
		}
		public Date getDataPublikacji() {
			return dataPublikacji;
		}
		public void setDataPublikacj(Date dataPublikacj) {
			this.dataPublikacji = dataPublikacj;
		}

		   
}
