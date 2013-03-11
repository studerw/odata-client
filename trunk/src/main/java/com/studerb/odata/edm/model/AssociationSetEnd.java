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

public class AssociationSetEnd {
    final Logger log = LoggerFactory.getLogger(AssociationSetEnd.class);
    private final AssociationSet associationSet;
    private String role;
    private String entitySet;
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public AssociationSetEnd(AssociationSet associationSet) {
        this.associationSet = associationSet;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        this.qName = el.getName();
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            this.attributes.add(att);
            String x = att.getName().getLocalPart();
            if (x.equalsIgnoreCase("EntitySet")) {
                this.log.debug("Entity Set: " + att.getValue());
                this.entitySet = att.getValue();
            }
            else if (x.equalsIgnoreCase("Role")) {
                this.log.debug("Role: " + att.getValue());
                this.role = att.getValue();
            }
        }
    }

    public AssociationSet getAssociationSet() {
        return this.associationSet;
    }

    public String getRole() {
        return this.role;
    }

    public String getEntitySet() {
        return this.entitySet;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
