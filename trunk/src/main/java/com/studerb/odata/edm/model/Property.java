package com.studerb.odata.edm.model;

import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Property {
    final Logger log = LoggerFactory.getLogger(Property.class);

    private Type parentType;
    private String name;
    private Boolean nullable;
    private String defaultValue;
    private String maxLength;
    private Boolean fixedLength;
    private String precision;
    private String scale;
    private Boolean unicode;
    private String type;
    private String mimeType;

    public Property() {}

    public Property(Type parentType) {
        this.parentType = parentType;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            String name = att.getName().getLocalPart();
            if (name.equalsIgnoreCase("Name")) {
                this.log.debug("Name: " + att.getValue());
                this.name = att.getValue();
            }
            else if (name.equalsIgnoreCase("Nullable")) {
                this.log.debug("nullable: " + att.getValue());
                this.nullable = (att.getValue().equalsIgnoreCase("True")) ? true : false;
            }
            else if (name.equalsIgnoreCase("Type")) {
                this.log.debug("type: " + att.getValue());
                this.type = att.getValue();
            }
            else if (name.equalsIgnoreCase("MaxLength")) {
                this.log.debug("maxLength: " + att.getValue());
                this.maxLength = att.getValue();
            }
            else if (name.equalsIgnoreCase("Unicode")) {
                this.log.debug("unicode: " + att.getValue());
                this.unicode = (att.getValue().equalsIgnoreCase("True")) ? true : false;
            }
            else if (name.equalsIgnoreCase("FixedLength")) {
                this.log.debug("fixedLength: " + att.getValue());
                this.fixedLength = (att.getValue().equalsIgnoreCase("True")) ? true : false;
            }
            else if (name.equalsIgnoreCase("Scale")) {
                this.log.debug("Scale: " + att.getValue());
                this.scale = att.getValue();
            }
            else if (name.equalsIgnoreCase("Precision")) {
                this.log.debug("Precision: " + att.getValue());
                this.precision = att.getValue();
            }
            else if (name.equalsIgnoreCase("MimeType")) {
                this.log.debug("Mime Type: " + att.getValue());
                this.mimeType = att.getValue();
            }

            else if (name.startsWith("FC_")) {
                this.log.warn(name + " - Customized Property Mappings are not yet supported...");
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNullable() {
        return this.nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getFixedLength() {
        return this.fixedLength;
    }

    public void setFixedLength(Boolean fixedLength) {
        this.fixedLength = fixedLength;
    }

    public String getPrecision() {
        return this.precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getScale() {
        return this.scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Boolean getUnicode() {
        return this.unicode;
    }

    public void setUnicode(Boolean unicode) {
        this.unicode = unicode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Type getParentType() {
        return this.parentType;
    }

    public void setParentType(Type parentType) {
        this.parentType = parentType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
