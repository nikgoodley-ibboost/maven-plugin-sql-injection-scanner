package de.nshevchenko.sqlinjection.check;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import java.io.IOException;


public class PageFetcher {

    private  Logger log = Logger.getLogger(PageFetcher.class);
    private WebClient webClient = new WebClient();

    public HtmlPage getHtmlPageForUrl(String url){

       HtmlPage page = pageForUrl(url);
       return page;

    }
    
    private HtmlPage pageForUrl(String url){
        try{

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
