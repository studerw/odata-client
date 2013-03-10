package com.studerb.odata.atom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPropertyParser<T> implements ODataPropertyParser<T> {
    final Logger log = LoggerFactory.getLogger(AbstractPropertyParser.class);
    protected String entryId;
    protected Entry entry;
    protected T target;

    static {
        BeanUtilsBean.setInstance(new BeanUtilsBean2());
        ConvertUtils.register(new JodaLocalDateTimeConverter(), LocalDateTime.class);
        ConvertUtils.register(new ODataDefaultDateConverter(), Date.class);
        ConvertUtils.register(new ODataDefaultBinaryByteConverter(), Byte[].class);
        ConvertUtils.register(new ODataBinaryArrayByteConverter(), byte[].class);
    }

    public abstract String getPropName(String name);

    @Override
    public void parse(T target, Entry entry) {
        this.entry = entry;
        this.target = target;
        this.entryId = entry.getId().toString();
        _parse();
    }

    protected void _parse() {
        Element content = this.entry.getContentElement();
        Element propsEl = content.getFirstChild(Namespaces.M_PROPERTIES);
        if (propsEl == null) {
            this.log.info(String.format("entry with id: %s contains no OData properties to parse", this.entryId));
            return;
        }
        this.log.debug("Parsing props for Object with Odata id: " + this.entryId);
        for (Element propEl : propsEl.getElements()) {
            parseProp(propEl);
        }
    }

    protected void parseProp(Element propEl) {
        try {
            String propName = propEl.getQName().getLocalPart();
            propName = getPropName(propName);
            String val = getPropValue(propEl);
            if (val == null) {
                this.log.trace("Ignoring null prop: " + propName);
                return;
            }
            this.log.trace(String.format("Setting prop: %s to val: %s", propName, val));
            boolean isWritable = PropertyUtils.isWriteable(this.target, propName);
            if (!isWritable) {
                this.log.warn("Can't write to bean with prop: " + propName);
                return;
            }
            BeanUtils.setProperty(this.target, propName, val);
        }
        catch (Exception e) {
            this.log.error(String.format("Error setting prop on Type<%s> : element was: '%s'", this.target.getClass().getSimpleName(), propEl.getText()));

        }
    }

    protected boolean isNullProp(Element propEl) {
        boolean isNull = false;
        if (!StringUtils.isBlank(propEl.getAttributeValue(Namespaces.M_NULL))) {
            isNull = propEl.getAttributeValue(Namespaces.M_NULL).equals("true");
        }
        return isNull;
    }

    protected String getPropValue(Element propEl) {
        String val;
        if (isNullProp(propEl)) {
            val = null;
        }
        else {
            val = propEl.getText();
        }
        return val;
    }

    /*
     * protected EdmType getEdmType(Element propEl) { String type =
     * StringUtils.defaultIfEmpty(propEl.getAttributeValue(Namespaces.M_TYPE),
     * EdmType.STRING.toString()); EdmType edmType = EdmType.get(type); return
     * edmType; }
     */

    static class JodaLocalDateTimeConverter extends AbstractConverter {
        private final Logger log = LoggerFactory.getLogger(JodaLocalDateTimeConverter.class);

        @Override
        protected Object convertToType(Class type, Object value) throws Throwable {
            LocalDateTime d = null;
            if (value instanceof String && !StringUtils.isBlank((String) value)) {
                d = new LocalDateTime(value);
            }
            return d;
        }

        @Override
        protected Class getDefaultType() {
            return LocalDateTime.class;
        }
    }

    static class ODataDefaultDateConverter extends AbstractConverter {
        private final Logger log = LoggerFactory.getLogger(ODataDefaultDateConverter.class);
        final static FastDateFormat isoDateTimeFormat = DateFormatUtils.ISO_DATETIME_FORMAT;

        @Override
        protected Object convertToType(Class type, Object value) throws Throwable {
            Date d = null;
            if (value instanceof String && !StringUtils.isBlank((String) value)) {
                SimpleDateFormat f = new SimpleDateFormat(isoDateTimeFormat.getPattern());
                try {
                    d = f.parse((String) value);
                }
                catch (ParseException e) {
                    this.log.error(e.getMessage(), e);
                }
            }
            return d;

        }

        @Override
        protected Class getDefaultType() {
            return java.util.Date.class;
        }
    }

    static class ODataDefaultBinaryByteConverter extends AbstractConverter {
        private final Logger log = LoggerFactory.getLogger(ODataDefaultBinaryByteConverter.class);

        @Override
        protected Object convertToType(Class type, Object value) throws Throwable {
            Byte[] bytes = null;
            if (value instanceof String && !StringUtils.isBlank((String) value)) {
                this.log.info("Base 64 decoding string");
                String s = (String) value;
                byte[] decoded = Base64.decodeBase64(s);
                bytes = ArrayUtils.toObject(decoded);
            }
            return bytes;
        }

        @Override
        protected Class getDefaultType() {
            return Byte[].class;
        }
    }

    static class ODataBinaryArrayByteConverter extends AbstractConverter {
        private final Logger log = LoggerFactory.getLogger(ODataDefaultBinaryByteConverter.class);

        @Override
        protected Object convertToType(Class type, Object value) throws Throwable {
            byte[] bytes = null;
            if (value instanceof String && !StringUtils.isBlank((String) value)) {
                this.log.info("Base 64 decoding string");
                String s = (String) value;
                bytes = Base64.decodeBase64(s);
            }
            return bytes;
        }

        @Override
        protected Class getDefaultType() {
            return byte[].class;
        }
    }

}
