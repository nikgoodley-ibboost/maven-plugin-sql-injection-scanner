package de.nshevchenko;


import de.nshevchenko.config.ParseSites;
import de.nshevchenko.config.Site;
import de.nshevchenko.sqlinjection.check.ScanResult;
import de.nshevchenko.sqlinjection.check.StartCheckingSite;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
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
    private static Logger log = Logger.getLogger(SQLIMaven.class);

    public static void main(String [] args){
        SQLIMaven sqlInjectionMojo = new SQLIMaven();
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.FATAL);
        try{
            sqlInjectionMojo.execute();
        }
        catch(Exception ex){
            log.error(ex);
            ex.printStackTrace();
        }
    }


    public void execute() throws MojoExecutionException {
        String pathToConfigFile = System.getProperty("PATH_TO_CONFIG_FILE");
        if(StringUtils.isEmpty(pathToConfigFile)){
           pathToConfigFile = "src/main/config/sites.xml";
        }

        ParseSites parseSites = new ParseSites();
        ArrayList<Site> sitesToTest = parseSites.parseSites(pathToConfigFile);
        StartCheckingSite start = new StartCheckingSite();
        for(int i=0; i<sitesToTest.size(); i++){
            ScanResult scanResult = start.checkSite(sitesToTest.get(i));
            if(scanResult.isSqlInjectionVulnerable()){
                log.warn("VULNERABLE TO SQLI Type: "+scanResult.getSqlInjectionType()+" for param name: "+scanResult.getVulnerableParamName()+ " in url: "+scanResult.getVulnerableUrl());
            }
        }
    }
    


}

