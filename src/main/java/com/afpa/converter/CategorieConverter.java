package com.afpa.converter;

import com.afpa.model.Categorie;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategorieConverter implements Converter<Categorie>, Serializable {


    @Override
    public Categorie getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        UISelectItems selectItems = (UISelectItems) uiComponent.getChildren().get(1);
        List<Categorie> categorieList=(List<Categorie>) selectItems.getValue();
        return categorieList.stream().filter(categorie -> categorie.getId()==Integer.parseInt(s)).findFirst().orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categorie categorie) {
        if(categorie!= null)
            return categorie.getId().toString();
        else
            return "";
    }
}