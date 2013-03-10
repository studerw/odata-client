package com.studerb.odata.edm.model;

import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntitySet {
    final Logger log = LoggerFactory.getLogger(EntitySet.class);
    private EntityContainer entityContainer;
    private String entityType;
    private String name;

    public EntitySet() {}

    public EntitySet(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
    }

    public void parse(StartElement el, XMLEventReader reader) {
        setAttributes(el);
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            if (att.getName().getLocalPart().equalsIgnoreCase("Name")) {
                this.log.debug("Name: " + att.getValue());
                this.name = att.getValue();
            }
            if (att.getName().getLocalPart().equalsIgnoreCase("EntityType")) {
                this.log.debug("EntityType: " + att.getValue());
                this.entityType = att.getValue();
            }
        }
    }

    public EntityContainer getEntityContainer() {
        return this.entityContainer;
    }

    public void setEntityContainer(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
