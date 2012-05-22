package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class DoubleQuotedStringTest {
    @Test
    public void testShouldAddExpectedString(){
        DoubleQuotedString singleQuotedString = new DoubleQuotedString();
        String generatedValue = singleQuotedString.createInjection("myValue");
        Assert.assertTrue(generatedValue.matches("myValue\"\\sAND\\s\"\\w{4}\"=\"\\w{4}"));

    }
}
