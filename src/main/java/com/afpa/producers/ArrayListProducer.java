package com.afpa.producers;

import com.afpa.model.Categorie;
import com.afpa.model.Produit;
import com.afpa.qualifiers.CategorieQualifier;

import javax.enterprise.inject.Produces;
import java.util.ArrayList;

public class ArrayListProducer {

    @Produces @CategorieQualifier
    public ArrayList<Categorie> ArrayListProducer(){
        return new ArrayList<>();
    }

}
