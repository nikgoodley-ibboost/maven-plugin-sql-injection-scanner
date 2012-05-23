package de.nshevchenko.sqlinjection.type;

public interface SQLInjection {
    public String createInjection(String oldValue);
    public String sqlInjectionType();
}
