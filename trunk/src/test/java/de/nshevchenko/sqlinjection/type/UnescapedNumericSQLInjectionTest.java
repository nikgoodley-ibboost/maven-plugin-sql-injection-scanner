package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class UnescapedNumericSQLInjectionTest {

    @Test
    public void testShouldAddExpectedStringWithZeroRoundBrackets(){
        UnescapedNumericSQLInjection singleQuotedString = new UnescapedNumericSQLInjection();
        String generatedValue = singleQuotedString.createInjectionWithNumberOfBrackets("myValue", 0);
        Assert.assertTrue(generatedValue.matches("myValue\\sAND\\s\\d{4}=\\d{4}"));

    }

    @Test
    public void testShouldAddExpectedStringForOneBracket(){
        UnescapedNumericSQLInjection singleQuotedString = new UnescapedNumericSQLInjection();
        String generatedValue = singleQuotedString.createInjectionWithNumberOfBrackets("myValue", 1);
        Assert.assertTrue(generatedValue.matches("myValue\\)\\sAND\\s\\(\\d{4}=\\d{4}"));


    }
}
