package com.studerb.odata.atom.convert;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * converts a string of base64 encoded bytes to an array of primitive bytes.
 * 
 * @author William Studer
 * 
 */
public class BinaryArrayPrimitiveByteConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(BinaryArrayByteConverter.class);

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        byte[] bytes = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            this.log.debug("Base 64 decoding string");
            String s = (String) value;
            bytes = Base64.decodeBase64(s);
        }
        else {
            log.warn("Unable to convert to byte[] for value: " + value);
        }
        return bytes;
    }

    @Override
    protected Class getDefaultType() {
        return byte[].class;
    }
}