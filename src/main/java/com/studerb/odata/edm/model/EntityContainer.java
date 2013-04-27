package com.studerb.odata.edm.model;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.studerb.odata.atom.Namespaces;

public class EntityContainer {
    final Logger log = LoggerFactory.getLogger(EntityContainer.class);
    private Schema schema;
    private String name;
    private String _extends;
    private List<EntitySet> entitySets = Lists.newArrayList();
    private List<AssociationSet> associationSets = Lists.newArrayList();
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public EntityContainer() {}

    public EntityContainer(Schema schema) {
        this.schema = schema;
    }

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        this.qName = el.getName();
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (Namespaces.isEndElement(event, Namespaces.ENTITY_CONTAINERS)) {
                return;
            }
            else if (Namespaces.isStartElement(event, Namespaces.ENTITY_SETS)) {
                EntitySet entitySet = new EntitySet(this);
                entitySet.parse(event.asStartElement(), reader);
                this.entitySets.add(entitySet);
            }
            else if (Namespaces.isStartElement(event, Namespaces.ASSOCIATION_SETS)) {
                AssociationSet associationSet = new AssociationSet(this);
                associationSet.parse(event.asStartElement(), reader);
                this.associationSets.add(associationSet);
            }

        }
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
                this.log.debug("Extends: " + att.getValue());
                this._extends = att.getValue();
            }
        }
    }

    public Schema getSchema() {
        return this.schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_extends() {
        return this._extends;
    }

    public void set_extends(String extends1) {
        this._extends = extends1;
    }

    public List<EntitySet> getEntitySets() {
        return this.entitySets;
    }

    public void setEntitySets(List<EntitySet> entitySets) {
        this.entitySets = entitySets;
    }

    public List<AssociationSet> getAssociationSets() {
        return this.associationSets;
    }

    public void setAssociationSets(List<AssociationSet> associationSets) {
        this.associationSets = associationSets;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
