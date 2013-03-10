package com.studerb.odata.edm.model;

import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationProperty {
    private final Logger log = LoggerFactory.getLogger(NavigationProperty.class);

    private EntityType entityType;
    private String name;
    private String relationship;
    private String toRole;
    private String fromRole;

    public NavigationProperty() {}

    public NavigationProperty(EntityType entityType) {
        this.entityType = entityType;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            String x = att.getName().getLocalPart();
            if (x.equalsIgnoreCase("Name")) {
                this.log.debug("Name: " + att.getValue());
                this.name = att.getValue();
            }
            else if (x.equalsIgnoreCase("Relationship")) {
                this.log.debug("Relationship: " + att.getValue());
                this.relationship = att.getValue();
            }
            else if (x.equalsIgnoreCase("ToRole")) {
                this.log.debug("ToRole: " + att.getValue());
                this.toRole = att.getValue();
            }
            else if (x.equalsIgnoreCase("FromRole")) {
                this.log.debug("FromRole: " + att.getValue());
                this.fromRole = att.getValue();
            }
        }
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getToRole() {
        return this.toRole;
    }

    public void setToRole(String toRole) {
        this.toRole = toRole;
    }

    public String getFromRole() {
        return this.fromRole;
    }

    public void setFromRole(String fromRole) {
        this.fromRole = fromRole;
    }
}
