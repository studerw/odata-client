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

public class AssociationSet {
    final Logger log = LoggerFactory.getLogger(AssociationSet.class);
    private String name;
    private String association;
    private final EntityContainer entityContainer;
    private final List<AssociationSetEnd> ends = Lists.newArrayList();
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    public AssociationSet(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
    }

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        this.qName = el.getName();
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (Namespaces.isEndElement(event, Namespaces.ASSOCIATION_SETS)) {
                return;
            }
            else if (Namespaces.isStartElement(event, Namespaces.ENDS)) {
                AssociationSetEnd associationSetEnd = new AssociationSetEnd(this);
                associationSetEnd.parse(event.asStartElement(), reader);
                this.ends.add(associationSetEnd);
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
            if (att.getName().getLocalPart().equalsIgnoreCase("Association")) {
                this.log.debug("Association: " + att.getValue());
                this.association = att.getValue();
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public String getAssociation() {
        return this.association;
    }

    public EntityContainer getEntityContainer() {
        return this.entityContainer;
    }

    public List<AssociationSetEnd> getEnds() {
        return this.ends;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
