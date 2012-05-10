package de.sqlinjection.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 10.05.12
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class ParseSites {

    static Logger log = Logger.getLogger(ParseSites.class);
    public ArrayList<Site> parseSites(String filePath){

        XMLConfiguration xmlConfiguration = readConfiguration(filePath);
        ArrayList<String> urls = readListProperty(xmlConfiguration, "site.url");
        ArrayList<String> usernames = readListProperty(xmlConfiguration, "authentication.username");
        ArrayList<String> passwords = readListProperty(xmlConfiguration, "authentication.password");
        ArrayList<Site> sites = new ArrayList<Site>();

        for(int i=0; i<urls.size(); i++){
            Site site = new Site();
            site.setUrl(urls.get(i));
            site.setUsername(usernames.get(i));
            site.setPassword(passwords.get(i));
            sites.add(site);
        }
        return sites;
    }

    private ArrayList readListProperty(XMLConfiguration xmlConfiguration, String propertyName){
        ArrayList listOfSites = null;
        if(xmlConfiguration.getProperty(propertyName) instanceof ArrayList) {
            listOfSites = (ArrayList)xmlConfiguration.getProperty(propertyName) ;

        }
        if(xmlConfiguration.getProperty(propertyName) instanceof String){
            listOfSites = new ArrayList();
            listOfSites.add(xmlConfiguration.getProperty(propertyName));

        }
        return listOfSites;
    }

    private  XMLConfiguration readConfiguration(String filePath){
        XMLConfiguration xmlConfiguration = null;
        try{
            File configurationFile =  new File(filePath);
            xmlConfiguration = new XMLConfiguration(configurationFile);
        }
        catch(ConfigurationException ce){
            log.error(ce);
        }
        return xmlConfiguration;

    }

}
