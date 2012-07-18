package ui.web2.web.jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ui.web2.web.jpa.bean.UrlToCheck;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
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
