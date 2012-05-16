package de.nshevchenko.sqlinjection.check;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 16.05.12
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class CheckAnchor {
    static Logger log = Logger.getLogger(CheckAnchor.class);
    public void checkAnchors(HtmlPage originalPage){
        List<HtmlAnchor> anchors = originalPage.getAnchors();
        Set<String> checkedParams = new HashSet<String>();

        for(HtmlAnchor anchor :anchors){
            List<String> paramsInAnchor = new ArrayList<String>();

            String hrefAttr = anchor.getHrefAttribute();
            int indexOfQuestionMark =  hrefAttr.indexOf("?");
            String paramString = hrefAttr.substring(indexOfQuestionMark+1);
            Matcher m = Pattern.compile("&").matcher(paramString);

            int start = 0;
            while (m.find())
            {

                MatchResult matchResult = m.toMatchResult();
                String paramWithValue = paramString.substring(start, matchResult.start());
                String paramName = paramWithValue.substring(0, paramWithValue.indexOf("="));
                start = matchResult.end();
                paramsInAnchor.add(paramName);
            }

            if(!checkedParams.containsAll(paramsInAnchor)){
                log.debug("start checking the params: "+paramsInAnchor);
                try{
                    CheckIfParamDynamic checkIfParamDynamic = new CheckIfParamDynamic();
                    boolean isParamDynamic = checkIfParamDynamic.checkParamDynamic(anchor.getHrefAttribute(), "");
                    HtmlPage newPage = anchor.click();
                    CompareSites compareSites = new CompareSites();
                    boolean isSameSite = compareSites.compare(originalPage.asText(), newPage.asText());

                }
                catch(IOException ex){
                    log.error(ex);
                }
            }

            checkedParams.addAll(paramsInAnchor);


        }



    }
}
