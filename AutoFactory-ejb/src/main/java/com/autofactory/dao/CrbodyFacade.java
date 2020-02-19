/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.dao;

import com.autofactory.data.Crbody;
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
public class CrbodyFacade extends AbstractFacade<Crbody> {

    //@PersistenceContext(unitName = "com.mycompany_AutoFactoryDB_war_1.0-SNAPSHOTPU")
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrbodyFacade() {
        super(Crbody.class);
    }

    public List<Crbody> findUnUseCrbodies() {
        try {
            Query q = em.createQuery("SELECT crbd FROM Crbody crbd WHERE (crbd.stactive = TRUE) AND (crbd.crbdUse IS NULL)", Crbody.class);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
