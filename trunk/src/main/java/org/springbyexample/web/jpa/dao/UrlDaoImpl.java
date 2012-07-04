package ui.web.org.springbyexample.web.jpa.dao;

import ui.web.org.springbyexample.web.jpa.bean.Person;
import ui.web.org.springbyexample.web.jpa.bean.UrlToCheck;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


public class UrlDaoImpl implements UrlDao{

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public UrlToCheck findUrlToCheckById(Integer id){
        return em.find(UrlToCheck.class, id);
    }

    @SuppressWarnings("unchecked")
    public Collection<UrlToCheck> findUrlToCheck(){
        return em.createQuery("select urls from UrlToCheck urls").getResultList();
    }

    @SuppressWarnings("unchecked")
    public UrlToCheck save(UrlToCheck urlToCheck){
        return em.merge(urlToCheck);
    }

    @SuppressWarnings("unchecked")
    public void delete(UrlToCheck urlToCheck){
        em.remove(em.merge(urlToCheck));
    }


}
