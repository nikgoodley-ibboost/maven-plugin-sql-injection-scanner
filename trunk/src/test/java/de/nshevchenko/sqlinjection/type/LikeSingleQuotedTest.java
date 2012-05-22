package de.nshevchenko.sqlinjection.type;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 22.05.12
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public class LikeSingleQuotedTest {

    @Test
    public void testShouldAddExpectedString(){
        LikeSingleQuoted  singleQuotedString = new LikeSingleQuoted();
        String generatedValue = singleQuotedString.createInjection("myValue");
        System.out.println("generatedValue "+generatedValue);
        Assert.assertTrue(generatedValue.matches("myValue'\\sAND\\s'\\w{4}' LIKE '\\w{4}"));

    }
}
