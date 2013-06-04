package com.studerb.odata.atom.convert;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//
/**
 * Converts stings using EDM.DateTimeOffset format to Joda {@link org.joda.time.DateTime DateTime} objects.
 * 
 * @author Bill Studer
 * 
 * @see <a href="http://joda-time.sourceforge.net/apidocs/">JodaTime 2 API</a>
 * @see <a href="http://www.odata.org/documentation/odata-v2-documentation/atom-format/#21_Primitive_Types">OData v2
 *      Primitive Types</a>
 */
public class JodaDateTimeConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(JodaDateTimeConverter.class);

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        DateTime d = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
            d = fmt.parseDateTime((String) value);
            // pd = new DateTime(value, DateTimeZone.UTC);
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