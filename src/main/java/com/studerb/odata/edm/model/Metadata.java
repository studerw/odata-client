/*
 * $Id: StringConversionTest.java 5 2013-03-12 06:24:16Z stbill79 $
 *
 * Copyright (c) 2013 William Studer
 */
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
import com.studerb.odata.edm.EdmUtil;

public class Metadata {
    final Logger log = LoggerFactory.getLogger(Metadata.class);
    String version;
    private QName qName;
    private final List<Attribute> attributes = Lists.newArrayList();

    List<DataService> dataServices = Lists.newArrayList();

    public Metadata() {};

    public void parse(StartElement el, XMLEventReader reader) throws XMLStreamException {
        log.trace(EdmUtil.printStartElement(el));
        this.qName = el.getName();
        setAttributes(el);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (EdmUtil.isEndElement(event, EdmUtil.EDMX)) {
                return;
            }
            else if (EdmUtil.isStartElement(event, EdmUtil.DATA_SERVICES)) {
                DataService dataService = new DataService();
                dataService.parse(event.asStartElement(), reader);
                this.getDataServices().add(dataService);
            }
        }

    }

    private void setAttributes(StartElement startElement) {
        Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            this.attributes.add(att);
            if (att.getName().getLocalPart().equalsIgnoreCase("Version")) {
                this.log.debug("EDMX Version: " + att.getValue());
                this.version = att.getValue();
            }
        }

    }

    public List<DataService> getDataServices() {
        return this.dataServices;
    }

    public String getVersion() {
        return version;
    }

    public QName getqName() {
        return qName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
