package de.nshevchenko.sqlinjection.type;


import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class SingleQuotedStringSQLInjectionTest extends TestCase {

    @Test
    public void testShouldAddExpectedString(){
        SingleQuotedStringSQLInjection singleQuotedString = new SingleQuotedStringSQLInjection();
        String generatedValue = singleQuotedString.createInjectionWithNumberOfBrackets("myValue", 0);
        Assert.assertTrue(generatedValue.matches("myValue'\\sAND\\s'\\w{4}'='\\w{4}"));

    }
}
