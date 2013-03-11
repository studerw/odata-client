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

public class EntityType extends Type {
    final Logger log = LoggerFactory.getLogger(EntityType.class);

    private List<NavigationProperty> navigationProperties = Lists.newArrayList();
    private List<String> keys = Lists.newArrayList();
    public String baseType;

    public EntityType() {}

    public EntityType(Schema schema) {
        this.schema = schema;
    }

    public List<String> getKeys() {
        return this.keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException {
        setAttributes(startElement);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (isEndElement(event, EdmUtil.ENTITY_TYPES)) {
                return;
            }
            else if (isStartElement(event, EdmUtil.KEYS)) {
                String key = parseKey(event.asStartElement(), reader);
                this.log.debug("Adding key: " + key);
                this.keys.add(key);
            }
            else if (isStartElement(event, EdmUtil.PROPERTIES)) {
                Property property = new Property(this);
                property.parse(event.asStartElement(), reader);
                this.properties.add(property);
            }
            else if (isStartElement(event, EdmUtil.NAV_PROPS)) {
                NavigationProperty navProp = new NavigationProperty(this);
                navProp.parse(event.asStartElement(), reader);
                this.navigationProperties.add(navProp);
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
        }
    }

    private String parseKey(StartElement el, XMLEventReader reader) throws XMLStreamException {
        String val = null;
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (isEndElement(event, EdmUtil.KEYS)) {
                break;
            }
            else if (isStartElement(event, EdmUtil.PROP_REFS)) {
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

    public void setNavigationProperties(List<NavigationProperty> navigationProperties) {
        this.navigationProperties = navigationProperties;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

}
