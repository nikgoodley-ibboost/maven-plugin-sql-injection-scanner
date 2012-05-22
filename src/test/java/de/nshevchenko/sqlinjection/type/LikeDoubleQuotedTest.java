package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;


public class LikeDoubleQuotedTest {

    @Test
    public void testShouldAddExpectedString(){
        LikeDoubleQuoted  likeDoubleQuotedString = new LikeDoubleQuoted();
        String generatedValue = likeDoubleQuotedString.createInjection("myValue");
        Assert.assertTrue(generatedValue.matches("myValue\"\\sAND\\s\"\\w{4}\" LIKE \"\\w{4}"));

    }
}
