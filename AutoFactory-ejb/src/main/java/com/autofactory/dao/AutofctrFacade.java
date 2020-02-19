/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.dao;

import com.autofactory.data.Autofctr;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eugene Sidorov
 */
@Stateless
public class AutofctrFacade extends AbstractFacade<Autofctr> {

    //@PersistenceContext(unitName = "com.mycompany_AutoFactoryDB_war_1.0-SNAPSHOTPU")
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutofctrFacade() {
        super(Autofctr.class);
    }
    
    public List<Autofctr> findAutofctr() {
        try {
            Query q = em.createQuery("SELECT fctr FROM Autofctr fctr", Autofctr.class);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
