package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class LikeSingleQuotedTest {

    @Test
    public void testShouldAddExpectedString(){
        LikeSingleQuoted  singleQuotedString = new LikeSingleQuoted();
        String generatedValue = singleQuotedString.createInjection("myValue");
        System.out.println("generatedValue "+generatedValue);
        Assert.assertTrue(generatedValue.matches("myValue'\\sAND\\s'\\w{4}' LIKE '\\w{4}"));

    }
}
