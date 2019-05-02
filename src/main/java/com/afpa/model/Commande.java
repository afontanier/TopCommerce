package com.afpa.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Commande")
public class Commande implements Serializable, Entities {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "commande", cascade = CascadeType.PERSIST)
    private List<LigneCommande>ligneCommandeList;
    @Transient
    private BigDecimal totalCommande;

    private boolean etat;


    public Commande() {

        ligneCommandeList=new ArrayList<>();
    }

    public Commande(Integer id, List<LigneCommande> ligneCommandeList) {
        this.id = id;
        this.ligneCommandeList = ligneCommandeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LigneCommande> getLigneCommandeList() {
        return ligneCommandeList;
    }

    public void setLigneCommandeList(List<LigneCommande> ligneCommandeList) {
        this.ligneCommandeList = ligneCommandeList;
    }

    public BigDecimal getTotalCommande() {
        return totalCommande;
    }

    public void setTotalCommande(BigDecimal totalCommande) {
        this.totalCommande = totalCommande;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void calculerTotal(){

        totalCommande=new BigDecimal("0");
        for (LigneCommande ligneCommande:ligneCommandeList){

            totalCommande=totalCommande.add(ligneCommande.computeTotalLigne());
        }
    }
}
