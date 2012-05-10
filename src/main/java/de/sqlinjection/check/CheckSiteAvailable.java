package de.sqlinjection.check;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import java.io.IOException;

import de.sqlinjection.HttpStatus;



public class CheckSiteAvailable {

    private  Logger log = Logger.getLogger(CheckSiteAvailable.class);
    public boolean isSiteAvailable(String url){
        try{
            WebClient webClient = new WebClient();
            webClient.setThrowExceptionOnScriptError(false);
            final HtmlPage page = webClient.getPage(url);
            return page.getWebResponse().getStatusCode() == HttpStatus.SUCCESS;
        }
        catch(IOException io){
            log.error(io);
        }


        return false;

    }
}
