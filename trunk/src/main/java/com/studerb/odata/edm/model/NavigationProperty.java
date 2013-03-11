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

public class NavigationProperty {
    private final Logger log = LoggerFactory.getLogger(NavigationProperty.class);

    private final EntityType entityType;
    private String name;
    private String relationship;
    private String toRole;
    private String fromRole;
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public NavigationProperty(EntityType entityType) {
        this.entityType = entityType;
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


    public String getName() {
        return this.name;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getToRole() {
        return this.toRole;
    }

    public String getFromRole() {
        return this.fromRole;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
