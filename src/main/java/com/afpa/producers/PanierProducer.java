package com.afpa.producers;

import com.afpa.model.Panier;
import com.afpa.qualifiers.PanierQualifier;

import javax.enterprise.inject.Produces;

public class PanierProducer {

    @Produces @PanierQualifier
    public Panier panierProducer(){
        return new Panier();
    }
}
