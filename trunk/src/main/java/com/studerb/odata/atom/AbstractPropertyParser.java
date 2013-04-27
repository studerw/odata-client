package com.studerb.odata.atom;

import java.util.Calendar;
import java.util.Date;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.atom.convert.BinaryArrayByteConverter;
import com.studerb.odata.atom.convert.BinaryArrayPrimitiveByteConverter;
import com.studerb.odata.atom.convert.CalendarConverter;
import com.studerb.odata.atom.convert.DateConverter;
import com.studerb.odata.atom.convert.JodaDateTimeConverter;
import com.studerb.odata.atom.convert.JodaLocalTimeConverter;
import com.studerb.odata.atom.convert.UnsignedByteConverter;
import com.studerb.odata.edm.UnsignedByte;

/**
 * @author William Studer
 * 
 * @param <T>
 * 
 * @see <a href="http://www.odata.org/documentation/overview#AbstractTypeSystem">OData Abstract Type System for
 *      Conversion info</a>
 */
public abstract class AbstractPropertyParser<T> implements ODataPropertyParser<T> {
    final Logger log = LoggerFactory.getLogger(AbstractPropertyParser.class);
    protected String entryId;
    protected Entry entry;
    protected T target;

    static {
        BeanUtilsBean.setInstance(new BeanUtilsBean2());
        ConvertUtils.register(new JodaDateTimeConverter(), DateTime.class);
        ConvertUtils.register(new CalendarConverter(), Calendar.class);
        ConvertUtils.register(new DateConverter(), Date.class);
        ConvertUtils.register(new JodaLocalTimeConverter(), LocalTime.class);
        ConvertUtils.register(new BinaryArrayByteConverter(), Byte[].class);
        ConvertUtils.register(new BinaryArrayPrimitiveByteConverter(), byte[].class);
        ConvertUtils.register(new UnsignedByteConverter(), UnsignedByte.class);
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

}
