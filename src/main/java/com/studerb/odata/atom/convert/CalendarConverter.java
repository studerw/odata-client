package com.studerb.odata.atom.convert;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts a string to an instance of {@link java.lang.Calendar}. Internally uses JodaTime's default constructor to
 * create a Joda Time object, then converting back to {@link java.lang.Calendar}. The timezone on the returned Date is
 * set to UTC.
 * 
 * @author William Studer
 * 
 */
public class CalendarConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(CalendarConverter.class);
    final static FastDateFormat isoDateTimeFormat = DateFormatUtils.ISO_DATETIME_FORMAT;
    SimpleDateFormat f = new SimpleDateFormat(isoDateTimeFormat.getPattern());

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        Calendar cal = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            DateTime temp = new DateTime(value, DateTimeZone.UTC);
            cal = temp.toCalendar(null);
        }
        else {
            log.warn("Unable to convert to java.util.Date: " + value);
        }
        return cal;
    }

    @Override
    protected Class getDefaultType() {
        return Calendar.class;
    }
}