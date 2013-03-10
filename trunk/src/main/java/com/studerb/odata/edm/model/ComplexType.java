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

public class ComplexType extends Type {
    final Logger log = LoggerFactory.getLogger(ComplexType.class);

    private List<ComplexType> complexTypes = Lists.newArrayList();

    public ComplexType() {}

    public ComplexType(Schema schema) {
        this.schema = schema;
    }

    public void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException {
        setAttributes(startElement);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
			if (isEndElement(event, EdmUtil.COMPLEX_TYPES)) {
                return;
            }
			else if (isStartElement(event, EdmUtil.COMPLEX_TYPES)) {
                ComplexType embedded = new ComplexType(this.schema);
                embedded.parse(startElement, reader);
                this.complexTypes.add(embedded);
            }
			else if (isStartElement(event, EdmUtil.PROPERTIES)) {
                Property property = new Property(this);
                property.parse(event.asStartElement(), reader);
                this.properties.add(property);
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

    public List<ComplexType> getComplexTypes() {
        return this.complexTypes;
    }

    public void setComplexTypes(List<ComplexType> complexTypes) {
        this.complexTypes = complexTypes;
    }

}
