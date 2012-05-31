package de.nshevchenko.sqlinjection.type;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.05.12
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class BlindSQLInjection implements SQLInjection{

    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets){
        //TODO
        return null;
    }
    public String sqlInjectionType(){
        return BlindSQLInjection.class.getName();
    }
}

