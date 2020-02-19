/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.dao;

import com.autofactory.data.Crbdclrs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Eugene Sidorov
 */
@Stateless
public class CrbdclrsFacade extends AbstractFacade<Crbdclrs> {

    //@PersistenceContext(unitName = "com.mycompany_AutoFactoryDB_war_1.0-SNAPSHOTPU")
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrbdclrsFacade() {
        super(Crbdclrs.class);
    }
    
}
