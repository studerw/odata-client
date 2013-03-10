package com.studerb.odata.northwind;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.parser.ParserOptions;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.studerb.odata.atom.ODataFeedParser;
import com.studerb.odata.naming.BeanPropertyNameStrategy;
import com.studerb.odata.northwind.model.Category;
import com.studerb.odata.northwind.model.Customer;

public class NwFeedParserTest {
    final static Logger log = Logger.getLogger(NwFeedParserTest.class);
    static Abdera abdera;

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @BeforeClass
    static public void beforeClass() {
        abdera = new Abdera();
    }

    @Test
    public void parseAllCustomers() {
        Parser parser = abdera.getParser();
        ParserOptions parserOptions = parser.getDefaultParserOptions();
        parserOptions.setMustPreserveWhitespace(true);
        parserOptions.setAutodetectCharset(true);
        parser.setDefaultParserOptions(parserOptions);

        InputStream input = this.getClass().getResourceAsStream("Customers.xml");
        Document<Feed> feedDoc = parser.parse(input);
        Feed feed = feedDoc.getRoot();
        ODataFeedParser<Customer> parser1 = new ODataFeedParser<Customer>(new BeanPropertyNameStrategy());
        List<Customer> customers = parser1.parse(Customer.class, feed);
        assertTrue(customers.size() == 20);
        assertEquals(parser1.getSkipToken(), "'ERNSH'");
        assertNull(parser1.getInlineCount());
        for (Customer customer : customers) {
            assertFalse(StringUtils.isBlank(customer.getCustomerID()));
        }
        IOUtils.closeQuietly(input);
    }

    @Test
    public void parseAllCustomersInlineCount() throws URISyntaxException {
        Parser parser = abdera.getParser();
        ParserOptions parserOptions = parser.getDefaultParserOptions();
        parserOptions.setMustPreserveWhitespace(true);
        parserOptions.setAutodetectCharset(true);
        parser.setDefaultParserOptions(parserOptions);

        InputStream input = this.getClass().getResourceAsStream("Customers_ic.xml");
        Document<Feed> feedDoc = parser.parse(input);
        Feed feed = feedDoc.getRoot();
        ODataFeedParser<Customer> parser1 = new ODataFeedParser<Customer>(new BeanPropertyNameStrategy());
        List<Customer> customers = parser1.parse(Customer.class, feed);
        assertTrue(customers.size() == 20);
        assertEquals(parser1.getSkipToken(), "'ERNSH'");
        assertTrue(parser1.getInlineCount() == 91);
        for (Customer customer : customers) {
            assertFalse(StringUtils.isBlank(customer.getCustomerID()));
        }
        IOUtils.closeQuietly(input);
    }

    @Test
    public void parseAllCategoriesCount() throws URISyntaxException {
        Parser parser = abdera.getParser();
        ParserOptions parserOptions = parser.getDefaultParserOptions();
        parserOptions.setMustPreserveWhitespace(true);
        parserOptions.setAutodetectCharset(true);
        parser.setDefaultParserOptions(parserOptions);

        InputStream input = this.getClass().getResourceAsStream("Categories.xml");
        Document<Feed> feedDoc = parser.parse(input);
        Feed feed = feedDoc.getRoot();
        ODataFeedParser<Category> parser1 = new ODataFeedParser<Category>(new BeanPropertyNameStrategy());
        List<Category> categories = parser1.parse(Category.class, feed);
        assertTrue(categories.size() == 8);
        assertNull(parser1.getInlineCount());
        assertNull(parser1.getSkipToken());
        assertNull(parser1.getSkipTokenLink());
        for (Category category : categories) {
            assertNotNull(category.getCategoryID());
        }
        IOUtils.closeQuietly(input);
    }
}
