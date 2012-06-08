package de.nshevchenko.sqlinjection.type;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SQLInjectionTypes {
    private Logger log = Logger.getLogger(SQLInjectionTypes.class);
    private List<SQLInjection> sqlInjectionTypes = new ArrayList<SQLInjection>();

    public SQLInjectionTypes(){
        sqlInjectionTypes.add(new SingleQuotedStringSQLInjection());
        sqlInjectionTypes.add(new DoubleQuotedStringSQLInjection());
        sqlInjectionTypes.add(new LikeSingleQuotedSQLInjection());
        sqlInjectionTypes.add(new LikeDoubleQuotedSQLInjection());
        sqlInjectionTypes.add(new UnescapedNumericSQLInjection());
        //TODO add more types when they are implemented! blind, errorbased!
    }
    
    public String getPayloadForIndex(int index, String oldValue, int numberOfBrackets){
        return sqlInjectionTypes.get(index).createInjectionWithNumberOfBrackets(oldValue, numberOfBrackets);
    }

    public String getName(int index){
        return sqlInjectionTypes.get(index).sqlInjectionType();
    }
    
    public int totalInjectionTypes(){
        return sqlInjectionTypes.size();
    }
    
    public String getSqlInjectionType(int index){
        return sqlInjectionTypes.get(index).sqlInjectionType();
    }
    
    
}
