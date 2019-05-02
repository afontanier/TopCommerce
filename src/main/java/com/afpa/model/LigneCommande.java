package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "LigneCommande")
public class LigneCommande implements Serializable, Entities {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

    private Integer quantite;



    public LigneCommande() {


    }



    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LigneCommande(Produit produit) {
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }




    public BigDecimal computeTotalLigne(){

        BigDecimal total_ligne = new BigDecimal("0");
        total_ligne = produit.getPrix().multiply(new BigDecimal(produit.getQuantite()));
        return total_ligne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneCommande that = (LigneCommande) o;
        return produit.equals(that.produit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(produit);
    }
}
