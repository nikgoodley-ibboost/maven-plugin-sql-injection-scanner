package de.nshevchenko.sqlinjection.check.anchor;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.nshevchenko.sqlinjection.check.CompareSites;
import de.nshevchenko.sqlinjection.check.DbPayload;
import de.nshevchenko.sqlinjection.check.PageFetcher;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckAnchor {
    private Logger log = Logger.getLogger(CheckAnchor.class);

    private static final String PARAM_VALUE_SEPARATOR = "=";
    
    public void checkAnchors(HtmlPage originalPage, PageFetcher pageFetcher) {
        List<HtmlAnchor> anchors = originalPage.getAnchors();
        Set<String> checkedParams = new HashSet<String>();

        for (HtmlAnchor anchor : anchors) {


            Map<String, String> paramsInAnchor = parseParamNamesWithValues(anchor);

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

    private Map<String, String> parseParamNamesWithValues(HtmlAnchor anchor) {
        String hrefAttr = anchor.getHrefAttribute();
        int indexOfQuestionMark = hrefAttr.indexOf("?");
        String paramString = hrefAttr.substring(indexOfQuestionMark + 1);

        Matcher m = Pattern.compile("&").matcher(paramString);

        int start = 0;
        String paramName = null;
        Map<String, String> paramsInAnchor = new HashMap<String, String>();
        while (m.find(start)) {

            MatchResult matchResult = m.toMatchResult();
            String paramWithValue = paramString.substring(start, matchResult.start());
            paramName = paramWithValue.substring(0, paramWithValue.indexOf(PARAM_VALUE_SEPARATOR));
            start = matchResult.end();
            paramsInAnchor.put(paramName, paramWithValue.substring(paramWithValue.indexOf(PARAM_VALUE_SEPARATOR)+PARAM_VALUE_SEPARATOR.length()));
        }
        //put in the last param
        if(start!=paramString.length()){
            String lastParam = paramString.substring(start);
            paramName = lastParam.substring(0, lastParam.indexOf(PARAM_VALUE_SEPARATOR)) ;
            paramsInAnchor.put(paramName, lastParam.substring(lastParam.indexOf(PARAM_VALUE_SEPARATOR)+PARAM_VALUE_SEPARATOR.length()));
        }
        return paramsInAnchor;
    }


    private boolean checkAnchor(PageFetcher pageFetcher, URL url, Map<String, String> paramsInAnchor, String paramNameToCheck) {
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
                    return isSameSite;
                }

            }


        }
        return false;


    }
}
