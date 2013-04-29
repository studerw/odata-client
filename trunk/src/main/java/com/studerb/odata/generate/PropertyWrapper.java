package com.studerb.odata.generate;

import java.beans.Introspector;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.edm.model.Property;

public class PropertyWrapper {
    final Logger log = LoggerFactory.getLogger(PropertyWrapper.class);
    private final Property property;

    public PropertyWrapper(Property property) {
        this.property = property;
    }

    public String getJavaClassType() {

        final String PREFIX = "(Edm\\.)?";
        String result = "Object";
        if (this.property.getType().matches(PREFIX + "Binary")) {
            result = "Byte[]";
        }
        else if (this.property.getType().matches(PREFIX + "Boolean")) {
            result = "Boolean";
        }
        else if (this.property.getType().matches(PREFIX + "Byte")) {
            result = "com.studerb.odata.edm.UnsignedByte";
        }
        else if (this.property.getType().matches(PREFIX + "DateTime")) {
            result = "org.joda.time.LocalDateTime";
            // result = "java.lang.calendar";
        }
        else if (this.property.getType().matches(PREFIX + "DateTimeOffset")) {
            result = "org.joda.time.DateTime";
            // result = "java.lang.calendar";
        }
        else if (this.property.getType().matches(PREFIX + "Decimal")) {
            result = "BigDecimal";
        }
        else if (this.property.getType().matches(PREFIX + "Single")) {
            result = "Float";
        }
        else if (this.property.getType().endsWith("Double")) {
            result = "Double";
        }
        else if (this.property.getType().matches(PREFIX + "Guid")) {
            result = "String";
        }
        else if (this.property.getType().matches(PREFIX + "Int16")) {
            result = "Short";
        }
        else if (this.property.getType().matches(PREFIX + "Int32")) {
            result = "Integer";
        }
        else if (this.property.getType().matches(PREFIX + "Int64")) {
            result = "Long";
        }
        else if (this.property.getType().matches(PREFIX + "SByte")) {
            result = "Byte";
        }

        else if (this.property.getType().matches(PREFIX + "String")) {
            result = "String";
        }
        else if (this.property.getType().matches(PREFIX + "Time")) {
            result = "org.joda.time.LocalTime";

        }
        else {
            log.warn("Can not find appropriate type for: " + this.property.getType() + ". Setting to default Object");
        }

        return result;
    }

    public String getJavaName() {
        return Introspector.decapitalize(this.property.getName());
    }

    public String getDefaultValue() {
        return this.property.getDefaultValue();
    }

    public String getGetter() {
        return "get" + StringUtils.capitalize(getJavaName());
    }

    public String getSetter() {
        return "set" + StringUtils.capitalize(getJavaName());
    }

}
