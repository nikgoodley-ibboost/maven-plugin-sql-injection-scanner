package de.nshevchenko.sqlinjection.check.anchor;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.nshevchenko.sqlinjection.check.CompareSites;
import de.nshevchenko.sqlinjection.check.DbPayload;
import de.nshevchenko.sqlinjection.check.PageFetcher;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckAnchor {
    static Logger log = Logger.getLogger(CheckAnchor.class);

    public void checkAnchors(HtmlPage originalPage, PageFetcher pageFetcher) {
        List<HtmlAnchor> anchors = originalPage.getAnchors();
        Set<String> checkedParams = new HashSet<String>();

        for (HtmlAnchor anchor : anchors) {
            List<String> paramsInAnchor = new ArrayList<String>();

            String hrefAttr = anchor.getHrefAttribute();
            int indexOfQuestionMark = hrefAttr.indexOf("?");
            String paramString = hrefAttr.substring(indexOfQuestionMark + 1);
            fetchParamNames( paramsInAnchor, paramString);
            if (!checkedParams.containsAll(paramsInAnchor)) {
                for (String paramNameInAnchor : paramsInAnchor) {
                    try {
                        checkAnchor(pageFetcher, originalPage.getFullyQualifiedUrl(anchor.getHrefAttribute()), paramNameInAnchor);
                    } catch (MalformedURLException e) {
                        //TODO show to the user
                        log.error(e);
                    }
                }

            }
            checkedParams.addAll(paramsInAnchor);

        }

    }

    public void fetchParamNames( List<String> paramsInAnchor, String paramString) {
        Matcher m = Pattern.compile("&").matcher(paramString);

        int start = 0;
        String paramName = null;
        while (m.find(start)) {

            MatchResult matchResult = m.toMatchResult();
            String paramWithValue = paramString.substring(start, matchResult.start());
            paramName = paramWithValue.substring(0, paramWithValue.indexOf("="));
            start = matchResult.end();
            paramsInAnchor.add(paramName);
        }
        //put in the last param
        if(start!=paramString.length()){
            String lastParam = paramString.substring(start);
            paramName = lastParam.substring(0, lastParam.indexOf("=")) ;
            paramsInAnchor.add(paramName);
        }
       

    }
    
    public static void main(String [] args){
        CheckAnchor checkAnchor = new CheckAnchor();
        List<String> list = new ArrayList<String>();
        checkAnchor.fetchParamNames(list, "action=artikel&cat=8&id=17&artlang=de");
        System.out.println(list.size());
    }

    private void checkAnchor(PageFetcher pageFetcher, URL url, String paramNameToCheck) {
        HtmlPage originalPage = pageFetcher.getHtmlPageForUrl(url.toString());
        String urlString = url.toString();
        int indexOfParamValue = urlString.indexOf(paramNameToCheck + "=");
        DbPayload payload = new DbPayload();
        while (payload.hasMorePayloads()) {

            if (indexOfParamValue > 0) {

                int indexOfEndOfValue = urlString.substring(indexOfParamValue).indexOf("&");
                int lengthUntilParamStringEndFromStart = indexOfParamValue + indexOfEndOfValue;
                String urlToModify = new String(urlString.substring(0, lengthUntilParamStringEndFromStart));

                String stringWithParamName = urlToModify.substring(0, urlToModify.lastIndexOf("=")+1);
                String valueToModify = urlToModify.substring(urlToModify.lastIndexOf("=")+1);

                StringBuffer newUrl = new StringBuffer(stringWithParamName);
                newUrl.append(payload.nextPayload(valueToModify));
                newUrl.append(urlString.substring(lengthUntilParamStringEndFromStart));
                HtmlPage newPage = pageFetcher.getHtmlPageForUrl(newUrl.toString());
                CompareSites compareSites = new CompareSites();
                boolean isSameSite = compareSites.compare(originalPage.asText(), newPage.asText());
                log.debug("isSamePage " + isSameSite + " param to check " + paramNameToCheck);
            }


        }


    }
}
