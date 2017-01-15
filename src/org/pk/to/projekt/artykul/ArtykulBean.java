package org.pk.to.projekt.artykul;

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
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "artykulBean", eager = true)
@SessionScoped
public class ArtykulBean implements Serializable {

   private static final long serialVersionUID = 1L;

   public List<Artykul> getArtykuly(){
	   System.err.println(" List<Artykul> getArtykuly(");
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "Select * from artykul";
      List<Artykul> records = new ArrayList<Artykul>();
      try {   
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
        
         while(rs.next()){
        	 Artykul artykul = new Artykul();
        	 artykul.setId(rs.getInt(1));
        	 artykul.setAutor(rs.getInt(2));
        	 artykul.setTytul(rs.getString(3));
        	 artykul.setTresc(rs.getClob(4));
        	 artykul.setStatus(rs.getInt(5));
        	 artykul.setKategoria(rs.getInt(6));
        	 artykul.setObrazek(rs.getString(7));
        	 artykul.setDataPublikacji(rs.getDate(8));
            records.add(artykul);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return records;
   }

   public Connection getConnection(){
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
         System.err.println("Connection Artykul");
      } catch (SQLException ex) {
         System.err.println(ex.getMessage());
      }
      finally{
      }
      return con;
   }
}