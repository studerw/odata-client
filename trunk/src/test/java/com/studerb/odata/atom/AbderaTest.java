package com.studerb.odata.atom;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.Parser;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AbderaTest {

    final static Logger log = Logger.getLogger(AbderaTest.class);
    static Abdera abdera;
    static Parser parser;

    @BeforeClass
    static public void beforeClass() {
        abdera = new Abdera();
        parser = abdera.getParser();
    }

    @Test(expected = ClassCastException.class)
    public void checkWrongCast() throws IOException {
        InputStream input = this.getClass().getResourceAsStream("Customers.xml");
        byte[] bytes = IOUtils.toByteArray(input);
        Assert.assertNotNull(input);
        Document<Entry> entryDoc = parser.parse(new ByteArrayInputStream(bytes));
        Entry entry = entryDoc.getRoot();
        log.debug(entry.toString());

    }

    @Test
    public void getFeedType() throws IOException {
        InputStream input = this.getClass().getResourceAsStream("Customers.xml");
        byte[] bytes = IOUtils.toByteArray(input);
        Assert.assertNotNull(input);

        Document<Element> elementDoc = parser.parse(new ByteArrayInputStream(bytes));
        QName qname = elementDoc.getRoot().getQName();
        String docType = qname.getLocalPart();
        log.debug(docType);
        assertEquals(docType, "feed");
    }

    @Test
    public void getEntryType() throws IOException {
        InputStream input = this.getClass().getResourceAsStream("one_customer.xml");
        Assert.assertNotNull(input);
        byte[] bytes = IOUtils.toByteArray(input);

        Document<Element> elementDoc = parser.parse(new ByteArrayInputStream(bytes));
        QName qname = elementDoc.getRoot().getQName();
        String docType = qname.getLocalPart();
        log.debug(docType);
        assertEquals(docType, "entry");
    }

    @Test
    public void getFeedOrEntryType() throws IOException {
        String[] files = new String[] { "one_customer.xml", "Customers.xml" };
        for (String file : files) {
            InputStream input = this.getClass().getResourceAsStream(file);
            byte[] bytes = IOUtils.toByteArray(input);
            Document<Element> elementDoc = parser.parse(new ByteArrayInputStream(bytes));
            String qname = elementDoc.getRoot().getQName().getLocalPart();
            log.debug(qname);
            if (qname.equals("entry")) {
                Entry entry = (Entry) elementDoc.getRoot();
                log.debug("Got Entry for file: " + file);
            }
            else if (qname.equals("feed")) {
                Feed feed = (Feed) elementDoc.getRoot();
                log.debug("Got Feed for file: " + file);
            }
            else {
                throw new RuntimeException("neither feed nor entry");
            }

        }

    }
}
