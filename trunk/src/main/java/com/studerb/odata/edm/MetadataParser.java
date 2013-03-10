package com.studerb.odata.edm;

import static com.studerb.odata.edm.EdmUtil.DATA_SERVICES;
import static com.studerb.odata.edm.EdmUtil.EDMX;
import static com.studerb.odata.edm.EdmUtil.isStartElement;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.edm.model.DataService;
import com.studerb.odata.edm.model.Metadata;
import com.studerb.odata.generate.Generator;
import com.studerb.odata.generate.basic.DefaultGenerator;

/**
 * MetadataParser is used by the Generator to create a {@link Metadata Metadata}
 * object from an XML OData Metadata file.
 * 
 * Typically, one only uses this object directly when generating source code
 * using a {@link Generator} implementation.
 * 
 * @author Bill Studer
 * @see Generator
 * @see DefaultGenerator
 * 
 */
public class MetadataParser {
    final Logger log = LoggerFactory.getLogger(MetadataParser.class);

    Metadata metadata;
    XMLEventReader reader;
    BufferedInputStream in;

    /**
     * @param in The {@link java.io.InputStream} is closed upon return, regardless of success or failure.
     * @return a configured {@link com.studerb.odata.edm.model.Metadata} object
     * @throws Exception
     */
    public Metadata parseXml(InputStream in) throws Exception {
        try {
            this.in = new BufferedInputStream(in);
            parse();
            return this.metadata;
        }
        finally {
            IOUtils.closeQuietly(this.in);
        }
    }

    protected void parse() throws Exception {
        this.metadata = new Metadata();
        this.reader = XMLInputFactory.newInstance().createXMLEventReader(this.in);
        while (this.reader.hasNext()) {
            XMLEvent event = this.reader.nextEvent();
            if (event.isStartDocument()) {
                confirmEdmx();
            }
            else if (isStartElement(event, DATA_SERVICES)) {
                DataService dataService = new DataService();
                dataService.parse(event.asStartElement(), this.reader);
                this.metadata.getDataServices().add(dataService);
            }
            else if (event.isEndDocument()) {
                this.log.debug("Finished parsing doc - closing input stream");
            }
        }
    }

    protected void confirmEdmx() throws Exception {
        while (this.reader.hasNext()) {
            XMLEvent event = this.reader.nextEvent();
            if (event.isStartElement()) {
                StartElement root = event.asStartElement();
                this.log.debug("Expecting EDMX root element and received: " + root.getName().toString());
                if (!root.getName().equals(EDMX)) {
                    throw new IllegalArgumentException(String.format("Root of Xml Doc = %s - Only edmx files accepted", root.getName().toString()));
                }
                return;
            }
        }
    }

}
