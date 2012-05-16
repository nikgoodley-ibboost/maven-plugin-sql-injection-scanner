package de.nshevchenko.sqlinjection.check;


public class CheckIfParamDynamic {


    public boolean checkParamDynamic(String url, String paramName){
        DbPayload dbPayload = new DbPayload();
        int randomInt = (int)Math.random()*100;
        dbPayload.payload(String.valueOf(randomInt));
        int indexOfParamValue = url.indexOf(paramName)+paramName.length()+1;//+1 because we have equals sign        

        return false;
    }
}
