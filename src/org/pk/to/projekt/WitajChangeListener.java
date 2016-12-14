package org.pk.to.projekt;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class WitajChangeListener implements ValueChangeListener {
   @Override
   public void processValueChange(ValueChangeEvent event)
      throws AbortProcessingException {
      //access country bean directly
      IndexBean userData = (IndexBean) FacesContext.getCurrentInstance().
         getExternalContext().getSessionMap().get("indexBean");

      
      System.out.println("Zmieniono imie z: "+userData.getImie()+" na "+event.getNewValue().toString());
      userData.setWelcomeText("Witaj " +event.getNewValue().toString());
   }
}