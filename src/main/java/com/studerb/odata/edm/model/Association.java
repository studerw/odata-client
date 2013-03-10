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

public class Association {
    final Logger log = LoggerFactory.getLogger(Association.class);

    private Schema schema;
    private String name;
    private List<AssociationEnd> ends = Lists.newArrayList();

    public Association() {}

    public Association(Schema schema) {
        this.schema = schema;
    }

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
			if (isEndElement(event, EdmUtil.ASSOCIATIONS)) {
                return;
            }
			else if (isStartElement(event, EdmUtil.ENDS)) {
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
            if (att.getName().getLocalPart().equalsIgnoreCase("Name")) {
                this.log.debug("name: " + att.getValue());
                this.name = att.getValue();
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

    public List<AssociationEnd> getEnds() {
        return this.ends;
    }

    public void setEnds(List<AssociationEnd> ends) {
        this.ends = ends;
    }

}
