package com.studerb.odata.atom.convert;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * converts a string of base64 encoded bytes to an array of object wrapped Bytes (instead of primitive type
 * <code>byte</code>.
 *
 * @author William Studer
 *
 */

public class BinaryArrayByteConverter extends AbstractConverter {
    private final Logger log = LoggerFactory.getLogger(BinaryArrayByteConverter.class);

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        Byte[] bytes = null;
        if (value instanceof String && !StringUtils.isBlank((String) value)) {
            this.log.info("Base 64 decoding string");
            String s = (String) value;
            byte[] decoded = Base64.decodeBase64(s);
            bytes = ArrayUtils.toObject(decoded);
        }
        else {
            log.warn("Unable to convert to Byte[] for value: " + value);
        }
        return bytes;
    }

    @Override
    protected Class getDefaultType() {
        return Byte[].class;
    }
}