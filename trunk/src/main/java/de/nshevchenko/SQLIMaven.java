package de.nshevchenko;


import de.nshevchenko.config.ParseSites;
import de.nshevchenko.config.Site;
import de.nshevchenko.sqlinjection.check.StartCheckingSite;
import org.apache.commons.logging.LogFactory;
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
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        try{
            sqlInjectionMojo.execute();
        }
        catch(Exception ex){
            log.error(ex);
        }
    }


    public void execute() throws MojoExecutionException {
        ParseSites parseSites = new ParseSites();
        ArrayList<Site> sitesToTest = parseSites.parseSites("src/main/config/sites.xml");
        StartCheckingSite start = new StartCheckingSite();
        for(int i=0; i<sitesToTest.size(); i++){
            start.checkSite(sitesToTest.get(i));
        }
    }
    


}

