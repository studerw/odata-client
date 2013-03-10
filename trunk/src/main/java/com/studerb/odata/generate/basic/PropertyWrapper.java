package com.studerb.odata.generate.basic;

import java.beans.Introspector;

import org.apache.commons.lang.StringUtils;

import com.studerb.odata.edm.model.Property;

public class PropertyWrapper {

    private Property property;

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
        else if (this.property.getType().endsWith("DateTime")) {
            result = "Date";
        }
        else if (this.property.getType().endsWith("DateTimeOffset")) {
            result = "Date";
        }
        else if (this.property.getType().endsWith("Time")) {
            result = "Long";
        }
        else if (this.property.getType().endsWith("Decimal")) {
            result = "Double";
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
        else if (this.property.getType().endsWith("Int16")) {
            result = "Short";
        }
        else if (this.property.getType().endsWith("Int32")) {
            result = "Integer";
        }
        else if (this.property.getType().endsWith("Int64")) {
            result = "Long";
        }
        else if (this.property.getType().endsWith("Byte")) {
            result = "Byte";
        }
        else if (this.property.getType().endsWith("String")) {
            result = "String";
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
