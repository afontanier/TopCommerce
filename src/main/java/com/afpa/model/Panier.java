package com.afpa.model;

import java.io.Serializable;


public class Panier implements Serializable {


    private Commande commande;

    public Panier() {

        commande=new Commande();
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}


