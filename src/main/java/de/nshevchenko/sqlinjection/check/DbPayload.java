package de.nshevchenko.sqlinjection.check;


import de.nshevchenko.sqlinjection.type.SQLInjectionTypes;
import org.apache.log4j.Logger;

public class DbPayload {
    private Logger log = Logger.getLogger(DbPayload.class);
    private  SQLInjectionTypes injectionTypes = new SQLInjectionTypes();
    private int indexOfActiveSQlInjectionPayload = 0;
    
    public String nextPayload(String oldValue, int numberOfBrackets){
        String payload = injectionTypes.getPayloadForIndex(indexOfActiveSQlInjectionPayload, oldValue, numberOfBrackets);
        log.debug("SQLInjection TYPE: " +injectionTypes.getName(indexOfActiveSQlInjectionPayload)+" "+injectionTypes.getPayloadForIndex(indexOfActiveSQlInjectionPayload, oldValue, numberOfBrackets));
        indexOfActiveSQlInjectionPayload++;
        return payload;
    }

    public boolean hasMorePayloads(){
        return indexOfActiveSQlInjectionPayload<injectionTypes.totalInjectionTypes();
    }
    
    public String getSqlInjectionType(){
        return injectionTypes.getSqlInjectionType(indexOfActiveSQlInjectionPayload);
    }
}
