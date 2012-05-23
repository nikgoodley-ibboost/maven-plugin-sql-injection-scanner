package de.nshevchenko.sqlinjection.check;


import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.nshevchenko.HttpStatus;
import de.nshevchenko.config.Site;
import de.nshevchenko.sqlinjection.check.anchor.CheckAnchor;
import de.nshevchenko.sqlinjection.check.form.CheckForm;
import org.apache.log4j.Logger;


import java.util.List;


public class StartCheckingSite {

    static Logger log = Logger.getLogger(StartCheckingSite.class);


    public ScanResult checkSite(Site site){
        PageFetcher pageFetcher = new PageFetcher();
        HtmlPage page = pageFetcher.getHtmlPageForUrl(site.getUrl());
        ScanResult scanResult = new ScanResult(site);
        if(page==null){
            return null;
        }
        if(page.getWebResponse().getStatusCode() != HttpStatus.SUCCESS){
            return null;
        }
        checkForms(page);
        checkAnchors(page, pageFetcher, scanResult);
        return scanResult;
    }
    
    
    private void checkForms(HtmlPage page){
        List<HtmlForm> forms =  page.getForms();
        CheckForm checkForm = new CheckForm();
        for(HtmlForm form : forms){
            checkForm.checkForm(page, form);
        }
    }
    
    private void checkAnchors(HtmlPage page, PageFetcher pageFetcher, ScanResult scanResult){
        CheckAnchor checkAnchor = new CheckAnchor(scanResult);
        checkAnchor.checkAnchors(page, pageFetcher);
    }






}
