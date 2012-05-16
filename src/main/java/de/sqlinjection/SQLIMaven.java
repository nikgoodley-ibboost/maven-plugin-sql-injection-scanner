package de.sqlinjection;


import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.sqlinjection.check.CheckConnectionToSite;
import de.sqlinjection.check.CheckIfParamDynamic;
import de.sqlinjection.config.ParseSites;
import de.sqlinjection.config.Site;
import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

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


        CheckIfParamDynamic checkIfParamDynamic = new CheckIfParamDynamic();
        boolean isParamDynamic = checkIfParamDynamic.checkParamDynamic(site.getUrl(), "");
    }


}

