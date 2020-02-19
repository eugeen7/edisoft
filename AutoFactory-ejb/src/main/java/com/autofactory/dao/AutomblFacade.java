
package com.autofactory.dao;

import com.autofactory.data.Autombl;
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
public class AutomblFacade extends AbstractFacade<Autombl> {

    //@PersistenceContext(unitName = "com.mycompany_AutoFactoryDB_war_1.0-SNAPSHOTPU")
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutomblFacade() {
        super(Autombl.class);
    }

    public List<Autombl> findAllAutombl() {
        try {
            Query q = em.createQuery("SELECT mbl FROM Autombl mbl WHERE mbl.stactive = TRUE", Autombl.class);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
