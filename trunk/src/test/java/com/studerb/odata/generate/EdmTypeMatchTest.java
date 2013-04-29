package com.studerb.odata.generate;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/test-context.xml" })
public class EdmTypeMatchTest {

    @Inject ApplicationContext appContext;

    @BeforeClass
    public static void before() throws IOException {
    }

    @AfterClass
    public static void after() throws IOException {
    }

    @Test
    public void regexBinaryMatchTest() throws Exception {
        String noPrefix = "Binary";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexByteMatchTest() throws Exception {
        String noPrefix = "Byte";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexNullMatchTest() throws Exception {
        String noPrefix = "Null";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexBooleanMatchTest() throws Exception {
        String noPrefix = "Boolean";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexDateTimeMatchTest() throws Exception {
        String noPrefix = "DateTime";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexDecimalMatchTest() throws Exception {
        String noPrefix = "Decimal";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexDoubleMatchTest() throws Exception {
        String noPrefix = "Double";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexSingleMatchTest() throws Exception {
        String noPrefix = "Single";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexGuidMatchTest() throws Exception {
        String noPrefix = "Guid";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexInt16MatchTest() throws Exception {
        String noPrefix = "Int16";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexInt32MatchTest() throws Exception {
        String noPrefix = "Int32";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexInt64MatchTest() throws Exception {
        String noPrefix = "Int64";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexSByteMatchTest() throws Exception {
        String noPrefix = "SByte";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexStringMatchTest() throws Exception {
        String noPrefix = "String";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexDateTimeOffsetMatchTest() throws Exception {
        String noPrefix = "DateTimeOffset";
        String full = "Edm." + noPrefix;
        String toMatchRegex = "(Edm\\.)?" + noPrefix;
        assertTrue(full.matches(toMatchRegex));
        assertTrue(noPrefix.matches(toMatchRegex));
    }

    @Test
    public void regexTimeMatchTest() throws Exception {
        String edmTime = "Edm.Time";
        String time = "Time";
        String toMatchRegex = "(Edm\\.)?Time";
        assertTrue(edmTime.matches(toMatchRegex));
        assertTrue(time.matches(toMatchRegex));
    }

}