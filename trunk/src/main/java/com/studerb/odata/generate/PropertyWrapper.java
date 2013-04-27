/*
 * $Id: StringConversionTest.java 5 2013-03-12 06:24:16Z stbill79 $
 *
 * Copyright (c) 2013 William Studer
 */
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
        String result = "Object";
        if (this.property.getType().endsWith("Binary")) {
            result = "byte[]";
        }
        else if (this.property.getType().endsWith("Boolean")) {
            result = "Boolean";
        }
        else if (this.property.getType().endsWith("Byte")) {
            result = "UnsignedByte";
        }
        else if (this.property.getType().endsWith("DateTime")) {
            result = "org.joda.time.LocalDateTime";
            // result = "java.lang.calendar";
        }
        else if (this.property.getType().endsWith("DateTimeOffset")) {
            result = "org.joda.time.DateTime";
            // result = "java.lang.calendar";
        }
        else if (this.property.getType().endsWith("Time")) {
            result = "org.joda.time.LocalTime";

        }
        else if (this.property.getType().endsWith("Decimal")) {
            result = "BigDecimal";
        }
        else if (this.property.getType().endsWith("Single")) {
            result = "Float";
        }
        else if (this.property.getType().endsWith("Double")) {
            result = "Double";
        }
        else if (this.property.getType().endsWith("Guid")) {
            result = "String";
        }
        else if (this.property.getType().endsWith("SByte")) {
            result = "Byte";
        }
        else if (this.property.getType().endsWith("Int16")) {
            result = "Short";
        }
        else if (this.property.getType().endsWith("Int32")) {
            result = "Integer";
        }
        else if (this.property.getType().endsWith("Int64")) {
            result = "Long";
        }
        else if (this.property.getType().endsWith("String")) {
            result = "String";
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
