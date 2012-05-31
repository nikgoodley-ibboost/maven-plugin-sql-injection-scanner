package de.nshevchenko.sqlinjection.type;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.05.12
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class ErrorBasedSQLInjection implements SQLInjection{
    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets){
     //TODO
        return null;
    }

    public String sqlInjectionType(){
        return ErrorBasedSQLInjection.class.getName();
    }
}
