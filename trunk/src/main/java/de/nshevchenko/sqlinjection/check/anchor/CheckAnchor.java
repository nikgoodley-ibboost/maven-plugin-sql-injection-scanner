package de.nshevchenko.sqlinjection.check.anchor;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.nshevchenko.sqlinjection.check.CompareSites;
import de.nshevchenko.sqlinjection.check.DbPayload;
import de.nshevchenko.sqlinjection.check.PageFetcher;
import de.nshevchenko.sqlinjection.check.ScanResult;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;



public class CheckAnchor {
    private Logger log = Logger.getLogger(CheckAnchor.class);
    private ScanResult scanResult;
    private static final String PARAM_VALUE_SEPARATOR = "=";

    public CheckAnchor(ScanResult scanResult){
        this.scanResult = scanResult;
    }

    public void checkAnchors(HtmlPage originalPage, PageFetcher pageFetcher) {
        List<HtmlAnchor> anchors = originalPage.getAnchors();
        Set<String> checkedParams = new HashSet<String>();
        ParamAndValueParserFromUrl paramAndValueParser = new ParamAndValueParserFromUrl();
        for (HtmlAnchor anchor : anchors) {

            Map<String, String> paramsInAnchor = paramAndValueParser.parseParamNamesWithValues(anchor);

            if (!checkedParams.containsAll(paramsInAnchor.keySet())) {
                for (String paramNameInAnchor : paramsInAnchor.keySet()) {

                    try {
                        checkAnchor(pageFetcher, originalPage.getFullyQualifiedUrl(anchor.getHrefAttribute()), paramsInAnchor, paramNameInAnchor);
                    } catch (MalformedURLException e) {
                        //TODO show to the user
                        log.error(e);
                    }
                }

            }
            checkedParams.addAll(paramsInAnchor.keySet());

        }

    }

    private void checkAnchor(PageFetcher pageFetcher, URL url, Map<String, String> paramsInAnchor, String paramNameToCheck) {
        HtmlPage originalPage = pageFetcher.getHtmlPageForUrl(url.toString());
        String urlString = url.toString();
        int indexOfParamValue = urlString.indexOf(paramNameToCheck + PARAM_VALUE_SEPARATOR);
        DbPayload payload = new DbPayload();

        while (payload.hasMorePayloads()) {

            if (indexOfParamValue > 0) {

                StringBuffer newUrl = new StringBuffer(urlString.substring(0, urlString.indexOf("?")+1));

                for(Map.Entry<String, String> paramWithValue: paramsInAnchor.entrySet()){
                    newUrl.append(paramWithValue.getKey());
                    newUrl.append(PARAM_VALUE_SEPARATOR);
                    if(paramWithValue.getKey().equals(paramNameToCheck))
                    {
                         newUrl.append(payload.nextPayload(paramWithValue.getValue()));
                    }
                    else{
                        newUrl.append(paramWithValue.getValue());
                    }

                    newUrl.append("&");
                }
                HtmlPage newPage = pageFetcher.getHtmlPageForUrl(newUrl.toString());
                CompareSites compareSites = new CompareSites();
                boolean isSameSite = compareSites.compare(originalPage.asText(), newPage.asText());
                log.debug("url: "+newUrl.toString()+ " isSamePage " + isSameSite + " param to check " + paramNameToCheck);
                if(isSameSite){
                    scanResult.setSqlInjectionVulnerable(true);
                    scanResult.setVulnerableParamName(paramNameToCheck);
                    scanResult.setVulnerableUrl(urlString);
                    scanResult.setSqlInjectionType(payload.getSqlInjectionType());

                    // don't check for the next injection, one is enough!
                    return;
                }

            }


        }



    }
}
