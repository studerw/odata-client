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

public class AssociationEnd {
    final Logger log = LoggerFactory.getLogger(AssociationEnd.class);

    private final Association association;
    private String type;
    private String role;
    private String multiplicity;
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public AssociationEnd(Association association) {
        this.association = association;
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
            if (x.equalsIgnoreCase("Type")) {
                this.log.debug("Type: " + att.getValue());
                this.type = att.getValue();
            }
            else if (x.equalsIgnoreCase("Role")) {
                this.log.debug("Role: " + att.getValue());
                this.role = att.getValue();
            }
            else if (x.equalsIgnoreCase("Multiplicity")) {
                this.log.debug("Multiplicity: " + att.getValue());
                this.multiplicity = att.getValue();
            }
        }
    }

    public Association getAssociation() {
        return this.association;
    }

    public String getType() {
        return this.type;
    }

    public String getRole() {
        return this.role;
    }

    public String getMultiplicity() {
        return this.multiplicity;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
