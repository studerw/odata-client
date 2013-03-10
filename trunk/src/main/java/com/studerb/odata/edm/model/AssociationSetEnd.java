package com.studerb.odata.edm.model;

import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssociationSetEnd {
    final Logger log = LoggerFactory.getLogger(AssociationSetEnd.class);
    private AssociationSet associationSet;
    private String role;
    private String entitySet;

    public AssociationSetEnd() {}

    public AssociationSetEnd(AssociationSet associationSet) {
        this.associationSet = associationSet;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
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

    public void setAssociationSet(AssociationSet associationSet) {
        this.associationSet = associationSet;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEntitySet() {
        return this.entitySet;
    }

    public void setEntitySet(String entitySet) {
        this.entitySet = entitySet;
    }

}
