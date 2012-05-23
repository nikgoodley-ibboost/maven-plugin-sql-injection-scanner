package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class DoubleQuotedStringSQLInjectionTest {
    @Test
    public void testShouldAddExpectedString(){
        DoubleQuotedStringSQLInjection singleQuotedString = new DoubleQuotedStringSQLInjection();
        String generatedValue = singleQuotedString.createInjection("myValue");
        Assert.assertTrue(generatedValue.matches("myValue\"\\sAND\\s\"\\w{4}\"=\"\\w{4}"));

    }
}
