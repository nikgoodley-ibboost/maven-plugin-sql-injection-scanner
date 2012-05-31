package de.nshevchenko.sqlinjection.type;

public interface SQLInjection {

    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets);
    public String sqlInjectionType();
}
