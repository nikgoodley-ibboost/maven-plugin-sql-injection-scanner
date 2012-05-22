package de.nshevchenko.sqlinjection.type;


import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class SingleQuotedStringTest extends TestCase {

    @Test
    public void testShouldAddExpectedString(){
        SingleQuotedString singleQuotedString = new SingleQuotedString();
        String generatedValue = singleQuotedString.createInjection("myValue");
        Assert.assertTrue(generatedValue.matches("myValue'\\sAND\\s'\\w{4}'='\\w{4}"));

    }
}
