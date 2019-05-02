package com.afpa.service;

import com.afpa.dbaccess.DbAccess;
import com.afpa.model.*;
import com.afpa.qualifiers.CategorieQualifier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CategorieService implements Serializable {

    @Inject
    DbAccess dbAccess;

    private Categorie categorie;

    private List<Categorie> categorieList;

    @Inject
    PanierService panierService;


    @PostConstruct
    public void init() {
        if (dbAccess.countCategories() == 0) {
            dbAccess.write((Entities) Datas.getC1());
            dbAccess.write((Entities) Datas.getC2());
            dbAccess.write((Entities) Datas.getC3());
        }

        categorieList = dbAccess.lireAll();
    }


    public CategorieService() {

    }

    public Categorie getCategorie() {
        return categorie;
    }

    @Transactional
    public void setCategorie(Categorie categorie) {
        if (categorie != null) {
            categorie = dbAccess.getEm().merge(categorie);
            dbAccess.getEm().refresh(categorie);
            categorie.getProduitList().size();
            System.out.println(categorie.getProduitList().size());
        }

        this.categorie = categorie;
        this.synWithPanier();
    }

    private void synWithPanier() {

        for (Produit produit : categorie.getProduitList()) {

            for (LigneCommande ligneCommande : panierService.getPanier().getCommande().getLigneCommandeList()) {

                if (produit.equals(ligneCommande.getProduit())) {

                    produit.setChecked(true);
                    produit.setQuantite(ligneCommande.getProduit().getQuantite());
                }
            }
        }
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }
}
