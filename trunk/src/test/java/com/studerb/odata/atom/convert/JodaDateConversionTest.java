package com.studerb.odata.atom.convert;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

public class JodaDateConversionTest {
    final static Logger log = Logger.getLogger(JodaDateConversionTest.class);

    @Test
    public void edmDateTimeTest() throws Throwable {
        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeParser();

        String edmDT1 = "2010-03-01T16:13:20.234";
        LocalDateTime localDT = fmt.parseLocalDateTime(edmDT1);
        log.debug("Date formatted: " + localDT.toString());

        assertTrue("year should be 2010", localDT.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + localDT.getMonthOfYear(), localDT.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", localDT.getDayOfMonth() == 1);
        assertTrue("Hour is 21", localDT.getHourOfDay() == 16);
        assertTrue("Minute is 13", localDT.getMinuteOfHour() == 13);
        assertTrue("Second is 20", localDT.getSecondOfMinute() == 20);
        assertTrue("Millisecond is 234", localDT.getMillisOfSecond() == 234);

        String edmDT2 = "2010-03-01T16:13:20.23";
        localDT = fmt.parseLocalDateTime(edmDT2);
        log.debug("Date formatted: " + localDT.toString());

        assertTrue("year should be 2010", localDT.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + localDT.getMonthOfYear(), localDT.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", localDT.getDayOfMonth() == 1);
        assertTrue("Hour is 21", localDT.getHourOfDay() == 16);
        assertTrue("Minute is 13, but got: " + localDT.getMinuteOfHour(), localDT.getMinuteOfHour() == 13);
        assertTrue("Second is 20, but got: " + localDT.getSecondOfMinute(), localDT.getSecondOfMinute() == 20);
        assertTrue("Millisecond is 230, but got: " + localDT.getMillisOfSecond(), localDT.getMillisOfSecond() == 230);

        String edmDT3 = "2010-03-01T16:13:20";
        localDT = fmt.parseLocalDateTime(edmDT3);
        log.debug("Date formatted: " + localDT.toString());

        assertTrue("year should be 2010", localDT.getYear() == 2010);
        assertTrue("Month should be 3, but got: " + localDT.getMonthOfYear(), localDT.getMonthOfYear() == 3);
        assertTrue("Day of month is 1", localDT.getDayOfMonth() == 1);
        assertTrue("Hour is 21", localDT.getHourOfDay() == 16);
        assertTrue("Minute is 13", localDT.getMinuteOfHour() == 13);
        assertTrue("Second is 20", localDT.getSecondOfMinute() == 20);
        assertTrue("Millisecond is null", localDT.getMillisOfSecond() == 0);

        String edmDT4 = "2010-02-26T17:13:20.0900752";
        localDT = fmt.parseLocalDateTime(edmDT4);
        log.debug("Date formatted: " + localDT.toString());

        assertTrue("year should be 2010", localDT.getYear() == 2010);
        assertTrue("Month should be 2, but got: " + localDT.getMonthOfYear(), localDT.getMonthOfYear() == 2);
        assertTrue("Day of month is 26", localDT.getDayOfMonth() == 26);
        assertTrue("Hour is 17", localDT.getHourOfDay() == 17);
        assertTrue("Minute is 13", localDT.getMinuteOfHour() == 13);
        assertTrue("Second is 20", localDT.getSecondOfMinute() == 20);
        assertTrue("Millisecond is 90, but got: " + localDT.getMillisOfSecond(), localDT.getMillisOfSecond() == 90);

        String edmDT5 = "2000-12-12T12:00";
        localDT = fmt.parseLocalDateTime(edmDT5);
        log.debug("Date formatted: " + localDT.toString());

    }

    @Test(expected = IllegalFieldValueException.class)
    public void edmDateTimeBadTimeTest() {
        String badTime = "2010-02-26T24:24:53.0900752";
        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeParser();
        LocalDateTime localDT = fmt.parseLocalDateTime(badTime);
    }


    @Test
    public void edmDateTimeWithZoneTest() {
        String edmDTOffsetWithZone = "2010-02-26T17:13:20.230-05:00";
        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeParser();
        LocalDateTime localDT = fmt.parseLocalDateTime(edmDTOffsetWithZone);
        log.debug("Date formatted: " + localDT.toString());
        assertTrue("year should be 2010, but got: " + localDT.getYear(), localDT.getYear() == 2010);
        assertTrue("Month should be 2, but got: " + localDT.getMonthOfYear(), localDT.getMonthOfYear() == 2);
        assertTrue("Day of month is 26", localDT.getDayOfMonth() == 26);
        assertTrue("Hour is 17", localDT.getHourOfDay() == 17);
        assertTrue("Minute is 13", localDT.getMinuteOfHour() == 13);
        assertTrue("Second is 20", localDT.getSecondOfMinute() == 20);
        assertTrue("Millisecond is 230, but got: " + localDT.getMillisOfSecond(), localDT.getMillisOfSecond() == 230);
    }

    @Test
    public void edmDateTimeOffsetTest() throws Exception {
        String edmDT1 = "2003:06:21T14:53:00.000Z";
        String edmDT7 = "2012-02-20T12:40:45.327+01:00";
        String edmDT6 = "2000-01-01T16:00:00.000Z";
        String edmDT2 = "2003:06:21T12:53:22-05:00";
        String edmDT3 = "2003:06:21T12:53";
        String edmDT4 = "2003:06:21T12:53:22";
        String edmDT5 = "2003:06:21T12:53:22.2342";
        String badTime = "2003:06:21T24:53:22.2342-05:00";

        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeParser();
        DateTime dt = fmt.parseDateTime(edmDT1);
        log.debug("Date formatted: " + dt.toString());

    }

    @Test
    public void edmTimeTest() throws Exception {
        String edmT1 = "14:53:22";
        String edmT2 = "12:53:22.2342";
        String edmT3 = "12:53:22.2342Z";
        String edmT4 = "12:53:22.2342-05:00";
        String edmT5 = "12:53:22-05:00";
        String badTime = "24:53:22.2342-05:00";

        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeParser();
        DateTime dt = fmt.parseDateTime(edmT1);
        log.debug("Date formatted: " + dt.toString());
        assertTrue("Hour is 14", dt.getHourOfDay() == 7);
        assertTrue("Minute is 53", dt.getMinuteOfHour() == 53);
        assertTrue("Second is 0", dt.getSecondOfMinute() == 0);
        assertTrue("Millisecond is 0, but got: " + dt.getMillisOfSecond(), dt.getMillisOfSecond() == 0);

    }

}