package com.studerb.odata.atom.convert;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.UnsignedBytes;
import com.studerb.odata.edm.UnsignedByte;

/**
 * Converts EDM {@code Byte} type which are actually unsigned bytes using the {@link com.studerb.odata.edm.UnsignedByte}
 * type instead of Java's standard primitive or Object Byte which is signed. Internally uses Google Guava's static
 * {@code UnsignedBytes} method to parse from Hexadecimal string.
 * 
 * @author Bill Studer
 * 
 */
public class UnsignedByteConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(UnsignedByteConverter.class);

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        UnsignedByte unsignedByte = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            try {
                byte b = UnsignedBytes.parseUnsignedByte((String) value, 16);
                // should already be under 255
                int i = b & 0xff;
                unsignedByte = new UnsignedByte(i);
            }
            catch (NumberFormatException e) {
                throw new RuntimeException("Unable to convert to UnsignedByte for value: " + value);
            }
        }
        else {
            log.warn("Unable to convert to UnsignedByte for value: " + value);
        }
        return unsignedByte;
    }

    @Override
    protected Class getDefaultType() {
        return UnsignedByte.class;
    }
}