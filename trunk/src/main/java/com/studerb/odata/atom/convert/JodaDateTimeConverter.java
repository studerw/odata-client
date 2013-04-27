package com.studerb.odata.atom.convert;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JodaDateTimeConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(JodaDateTimeConverter.class);

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        DateTime d = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            d = new DateTime(value, DateTimeZone.UTC);
        }
        else {
            log.warn("Unable to convert to org.joda.time.DateTime for: " + value);
        }
        return d;
    }

    @Override
    protected Class getDefaultType() {
        return DateTime.class;
    }
}