package com.studerb.odata.edm.model;

import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssociationEnd {
    final Logger log = LoggerFactory.getLogger(AssociationEnd.class);

    private Association association;
    private String type;
    private String role;
    private String multiplicity;

    public AssociationEnd() {}

    public AssociationEnd(Association association) {
        this.association = association;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
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

    public void setAssociation(Association association) {
        this.association = association;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMultiplicity() {
        return this.multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

}
