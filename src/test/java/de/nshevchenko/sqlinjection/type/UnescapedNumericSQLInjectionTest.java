package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class UnescapedNumericSQLInjectionTest {

    @Test
    public void testShouldAddExpectedString(){
        UnescapedNumericSQLInjection singleQuotedString = new UnescapedNumericSQLInjection();
        String generatedValue = singleQuotedString.createInjection("myValue");
        Assert.assertTrue(generatedValue.matches("myValue\\sAND\\s\\d{4}=\\d{4}"));

    }
}
