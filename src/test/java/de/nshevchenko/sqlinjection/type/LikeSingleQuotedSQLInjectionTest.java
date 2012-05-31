package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class LikeSingleQuotedSQLInjectionTest {

    @Test
    public void testShouldAddExpectedString(){
        LikeSingleQuotedSQLInjection singleQuotedString = new LikeSingleQuotedSQLInjection();
        String generatedValue = singleQuotedString.createInjectionWithNumberOfBrackets("myValue", 0);
        Assert.assertTrue(generatedValue.matches("myValue'\\sAND\\s'\\w{4}' LIKE '\\w{4}"));

    }
}
