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

public class AssociationSet {
    final Logger log = LoggerFactory.getLogger(AssociationSet.class);
    private String name;
    private String association;
    private EntityContainer entityContainer;
    private List<AssociationSetEnd> ends = Lists.newArrayList();

    public AssociationSet() {}

    public AssociationSet(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
    }

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
			if (isEndElement(event, EdmUtil.ASSOCIATION_SETS)) {
                return;
            }
			else if (isStartElement(event, EdmUtil.ENDS)) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAssociation() {
        return this.association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public EntityContainer getEntityContainer() {
        return this.entityContainer;
    }

    public void setEntityContainer(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
    }

    public List<AssociationSetEnd> getEnds() {
        return this.ends;
    }

    public void setEnds(List<AssociationSetEnd> ends) {
        this.ends = ends;
    }

}
