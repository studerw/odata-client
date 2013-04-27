package com.studerb.odata.atom.convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts a string to an instance of {@link java.util.Date}. Internally uses JodaTime's default constructor to create
 * a Joda Time object, then converting back to {@link java.util.Date}. The timezone on the returned Date is set to UTC.
 * 
 * @author William Studer
 * 
 */
public class DateConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(DateConverter.class);
    final static FastDateFormat isoDateTimeFormat = DateFormatUtils.ISO_DATETIME_FORMAT;
    SimpleDateFormat f = new SimpleDateFormat(isoDateTimeFormat.getPattern());

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        Date d = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            DateTime temp = new DateTime(value, DateTimeZone.UTC);
            d = temp.toDate();
        }
        else {
            log.warn("Unable to convert to java.util.Date: " + value);
        }
        return d;
    }

    @Override
    protected Class getDefaultType() {
        return Date.class;
    }
}