package com.studerb.odata.northwind;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.parser.ParserOptions;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.studerb.odata.atom.BeanNamePropertyParser;
import com.studerb.odata.atom.ODataPropertyParser;
import com.studerb.odata.northwind.model.Customer;

public class NwBeanUtilsPropertyParserTest {

    final static Logger log = Logger.getLogger(NwBeanUtilsPropertyParserTest.class);
    static Abdera abdera;
    static Parser parser;

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @BeforeClass
    static public void beforeClass() {
        abdera = new Abdera();
        parser = abdera.getParser();
        ParserOptions parserOptions = parser.getDefaultParserOptions();
        parserOptions.setMustPreserveWhitespace(true);
        parser.setDefaultParserOptions(parserOptions);
    }

    @Test
    public void parseOneCustomerProps() {
        InputStream input = this.getClass().getResourceAsStream("one_customer.xml");
        Document<Entry> entryDoc = parser.parse(input);
        Entry entry = entryDoc.getRoot();
        ODataPropertyParser propParser = new BeanNamePropertyParser<Customer>();
        Customer customer = new Customer();
        propParser.parse(customer, entry);
        log.info(customer);
        assertEquals(customer.getCustomerID(), "ALFKI");
        assertEquals(customer.getCompanyName(), "Alfreds Futterkiste");
        assertEquals(customer.getContactName(), "Maria Anders");
        assertEquals(customer.getContactTitle(), "Sales Representative");
        assertNull(customer.getRegion());
        assertEquals(customer.getPostalCode(), "12209");
        assertEquals(customer.getCountry(), "Germany");
        assertEquals(customer.getCity(), "Berlin");
        assertEquals(customer.getFax(), "030-0076545");
        assertNull(customer.getOrders());
        IOUtils.closeQuietly(input);
    }

    @Test
    public void parseOneCustomerExpandedOrders() {
        InputStream input = this.getClass().getResourceAsStream("one_customer_expand_orders.xml");
        Document<Entry> entryDoc = parser.parse(input);
        Entry entry = entryDoc.getRoot();
        ODataPropertyParser propParser = new BeanNamePropertyParser<Customer>();
        Customer customer = new Customer();
        propParser.parse(customer, entry);
        log.info(customer);
        assertEquals(customer.getCustomerID(), "ALFKI");
        assertEquals(customer.getCompanyName(), "Alfreds Futterkiste");
        assertEquals(customer.getContactName(), "Maria Anders");
        assertEquals(customer.getContactTitle(), "Sales Representative");
        assertNull(customer.getRegion());
        assertEquals(customer.getPostalCode(), "12209");
        assertEquals(customer.getCountry(), "Germany");
        assertEquals(customer.getCity(), "Berlin");
        assertEquals(customer.getFax(), "030-0076545");
        assertNull(customer.getOrders());
        IOUtils.closeQuietly(input);
    }

}
