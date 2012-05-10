package de.sqlinjection;


import de.sqlinjection.check.CheckSiteAvailable;
import de.sqlinjection.config.ParseSites;
import de.sqlinjection.config.Site;
import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.util.ArrayList;


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
        CheckSiteAvailable checkSiteAvailable = new CheckSiteAvailable();
        for(int i=0; i<sitesToTest.size(); i++){
            boolean isSiteAvailable = checkSiteAvailable.isSiteAvailable(sitesToTest.get(i).getUrl());
        }
    }


}

