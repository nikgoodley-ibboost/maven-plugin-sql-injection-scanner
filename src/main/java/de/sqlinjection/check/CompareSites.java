package de.sqlinjection.check;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompareSites {
    // \\s means space
    //\\d  means number
    //\\w word characters (letters, digits, and underscores)
    //* Repeats the previous item zero or more times
    //+ Greedy, so as many items as possible will be matched before trying permutations with less matches of the preceding item, up to the point where the preceding item is matched only once.
    private String regExp = "([\\s]*AND[\\s]*[\\d\\w( )./,'\\#&;*@%]+([\\s]*=[\\s]*[\\d\\w( )./,'\\#&;*@%]*)*)";

    public boolean compare(String origPage, String page){
        Matcher m = Pattern.compile(regExp).matcher(page);

        String regExpResult = null;
        while (m.find())
        {
            regExpResult = m.group();
            if (regExpResult == null)
                continue;
            int index = page.indexOf(regExpResult);
            int length = regExpResult.length();
            String pageWithoutRegExp = page.substring(0, index);
            pageWithoutRegExp = pageWithoutRegExp + page.substring(index + length);
            page = pageWithoutRegExp;
        }
        if (StringUtils.getLevenshteinDistance(origPage, page)<= 0.9D){
            return true;
        }
        return origPage.equals(page);
    }
    
    //public static void main(String )
}
