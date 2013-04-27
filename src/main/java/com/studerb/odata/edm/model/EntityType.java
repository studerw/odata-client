package com.studerb.odata.edm.model;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.studerb.odata.atom.Namespaces;

public class EntityType extends Type {
    final Logger log = LoggerFactory.getLogger(EntityType.class);

    private final List<NavigationProperty> navigationProperties = Lists.newArrayList();
    private List<String> keys = Lists.newArrayList();
    protected Boolean openType;

    public EntityType(Schema schema) {
        this.schema = schema;
    }

    public List<String> getKeys() {
        return this.keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException {
        log.trace(Namespaces.printStartElement(startElement));
        this.qName = startElement.getName();
        setAttributes(startElement);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (Namespaces.isEndElement(event, Namespaces.ENTITY_TYPES)) {
                return;
            }
            else if (Namespaces.isStartElement(event, Namespaces.KEYS)) {
                String key = parseKey(event.asStartElement(), reader);
                this.log.debug("Adding key: " + key);
                this.keys.add(key);
            }
            else if (Namespaces.isStartElement(event, Namespaces.PROPERTIES)) {
                Property property = new Property(this);
                property.parse(event.asStartElement(), reader);
                this.properties.add(property);
            }
            else if (Namespaces.isStartElement(event, Namespaces.NAV_PROPS)) {
                NavigationProperty navProp = new NavigationProperty(this);
                navProp.parse(event.asStartElement(), reader);
                this.navigationProperties.add(navProp);
            }
        }
    }

    @Override
    protected void setAttributes(StartElement startElement) {
        super.setAttributes(startElement);
        Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            if (att.getName().getLocalPart().equalsIgnoreCase("openType")) {
                this.log.debug("OpenType: " + att.getValue());
                this.openType = BooleanUtils.toBooleanObject(att.getValue());
            }
        }
    }

    private String parseKey(StartElement el, XMLEventReader reader) throws XMLStreamException {
        String val = null;
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (Namespaces.isEndElement(event, Namespaces.KEYS)) {
                break;
            }
            else if (Namespaces.isStartElement(event, Namespaces.PROP_REFS)) {
                Attribute att = event.asStartElement().getAttributeByName(new QName("Name"));
                if (att == null) {
                    throw new RuntimeException("Cannot find Key name for property");
                }
                else {
                    val = att.getValue();
                }
            }
        }
        return val;
    }

    public List<NavigationProperty> getNavigationProperties() {
        return this.navigationProperties;
    }

}
