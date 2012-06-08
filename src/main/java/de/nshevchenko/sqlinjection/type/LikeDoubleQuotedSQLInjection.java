package de.nshevchenko.sqlinjection.type;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.05.12
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class LikeDoubleQuotedSQLInjection implements SQLInjection{

    //action=artikel" AND "IXSZX" LIKE "IXSZX&cat=8&id=17&artlang=de

    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets){
        String randomString = RandomStringUtils.randomAlphabetic(4);
        StringBuffer myNewValue = new StringBuffer(oldValue);
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
        myNewValue.append("\" LIKE \"");
        myNewValue.append(randomString);
        return myNewValue.toString();
    }

    public String sqlInjectionType(){
        return "LikeDoubleQuotedSQLInjection";
    }
}
