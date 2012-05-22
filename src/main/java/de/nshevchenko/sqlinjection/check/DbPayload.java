package de.nshevchenko.sqlinjection.check;


import de.nshevchenko.sqlinjection.type.SQLInjectionTypes;

public class DbPayload {

    private  SQLInjectionTypes injectionTypes = new SQLInjectionTypes();
    private int indexOfActiveSQlInjectionPayload = 0;
    
    public String nextPayload(String oldValue){
        String payload = injectionTypes.getPayloadForIndex(indexOfActiveSQlInjectionPayload, oldValue);
        indexOfActiveSQlInjectionPayload++;
        return payload;
    }

    public boolean hasMorePayloads(){
        return indexOfActiveSQlInjectionPayload<injectionTypes.totalInjectionTypes();
    }
}
