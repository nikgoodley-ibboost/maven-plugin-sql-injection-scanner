package de.nshevchenko.sqlinjection.check;


import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.nshevchenko.HttpStatus;
import de.nshevchenko.config.Site;
import org.apache.log4j.Logger;


import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 16.05.12
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class StartCheckingSite {

    static Logger log = Logger.getLogger(StartCheckingSite.class);


    public void checkSite(Site site){
        PageFetcher checkSiteAvailable = new PageFetcher();
        HtmlPage page = checkSiteAvailable.getHtmlPageForUrl(site.getUrl());
        if(page==null){
            return ;
        }
        if(page.getWebResponse().getStatusCode() != HttpStatus.SUCCESS){
            return;
        }
        checkForms(page);
        
        checkAnchors(page);

    }
    
    
    private void checkForms(HtmlPage page){
        List<HtmlForm> forms =  page.getForms();
        CheckForm checkForm = new CheckForm();
        for(HtmlForm form : forms){
            checkForm.checkForm(page, form);
        }
    }
    
    private void checkAnchors(HtmlPage page){
        CheckAnchor checkAnchor = new CheckAnchor();
        checkAnchor.checkAnchors(page);
    }






}
