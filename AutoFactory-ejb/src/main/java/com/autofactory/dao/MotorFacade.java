/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.dao;

import com.autofactory.data.Motor;
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
public class MotorFacade extends AbstractFacade<Motor> {

    //@PersistenceContext(unitName = "com.mycompany_AutoFactoryDB_war_1.0-SNAPSHOTPU")
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotorFacade() {
        super(Motor.class);
    }
    
    public List<Motor> findUnUseMotors() {
        try {
            Query q = em.createQuery("SELECT mtr FROM Motor mtr WHERE (mtr.stactive = TRUE) AND (mtr.mtrUse IS NULL)", Motor.class);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Motor> findActiveMotors() {
        try {
            Query q = em.createQuery("SELECT mtr FROM Motor mtr WHERE (mtr.stactive = TRUE)", Motor.class);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
