package com.studerb.odata.edm.model;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.atom.Namespaces;

public class ComplexType extends Type {
    final Logger log = LoggerFactory.getLogger(ComplexType.class);

    public ComplexType(Schema schema) {
        this.schema = schema;
    }

    @Override
    public void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException {
        setAttributes(startElement);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (Namespaces.isEndElement(event, Namespaces.COMPLEX_TYPES)) {
                return;
            }
            else if (Namespaces.isStartElement(event, Namespaces.COMPLEX_TYPES)) {
                ComplexType embedded = new ComplexType(this.schema);
                embedded.parse(startElement, reader);
                this.complexTypes.add(embedded);
            }
            else if (Namespaces.isStartElement(event, Namespaces.PROPERTIES)) {
                Property property = new Property(this);
                property.parse(event.asStartElement(), reader);
                this.properties.add(property);
            }
        }
    }

    @Override
    protected void setAttributes(StartElement startElement) {
        super.setAttributes(startElement);
        /*-
        Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
        }
         */
    }

}
