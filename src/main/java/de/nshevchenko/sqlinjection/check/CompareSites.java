package de.nshevchenko.sqlinjection.check;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompareSites {
    private Logger log = Logger.getLogger(CompareSites.class);
    String specialChars = "( )./,'\\#&;*@%";
    String numbersAndWordChars = "\\d\\w";
    String specialCharsAndNumberAndWordChars =numbersAndWordChars+specialChars ;
    String space = "\\s";
    String repeatZeroOrMore = "*";
    String repeatZeroOrMoreTimesSpace = "["+space+"]"+repeatZeroOrMore;
    String positiveLookAhead = "=";
    //\\w word characters (letters, digits, and underscores)
    // \\s means space
    //\\d  means number
    //\\w word characters (letters, digits, and underscores)
    //* Repeats the previous item zero or more times
    //+ Greedy, so as many items as possible will be matched before trying permutations with less matches of the preceding item, up to the point where the preceding item is matched only once.
    private String regExp = "("+repeatZeroOrMoreTimesSpace+"AND"+
            repeatZeroOrMoreTimesSpace+
            "["+specialCharsAndNumberAndWordChars+"]+("+
            repeatZeroOrMoreTimesSpace+positiveLookAhead+repeatZeroOrMoreTimesSpace+
            "["+specialCharsAndNumberAndWordChars+"]"+repeatZeroOrMore+")"+repeatZeroOrMore+")";

    public boolean compare(String origPage, String page){
        page = stripPage(page);
        int levenshteinDistance  =  StringUtils.getLevenshteinDistance(origPage, page);
        log.debug("levenstein distance "+levenshteinDistance+ " is less than 0.9D "+(levenshteinDistance<=0.9D)+" is less than 0.9 "+(levenshteinDistance<=0.9));
        if (levenshteinDistance<= 9 && levenshteinDistance!=0){
            return true;
        }
        if(levenshteinDistance==0){
            log.debug("levenstein distance is null");
        }
        return origPage.equals(page);
    }

    public String stripPage(String page ){
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
        return page;
    }

    public static void main (String [] args){
        CompareSites compareSites = new CompareSites();
        String page = "sjhdbc AND 1=1 #";
        String pageAfterStrip = compareSites.stripPage(page);
        System.out.println("pageAfterStrip "+pageAfterStrip);
    }

}
