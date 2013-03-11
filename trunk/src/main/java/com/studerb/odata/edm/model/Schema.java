package com.studerb.odata.edm.model;

import static com.studerb.odata.edm.EdmUtil.isEndElement;
import static com.studerb.odata.edm.EdmUtil.isStartElement;

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
import com.studerb.odata.edm.EdmUtil;

public class Schema {
    final Logger log = LoggerFactory.getLogger(Schema.class);
    private String namespace;
    private String alias;

    private final List<Association> associations = Lists.newArrayList();
    private final List<ComplexType> complexTypes = Lists.newArrayList();
    private final List<EntityType> entityTypes = Lists.newArrayList();
    private final List<EntityContainer> entityContainers = Lists.newArrayList();
    private final DataService dataService;
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public Schema(DataService dataService) {
        this.dataService = dataService;
    }

    public void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException {
        this.qName = startElement.getName();
        setAttributes(startElement);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
			if (isEndElement(event, EdmUtil.SCHEMAS)) {
                return;
            }
			else if (isStartElement(event, EdmUtil.ENTITY_TYPES)) {
                EntityType entityType = new EntityType(this);
                log.trace(EdmUtil.printStartElement(event.asStartElement()));
                entityType.parse(event.asStartElement(), reader);
                this.entityTypes.add(entityType);
            }
			else if (isStartElement(event, EdmUtil.ASSOCIATIONS)) {
                Association association = new Association(this);
                association.parse(event.asStartElement(), reader);
                this.associations.add(association);
            }
			else if (isStartElement(event, EdmUtil.COMPLEX_TYPES)) {
                ComplexType complexType = new ComplexType(this);
                complexType.parse(event.asStartElement(), reader);
                this.complexTypes.add(complexType);
            }
			else if (isStartElement(event, EdmUtil.ENTITY_CONTAINERS)) {
                EntityContainer entityContainer = new EntityContainer(this);
                entityContainer.parse(event.asStartElement(), reader);
                this.entityContainers.add(entityContainer);
            }
        }
    }

    private void setAttributes(StartElement startElement) {
		Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
			Attribute att = (Attribute) iter.next();
            this.attributes.add(att);
            log.trace("Attribute QName: " + att.getName().toString());
            if (att.getName().getLocalPart().equalsIgnoreCase("Namespace")) {
                this.log.debug("Namespace: " + att.getValue());
                this.namespace = att.getValue();
            }
            else if (att.getName().getLocalPart().equalsIgnoreCase("Alias")) {
                this.log.debug("Alias: " + att.getValue());
                this.alias = att.getValue();
            }
        }
    }

    public List<Association> getAssociations() {
        return this.associations;
    }

    public List<ComplexType> getComplexTypes() {
        return this.complexTypes;
    }

    public List<EntityType> getEntityTypes() {
        return this.entityTypes;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getAlias() {
        return this.alias;
    }

    public List<EntityContainer> getEntityContainers() {
        return this.entityContainers;
    }

    public DataService getDataService() {
        return this.dataService;
    }

    public Association getAssociationByName(String name) {
        if (this.associations == null || this.associations.isEmpty()) {
            return null;
        }
        for (Association temp : this.associations) {
            if (name.equals(this.namespace + "." + temp.getName()) || name.equals(temp.getName())) {
                return temp;
            }
        }
        return null;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}

