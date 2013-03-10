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

public class DataService {
    final Logger log = LoggerFactory.getLogger(DataService.class);
	final static String DATA_SERVICE_VERSION = "DataServiceVersion";
	final static String MAX_DATA_SERVICE_VERSION = "MaxDataServiceVersion";
    private String version;
    private String maxVersion;

	private List<Schema> schemas = Lists.newArrayList();

    public void parse(StartElement startElement, XMLEventReader reader) throws XMLStreamException {
        setAttributes(startElement);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
			if (isEndElement(event, EdmUtil.DATA_SERVICES)) {
                return;
            }
			else if (isStartElement(event, EdmUtil.SCHEMAS)) {
                this.log.debug("Adding new Schema...");
				Schema schema = new Schema(this);
                schema.parse(event.asStartElement(), reader);
                this.schemas.add(schema);
            }
        }
    }

    private void setAttributes(StartElement startElement) {
        Iterator<?> iter = startElement.getAttributes();
        while (iter.hasNext()) {
            Attribute att = (Attribute) iter.next();
            if (att.getName().getLocalPart().equalsIgnoreCase(DATA_SERVICE_VERSION)) {
                this.log.debug("DataService Version: " + att.getValue());
                this.version = att.getValue();
            }
			else if (att.getName().getLocalPart().equalsIgnoreCase(MAX_DATA_SERVICE_VERSION)) {
				this.log.debug("Max DataService Version: " + att.getValue());
				this.maxVersion = att.getValue();
			}
        }
    }

    public String getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Schema> getSchemas() {
        return this.schemas;
    }

    public void setSchemas(List<Schema> schemas) {
        this.schemas = schemas;
    }

}
