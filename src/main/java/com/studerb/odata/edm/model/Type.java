package com.studerb.odata.edm.model;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public abstract class Type {
    final Logger log = LoggerFactory.getLogger(Type.class);
    protected List<Property> properties = Lists.newArrayList();
    protected List<ComplexType> complexTypes = Lists.newArrayList();
    protected String documentation;
    protected String name;
    protected Schema schema;
    protected Boolean abstractType = false;
    protected String baseType;
    protected String annotationAttribute;
    protected QName qName;
    protected final List<Attribute> attributes = Lists.newArrayList();

    public abstract void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException;

    protected void setAttributes(StartElement startElement) {
        Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            this.attributes.add(att);
            if (att.getName().getLocalPart().equalsIgnoreCase("Name")) {
                this.log.debug("Name: " + att.getValue());
                this.name = att.getValue();
            }
            else if (att.getName().getLocalPart().equalsIgnoreCase("BaseType")) {
                this.log.debug("BaseType: " + att.getValue());
                this.baseType = att.getValue();
            }
            else if (att.getName().getLocalPart().equalsIgnoreCase("Abstract")) {
                this.log.debug("Abstract: " + att.getValue());
                this.abstractType = BooleanUtils.toBooleanObject(att.getValue());
            }
            else if (att.getName().getLocalPart().equalsIgnoreCase("AnnotationAttribute")) {
                this.log.debug("AnnotationAttribute: " + att.getValue());
                this.annotationAttribute = att.getValue();
            }

        }
    }

    public String getBaseType() {
        return baseType;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public String getName() {
        return this.name;
    }

    public Schema getSchema() {
        return this.schema;
    }

    public List<ComplexType> getComplexTypes() {
        return complexTypes;
    }

    public String getDocumentation() {
        return documentation;
    }

    public Boolean isAbstractType() {
        return abstractType;
    }

    public String getAnnotationAttribute() {
        return annotationAttribute;
    }

}
