package de.sqlinjection.check;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import java.io.IOException;

import de.sqlinjection.HttpStatus;



public class CheckConnectionToSite {

    private  Logger log = Logger.getLogger(CheckConnectionToSite.class);
    public boolean isSiteAvailable(String url){

       HtmlPage page = pageForUrl(url);
       if(page==null){
           return false;
       }
       return page.getWebResponse().getStatusCode() == HttpStatus.SUCCESS;

    }
    
    public String getSiteSourceCode(String url){
        HtmlPage htmlPage = pageForUrl(url);
        if(htmlPage!=null)
            return htmlPage.asText();
        else
            return null;
    }
    
    private HtmlPage pageForUrl(String url){
        try{
            WebClient webClient = new WebClient();
            webClient.setThrowExceptionOnScriptError(false);
            url = url.replace(" ", "%20");
            return  webClient.getPage(url);
        }
        catch(IOException io){
            log.error(io);
        }
        return null;
    }
}
