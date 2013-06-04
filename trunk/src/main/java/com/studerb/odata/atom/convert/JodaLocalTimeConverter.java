package com.studerb.odata.atom.convert;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JodaLocalTimeConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(JodaLocalTimeConverter.class);

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        LocalTime t = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            this.log.debug("Converting " + value + " to Joda Local Time Object");
            t = new LocalTime(value);
        }
        else {
            log.warn("Unable to convert to JodaLocalTime for value: " + value);
        }
        return t;
    }

    @Override
    protected Class getDefaultType() {
        return org.joda.time.LocalTime.class;
    }
}