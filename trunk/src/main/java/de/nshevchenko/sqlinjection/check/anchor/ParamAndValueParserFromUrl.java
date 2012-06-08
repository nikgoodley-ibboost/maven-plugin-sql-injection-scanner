package de.nshevchenko.sqlinjection.check.anchor;


import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamAndValueParserFromUrl {
    private static final String PARAM_VALUE_SEPARATOR = "=";
    private static Logger log = Logger.getLogger(ParamAndValueParserFromUrl.class);
    public Map<String, String> parseParamNamesWithValues(HtmlAnchor anchor) {
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
        //put in the last param if there are any params at all
        if(start!=paramString.length() && indexOfQuestionMark>0){
            String lastParam = paramString.substring(start);
            paramName = lastParam.substring(0, lastParam.indexOf(PARAM_VALUE_SEPARATOR)) ;
            paramsInAnchor.put(paramName, lastParam.substring(lastParam.indexOf(PARAM_VALUE_SEPARATOR)+PARAM_VALUE_SEPARATOR.length()));
        }
        return paramsInAnchor;
    }
}
