package com.studerb.odata.edm.model;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.studerb.odata.edm.EdmUtil;

public class Property {
    final Logger log = LoggerFactory.getLogger(Property.class);

    final private Type parentType;
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
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();


    public Property(Type parentType) {
        this.parentType = parentType;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        log.trace(EdmUtil.printStartElement(el));
        this.qName = el.getName();
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            this.attributes.add(att);
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


    public Boolean getNullable() {
        return this.nullable;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }


    public String getMaxLength() {
        return this.maxLength;
    }

    public Boolean getFixedLength() {
        return this.fixedLength;
    }


    public String getPrecision() {
        return this.precision;
    }


    public String getScale() {
        return this.scale;
    }


    public Boolean getUnicode() {
        return this.unicode;
    }


    public String getType() {
        return this.type;
    }


    public Type getParentType() {
        return this.parentType;
    }


    public String getMimeType() {
        return this.mimeType;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
