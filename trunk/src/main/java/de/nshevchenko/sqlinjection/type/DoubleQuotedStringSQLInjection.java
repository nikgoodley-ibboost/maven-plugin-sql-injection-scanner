package de.nshevchenko.sqlinjection.type;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.05.12
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class DoubleQuotedStringSQLInjection implements SQLInjection{
    //action=artikel" AND "IXSZX"="IXSZX&cat=8&id=17&artlang=de

    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets){
        StringBuffer myNewValue = new StringBuffer(oldValue);
        String randomString = RandomStringUtils.randomAlphabetic(4);
        myNewValue.append("\"");
        for(int i=0; i<numberOfBrackets; i++){
            myNewValue.append(")");
        }
        myNewValue.append(" AND ");
        for(int i=0; i<numberOfBrackets; i++){
            myNewValue.append("(");
        }
        myNewValue.append("\"");
        myNewValue.append(randomString);
        myNewValue.append("\"=\"");
        myNewValue.append(randomString);
        return myNewValue.toString();
    }

    public String sqlInjectionType(){
        return "DoubleQuotedStringSQLInjection";
    }
}
