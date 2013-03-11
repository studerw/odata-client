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

public class EntitySet {
    final Logger log = LoggerFactory.getLogger(EntitySet.class);
    private final EntityContainer entityContainer;
    private String entityType;
    private String name;
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public EntitySet(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
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
    public String getEntityType() {
        return this.entityType;
    }

    public String getName() {
        return this.name;
    }
    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
