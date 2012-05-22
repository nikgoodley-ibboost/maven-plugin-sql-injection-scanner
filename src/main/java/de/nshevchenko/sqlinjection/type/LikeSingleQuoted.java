package de.nshevchenko.sqlinjection.type;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.05.12
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class LikeSingleQuoted implements SQLInjection{
    //action=artikel' AND 'IXSZX' LIKE 'IXSZX&cat=8&id=17&artlang=de

    public String createInjection(String oldValue){
        StringBuffer myNewValue = new StringBuffer(oldValue);
        myNewValue.append("' AND '");
        myNewValue.append(RandomStringUtils.randomAlphabetic(4));
        myNewValue.append("' LIKE '");
        myNewValue.append(RandomStringUtils.randomAlphabetic(4));
        return myNewValue.toString();
    }
}
