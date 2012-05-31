package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class LikeDoubleQuotedSQLInjectionTest {

    @Test
    public void testShouldAddExpectedStringWithZeroBrackets(){
        LikeDoubleQuotedSQLInjection likeDoubleQuotedString = new LikeDoubleQuotedSQLInjection();
        String generatedValue = likeDoubleQuotedString.createInjectionWithNumberOfBrackets("myValue", 0);
        Assert.assertTrue(generatedValue.matches("myValue\"\\sAND\\s\"\\w{4}\" LIKE \"\\w{4}"));

    }

    @Test
    public void testShouldAddExpectedStringForOneBracket(){
        LikeDoubleQuotedSQLInjection likeDoubleQuotedString = new LikeDoubleQuotedSQLInjection();
        String generatedValue = likeDoubleQuotedString.createInjectionWithNumberOfBrackets("myValue", 1);
        Assert.assertTrue(generatedValue.matches("myValue\"\\)\\sAND\\s\\(\"\\w{4}\" LIKE \"\\w{4}"));


    }
}
