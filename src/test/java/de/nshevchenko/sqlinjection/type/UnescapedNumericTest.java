package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class UnescapedNumericTest {

    @Test
    public void testShouldAddExpectedString(){
        UnescapedNumeric singleQuotedString = new UnescapedNumeric();
        String generatedValue = singleQuotedString.createInjection("myValue");
        System.out.println("generated string "+generatedValue);
        Assert.assertTrue(generatedValue.matches("myValue'\\sAND\\s'\\w{4}'='\\w{4}"));

    }
}
