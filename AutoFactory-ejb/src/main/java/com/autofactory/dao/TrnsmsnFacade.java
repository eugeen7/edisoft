/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.dao;

import com.autofactory.data.Trnsmsn;
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
public class TrnsmsnFacade extends AbstractFacade<Trnsmsn> {

    //@PersistenceContext(unitName = "com.mycompany_AutoFactoryDB_war_1.0-SNAPSHOTPU")
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrnsmsnFacade() {
        super(Trnsmsn.class);
    }

    public List<Trnsmsn> findUnUseTrnsmsn() {
        try {
            Query q = em.createQuery("SELECT trns FROM Trnsmsn trns WHERE (trns.stactive = TRUE) AND (trns.trnsUse IS NULL)", Trnsmsn.class);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
