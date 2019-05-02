package com.afpa.dbaccess;

import com.afpa.model.Categorie;
import com.afpa.model.Entities;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class DbAccess implements Serializable {

    @PersistenceContext(unitName = "MYPU")
    private EntityManager em;



    @Transactional
    public void write(Entities e)
    {
        em.persist(e);
    }



    public Long countCategories() {
        return (Long) em.createQuery("select count(c) from Categorie c").getSingleResult();

    }



    public List<Categorie> lireAll()
    {
        return em.createQuery("select c from Categorie c").getResultList();
    }




    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
