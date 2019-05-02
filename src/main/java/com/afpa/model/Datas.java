package com.afpa.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Datas {

    private static final Categorie c1 = new Categorie(1, "processeur");
    private static final Categorie c2 = new Categorie(2, "boitier");
    private static final Categorie c3 = new Categorie(3, "carteMere");

    private final static List<Produit> processeur = Arrays.asList(
            new Produit(1, "Core i7 920", "Intel", "2.6 GHz, 8Mo, Socket 1366", new BigDecimal("290"), c1),
            new Produit(2, "Phenom X4 9550", "AMD", "2.2 GHz, 2Mo, Socket AM2", new BigDecimal("130"), c1));

    private final static List<Produit> boitier = Arrays.asList(
            new Produit(3, "PC-V300A", "Lian Li", "Mini boitier blanc, sans alim", new BigDecimal("115"), c2),
            new Produit(4, "NSK4000", "Antec", "Acier, mini tour, sans alim", new BigDecimal("40"), c2));

    private final static List<Produit> carteMere = Arrays.asList(
            new Produit(5, "P6T Deluxe", "Asus", "Intel X58 ICH10R Socket 1366", new BigDecimal("300"), c3),
            new Produit(6, "DKA790GX", "MSI", "AMD 790GX, Radeon HD3300, Socket AM2", new BigDecimal("150"), c3));


    public static Categorie getC1()
    {
        c1.setProduitList(processeur);
        return c1;
    }

    public static Categorie getC2()
    {
        c2.setProduitList(boitier);
        return c2;
    }

    public static Categorie getC3()
    {
        c3.setProduitList(carteMere);
        return c3;
    }

}
