package com.studerb.odata.atom.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

public class StringConversionTest {
    final static FastDateFormat isoDateTimeFormat = DateFormatUtils.ISO_DATETIME_FORMAT;
    final static Logger log = Logger.getLogger(StringConversionTest.class);

    @Test
    public void stringToDate() throws Throwable {
        String stringDate = "2010-03-01T16:13:20-05:00";
        DateConverter converter = new DateConverter();
        Date date = (Date) converter.convert(java.util.Date.class, stringDate);
        assertTrue(date.getClass() == Date.class);
        log.debug("using DefaultDateFormater: " + isoDateTimeFormat.format(date));

        DateTime dt = new DateTime(date, DateTimeZone.UTC);

        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        log.debug("Date formatted: " + fmt.print(dt));

        assertTrue("year should be 2010", dt.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + dt.getMonthOfYear(), dt.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", dt.getDayOfMonth() == 1);
        assertTrue("Hour is 21", dt.getHourOfDay() == 21);
        assertTrue("Minute is 13", dt.getMinuteOfHour() == 13);
        assertTrue("Second is 20", dt.getSecondOfMinute() == 20);
        assertTrue("Millisecond is empty", dt.getMillisOfSecond() == 0);
        assertEquals("zone should be UTC: ", dt.getZone().getID(), "UTC");
        log.debug("Zone: " + dt.getZone().toString());
    }

    @Test
    public void stringToDateTime() throws Throwable {
        String stringDate = "2010-03-01T16:13:20-05:00";
        JodaDateTimeConverter converter = new JodaDateTimeConverter();
        DateTime dt = (DateTime) converter.convertToType(DateTime.class, stringDate);
        assertTrue(dt.getClass() == DateTime.class);
        dt = dt.withZone(DateTimeZone.UTC);
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        log.debug("Date formatted: " + fmt.print(dt));

        assertTrue("year should be 2010", dt.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + dt.getMonthOfYear(), dt.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", dt.getDayOfMonth() == 1);
        // hours change with real timezone handling
        assertTrue("Hour is 21", dt.getHourOfDay() == 21);
        assertTrue("Minute is 13", dt.getMinuteOfHour() == 13);
        assertTrue("Second is 20", dt.getSecondOfMinute() == 20);
        assertTrue("Millisecond is empty", dt.getMillisOfSecond() == 0);
        assertEquals("Zone should be UTC", dt.getZone().getID(), "UTC");
        log.debug("Zone: " + dt.getZone().toString());

        dt = dt.withZone(DateTimeZone.forOffsetHours(-5));
        log.debug("Date formatted: " + fmt.print(dt));
        log.debug("Zone After setting back 5 hours: " + dt.getZone().toString());
        assertTrue("Hour is back to 16", dt.getHourOfDay() == 16);
    }

    @Test
    public void defaultBeanUtilsConvertTest() {

    }

    @Test
    public void stringToSqlTime() {
        String time = "13:20:00";
        SqlTimeConverter converter = new SqlTimeConverter();
        java.sql.Time sqlTime = (java.sql.Time) converter.convert(java.sql.Time.class, time);
        log.debug(sqlTime.toString());
    }

    @Test
    public void stringWithPrecisionToSqlTime() {
        String time = "13:20:00.123";
        SqlTimeConverter converter = new SqlTimeConverter();
        java.sql.Time sqlTime = (java.sql.Time) converter.convert(java.sql.Time.class, time);
        log.debug(sqlTime.toString());
    }

    @Test
    public void stringWithPrecisionTimeZoneToSqlTime() {
        String time = "13:20:00.234-05:00";
        SqlTimeConverter converter = new SqlTimeConverter();
        java.sql.Time sqlTime = (java.sql.Time) converter.convert(java.sql.Time.class, time);
        log.debug(sqlTime.toString());
    }

    @Test
    public void stringWithTimeZoneToSqlTime() {
        String time = "13:20:00-05:00";
        SqlTimeConverter converter = new SqlTimeConverter();
        java.sql.Time sqlTime = (java.sql.Time) converter.convert(java.sql.Time.class, time);
        log.debug(sqlTime.toString());
    }

    @Test
    public void stringToDateTimeOffset() {
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

    @Test
    public void stringToBigDecimal() {
        fail("SByte not successfully created");
    }

}
