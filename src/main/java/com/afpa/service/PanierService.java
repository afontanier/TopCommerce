package com.afpa.service;


import com.afpa.dbaccess.DbAccess;
import com.afpa.model.Categorie;
import com.afpa.model.LigneCommande;
import com.afpa.model.Panier;
import com.afpa.model.Produit;
import com.afpa.qualifiers.PanierQualifier;
import com.afpa.utils.ViewUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PanierService implements Serializable {


    @Inject
    @PanierQualifier
    private Panier panier;

    @Inject
    FacesContext context;

    @Inject
    ViewUtils viewUtils;

    private boolean commande;

    @Inject
    DbAccess dbAccess;

    public PanierService() {

        commande = false;
    }

    public boolean isCommande() {
        return commande;
    }

    public void setCommande(boolean commande) {
        this.commande = commande;
    }


    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }




    public void managedChecked(Produit produit) {

        if (produit.getQuantite() > 0 && !produit.isChecked())
            produit.setChecked(true);
         else
             if (produit.getQuantite() == 0 && produit.isChecked())
            produit.setChecked(false);

        this.addToPanier(produit, true);
    }


    public void addToPanier(Produit produit, boolean managed) {

        LigneCommande ligneCommande = null;
        if (!managed) {
            ligneCommande = new LigneCommande(produit);
            ligneCommande.setCommande(panier.getCommande());
            produit.setAssociatedLine(ligneCommande);
        }
        else
        {
            ligneCommande = produit.getAssociatedLine();
        }

        if (ligneCommande.getProduit().isChecked()) {
            if (ligneCommande.getProduit().getQuantite()==0)
            {
                ligneCommande.getProduit().setQuantite(1);
            }

            if (!panier.getCommande().getLigneCommandeList().contains(ligneCommande)) {
                ligneCommande.setQuantite(produit.getQuantite());

                panier.getCommande().getLigneCommandeList().add(ligneCommande);
                context.addMessage(null, new FacesMessage("L'article " +ligneCommande.getProduit().getModele() + " a été ajouté au panier"));
            }
            else
            {
                ligneCommande.setQuantite(produit.getQuantite());
                context.addMessage(null, new FacesMessage("La quantité a été incrémentée"));
            }

        }
        else
        {
            if (ligneCommande.getProduit().getQuantite()>0)
            {
                ligneCommande.getProduit().setQuantite(0);
            }
            if (panier.getCommande().getLigneCommandeList().contains(ligneCommande)) {
                panier.getCommande().getLigneCommandeList().remove(ligneCommande);
                context.addMessage(null, new FacesMessage("L'article "+ligneCommande.getProduit().getModele()+" a été retiré du panier"));
            }
            else
            {
                context.addMessage(null, new FacesMessage("La quantité a été décrémentée"));
            }

        }

        panier.getCommande().calculerTotal();
    }

    public void validerCommande() {

        commande = true;
        HtmlOutputLabel titre = (HtmlOutputLabel) viewUtils.getUIComponentOfId(context.getViewRoot(), "titre");
        titre.setValue("Commande");
        panier.getCommande().setEtat(commande);
        dbAccess.write(panier.getCommande());
        context.addMessage(null, new FacesMessage("La commande a été validée"));

    }


}

