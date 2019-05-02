package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Produit")
public class Produit implements Entities, Serializable {

    @Id
    private Integer id;
    private String modele;
    private String marque;
    private String descriptif;
    private BigDecimal prix;
    @Transient
    private boolean checked;
    @Transient
    private Integer quantite;
    @Transient
    private LigneCommande associatedLine;


    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> ligneCommande;


    public Produit() {
        checked = false;
    }


    public Produit(Integer id, String modele, String marque, String descriptif, BigDecimal prix, Categorie categorie) {
        this.id = id;
        this.modele = modele;
        this.marque = marque;
        this.descriptif = descriptif;
        this.prix = prix;
        this.categorie = categorie;
        checked=false;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LigneCommande getAssociatedLine() {
        return associatedLine;
    }

    public void setAssociatedLine(LigneCommande associatedLine) {
        this.associatedLine = associatedLine;
    }

    public List<LigneCommande> getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(List<LigneCommande> ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id.equals(produit.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
