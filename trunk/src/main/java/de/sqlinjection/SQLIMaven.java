package de.sqlinjection;


import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.sqlinjection.check.CheckConnectionToSite;
import de.sqlinjection.check.CheckIfParamDynamic;
import de.sqlinjection.check.CompareSites;
import de.sqlinjection.config.ParseSites;
import de.sqlinjection.config.Site;
import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This goal will say a message.
 *
 * @goal testsqlinjection
 *
 * execute mvn clean package to make sure the goal has been executed!
 */
public class SQLIMaven extends AbstractMojo {
    static Logger log = Logger.getLogger(SQLIMaven.class);

    public static void main(String [] args){
        SQLIMaven sqlInjectionMojo = new SQLIMaven();
        try{
            sqlInjectionMojo.execute();
        }
        catch(Exception ex){
            log.error(ex);
        }
    }


    public void execute() throws MojoExecutionException {
        ParseSites parseSites = new ParseSites();
        ArrayList<Site> sitesToTest = parseSites.parseSites("src/main/config/mySites.xml");
        for(int i=0; i<sitesToTest.size(); i++){
            checkSite(sitesToTest.get(i));
        }
    }
    
    private void checkSite(Site site){
        CheckConnectionToSite checkSiteAvailable = new CheckConnectionToSite();
        HtmlPage page = checkSiteAvailable.isSiteAvailable(site.getUrl());
        if(page==null){
            return ;
        }
        if(page.getWebResponse().getStatusCode() != HttpStatus.SUCCESS){
            return;
        }
        List<HtmlForm> forms =  page.getForms();
        for(HtmlForm form : forms){
            checkForm(page, form);
        }
        List<HtmlAnchor> anchors = page.getAnchors();
        List<String> checkedParams = new ArrayList<String>();
        for(HtmlAnchor anchor :anchors){
           checkLink(page, anchor);

        }

        CheckIfParamDynamic checkIfParamDynamic = new CheckIfParamDynamic();
        boolean isParamDynamic = checkIfParamDynamic.checkParamDynamic(site.getUrl(), "");
    }
    
    private void checkLink(HtmlPage originalPage, HtmlAnchor anchor){
        log.debug(anchor.getHrefAttribute());
        try{
            HtmlPage newPage = anchor.click();
            CompareSites compareSites = new CompareSites();
            boolean isSameSite = compareSites.compare(originalPage.asText(), newPage.asText());

        }
        catch(IOException ex){
            log.error(ex);
        }
    }
    
    private void checkForm(HtmlPage page, HtmlForm form){
        
    }


}

