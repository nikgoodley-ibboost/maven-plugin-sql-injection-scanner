package ui.web2.web.jpa.dao;



import ui.web2.web.jpa.bean.UrlToCheck;

import java.util.Collection;



public interface UrlDao {


    public UrlToCheck findUrlToCheckById(Integer id);

    public Collection<UrlToCheck> findUrlToCheck();

    public UrlToCheck save(UrlToCheck urlToCheck);

    public void delete(Integer id);


}
