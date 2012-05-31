package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class DoubleQuotedStringSQLInjectionTest {
    @Test
    public void testShouldAddExpectedStringWithZeroBrackets(){
        DoubleQuotedStringSQLInjection singleQuotedString = new DoubleQuotedStringSQLInjection();
        String generatedValue = singleQuotedString.createInjectionWithNumberOfBrackets("myValue", 0);
        Assert.assertTrue(generatedValue.matches("myValue\"\\sAND\\s\"\\w{4}\"=\"\\w{4}"));

    }

    @Test
    public void testShouldAddExpectedStringForOneBracket(){
        DoubleQuotedStringSQLInjection singleQuotedString = new DoubleQuotedStringSQLInjection();
        String generatedValue = singleQuotedString.createInjectionWithNumberOfBrackets("myValue", 1);
        Assert.assertTrue(generatedValue.matches("myValue\"\\)\\sAND\\s\\(\"\\w{4}\"=\"\\w{4}"));

    }
}
