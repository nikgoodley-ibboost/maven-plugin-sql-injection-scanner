package de.sqlinjection.check;


public class DbPayload {
    public String payload(String newValue){
        StringBuffer payload = new StringBuffer();
        payload.append(" "+newValue);
        
        return payload.toString();
    }
}
