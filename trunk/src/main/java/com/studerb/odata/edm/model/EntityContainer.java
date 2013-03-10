package com.studerb.odata.edm.model;

import static com.studerb.odata.edm.EdmUtil.isEndElement;
import static com.studerb.odata.edm.EdmUtil.isStartElement;

import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.studerb.odata.edm.EdmUtil;

public class EntityContainer {
    final Logger log = LoggerFactory.getLogger(EntityContainer.class);
    private Schema schema;
    private String name;
    private String _extends;
    private List<EntitySet> entitySets = Lists.newArrayList();
    private List<AssociationSet> associationSets = Lists.newArrayList();

    public EntityContainer() {}

    public EntityContainer(Schema schema) {
        this.schema = schema;
    }

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
			if (isEndElement(event, EdmUtil.ENTITY_CONTAINERS)) {
                return;
            }
			else if (isStartElement(event, EdmUtil.ENTITY_SETS)) {
                EntitySet entitySet = new EntitySet(this);
                entitySet.parse(event.asStartElement(), reader);
                this.entitySets.add(entitySet);
            }
			else if (isStartElement(event, EdmUtil.ASSOCIATION_SETS)) {
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
}
