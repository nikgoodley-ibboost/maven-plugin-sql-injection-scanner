package de.nshevchenko.sqlinjection.type;


public class UnescapedNumericSQLInjection implements SQLInjection{

    //action=artikel AND 229=229&cat=8&id=17&artlang=de
    //action=artikel&cat=8&id=17&artlang=de AND 95=95
    public String createInjection(String oldValue){
        int randomInt = (int)(Math.random()*10000);
        StringBuffer myNewValue = new StringBuffer(oldValue);
        myNewValue.append(" AND ");
        myNewValue.append(randomInt);
        myNewValue.append("=");
        myNewValue.append(randomInt);
        return myNewValue.toString();
    }

    public String sqlInjectionType(){
        return UnescapedNumericSQLInjection.class.getName();
    }
}
