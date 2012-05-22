package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class UnescapedNumericTest {

    @Test
    public void testShouldAddExpectedString(){
        UnescapedNumeric singleQuotedString = new UnescapedNumeric();
        String generatedValue = singleQuotedString.createInjection("myValue");
        Assert.assertTrue(generatedValue.matches("myValue\\sAND\\s\\d{4}=\\d{4}"));

    }
}
