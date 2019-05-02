package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Categorie")
public class Categorie implements Entities, Serializable {

    @Id
    private Integer id;
    private String libelle;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.PERSIST)
    private List<Produit> produitList;

    @Transient
    private List<LigneCommande> ligneCommandes;


    public Categorie() {

    }

    public Categorie(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.produitList = produitList;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }
}
