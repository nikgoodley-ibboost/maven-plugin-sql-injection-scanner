package de.nshevchenko.sqlinjection.type;


import java.util.ArrayList;
import java.util.List;

public class SQLInjectionTypes {
    
    private List<SQLInjection> sqlInjectionTypes = new ArrayList<SQLInjection>();

    public SQLInjectionTypes(){
        sqlInjectionTypes.add(new SingleQuotedStringSQLInjection());
        sqlInjectionTypes.add(new DoubleQuotedStringSQLInjection());
        sqlInjectionTypes.add(new LikeSingleQuotedSQLInjection());
        sqlInjectionTypes.add(new LikeDoubleQuotedSQLInjection());
        sqlInjectionTypes.add(new UnescapedNumericSQLInjection());
        //TODO add more types when they are implemented! blind, errorbased!
    }
    
    public String getPayloadForIndex(int index, String oldValue){

        return sqlInjectionTypes.get(index).createInjection(oldValue);
    }
    
    public int totalInjectionTypes(){
        return sqlInjectionTypes.size();
    }
    
    
}
