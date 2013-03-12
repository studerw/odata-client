/*
 * $Id$
 *
 * Copyright (c) 2013 William Studer
 */
package com.studerb.odata.atom.convert;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

public class StringConversionTest {

    final static Logger log = Logger.getLogger(StringConversionTest.class);

    @Test
    public void stringToDate() throws Throwable {
        String stringDate = "2010-03-01T16:13:20-05:00";
        DefaultDateConverter converter = new DefaultDateConverter();
        Date date = (Date) converter.convertToType(java.util.Date.class, stringDate);
        assertTrue(date.getClass() == Date.class);
        DateTime dt = new DateTime(date);

        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        log.debug("Date formatted: " + fmt.print(dt));

        assertTrue("year should be 2010", dt.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + dt.getMonthOfYear(), dt.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", dt.getDayOfMonth() == 1);
        assertTrue("Hour is 16", dt.getHourOfDay() == 16);
        assertTrue("Minute is 13", dt.getMinuteOfHour() == 13);
        assertTrue("Second is 20", dt.getSecondOfMinute() == 20);
        assertTrue("Millisecond is empty", dt.getMillisOfSecond() == 0);
        log.debug("Zone: " + dt.getZone().toString());

        Date date2 = (Date) new DateConverter().convert(Date.class, stringDate);
        assertTrue(date.getClass() == Date.class);
        final FastDateFormat isoDateTimeFormat = DateFormatUtils.ISO_DATETIME_FORMAT;
        log.debug("using beanDateFormater: " + isoDateTimeFormat.format(date2));
    }

    @Test
    public void stringToDateTimeOffset() throws Throwable {
        String stringDate = "2010-03-01T16:13:20-05:00";
        JodaDateTimeConverter converter = new JodaDateTimeConverter();
        DateTime dt = (DateTime) converter.convertToType(DateTime.class, stringDate);
        assertTrue(dt.getClass() == DateTime.class);

        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        log.debug("Date formatted: " + fmt.print(dt));

        assertTrue("year should be 2010", dt.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + dt.getMonthOfYear(), dt.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", dt.getDayOfMonth() == 1);
        // hours change with real timezone handling
        assertTrue("Hour is 16", dt.getHourOfDay() == 16);
        assertTrue("Minute is 13", dt.getMinuteOfHour() == 13);
        assertTrue("Second is 20", dt.getSecondOfMinute() == 20);
        assertTrue("Millisecond is empty", dt.getMillisOfSecond() == 0);
        //assertTrue(dt.getZone().)
        log.debug("Zone: " + dt.getZone().toString());

    }

    @Test
    public void stringToTime() {
        fail("Time not successfully created");
    }

    @Test
    public void stringToDateTime() {
        fail("DateTime not successfully created");
    }

    @Test
    public void stringToCalendar() {
        fail("Calender not successfully created");
    }

    @Test
    public void stringToByte() {
        fail("Byte not successfully created");
    }

    @Test
    public void stringToBinary() {
        fail("Binary[] not successfully created");
    }

    @Test
    public void stringToGuid() {
        fail("GUID not successfully created");
    }

    @Test
    public void stringToSingle() {
        fail("Single not successfully created");
    }

    @Test
    public void stringToDecimal() {
        fail("Decimal not successfully created");
    }

    @Test
    public void stringToSByte() {
        fail("SByte not successfully created");
    }

}
