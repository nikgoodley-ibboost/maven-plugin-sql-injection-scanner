package de.sqlinjection.check;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.List;

import de.sqlinjection.HttpStatus;



public class CheckConnectionToSite {

    private  Logger log = Logger.getLogger(CheckConnectionToSite.class);
    public HtmlPage isSiteAvailable(String url){

       HtmlPage page = pageForUrl(url);

        
      return page;

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
