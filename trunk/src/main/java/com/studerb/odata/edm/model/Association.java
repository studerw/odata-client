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

public class Association {
    final Logger log = LoggerFactory.getLogger(Association.class);

    private final Schema schema;
    private String name;
    private QName qName;

    private final List<Attribute> attributes = Lists.newArrayList();
    private final List<AssociationEnd> ends = Lists.newArrayList();

    public Association(Schema schema) {
        this.schema = schema;
    }

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        this.qName = el.getName();
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (Namespaces.isEndElement(event, Namespaces.ASSOCIATIONS)) {
                return;
            }
            else if (Namespaces.isStartElement(event, Namespaces.ENDS)) {
                AssociationEnd end = new AssociationEnd(this);
                end.parse(event.asStartElement(), reader);
                this.ends.add(end);
            }
        }
    }

    private void setAttributes(StartElement startElement) {
        Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            this.attributes.add(att);
            if (att.getName().getLocalPart().equalsIgnoreCase("Name")) {
                this.log.debug("name: " + att.getValue());
                this.name = att.getValue();
            }
        }
    }

    public Schema getSchema() {
        return this.schema;
    }

    public String getName() {
        return this.name;
    }

    public List<AssociationEnd> getEnds() {
        return this.ends;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
