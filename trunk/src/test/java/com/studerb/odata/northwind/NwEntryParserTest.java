package com.studerb.odata.northwind;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.parser.ParserOptions;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.studerb.odata.atom.ODataEntryParser;
import com.studerb.odata.naming.BeanPropertyNameStrategy;
import com.studerb.odata.northwind.model.*;

public class NwEntryParserTest {

    final static Logger log = Logger.getLogger(NwEntryParserTest.class);
    static Abdera abdera;
    static Parser parser;
    InputStream input;

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {
        IOUtils.closeQuietly(input);
        input = null;
    }

    @BeforeClass
    static public void beforeClass() {
        abdera = new Abdera();
        parser = abdera.getParser();
        ParserOptions parserOptions = parser.getDefaultParserOptions();
        parserOptions.setMustPreserveWhitespace(true);
        parserOptions.setAutodetectCharset(true);
        parser.setDefaultParserOptions(parserOptions);
    }

    @Test
    public void parseOneCustomer() {
        input = this.getClass().getResourceAsStream("one_customer.xml");
        Document<Entry> entryDoc = parser.parse(input);
        Entry entry = entryDoc.getRoot();
        ODataEntryParser<Customer> entryParser = new ODataEntryParser<Customer>(new BeanPropertyNameStrategy());
        Customer customer = entryParser.parse(Customer.class, entry);
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
    public void parseOneCustomerExpandOrders() {
        input = this.getClass().getResourceAsStream("one_customer_expand_orders.xml");
        Document<Entry> entryDoc = parser.parse(input);
        Entry entry = entryDoc.getRoot();
        ODataEntryParser<Customer> entryParser = new ODataEntryParser<Customer>(new BeanPropertyNameStrategy());
        Customer customer = entryParser.parse(Customer.class, entry);
        log.info(customer);
        IOUtils.closeQuietly(input);
        assertEquals(customer.getCustomerID(), "ALFKI");
        assertEquals(customer.getCompanyName(), "Alfreds Futterkiste");
        assertEquals(customer.getContactName(), "Maria Anders");
        assertEquals(customer.getContactTitle(), "Sales Representative");
        assertNull(customer.getRegion());
        assertEquals(customer.getPostalCode(), "12209");
        assertEquals(customer.getCountry(), "Germany");
        assertEquals(customer.getCity(), "Berlin");
        assertEquals(customer.getFax(), "030-0076545");
        assertNotNull("Orders shouldn't be null", customer.getOrders());
        List<Order> orders = customer.getOrders();
        List<Integer> orderIds = Arrays.asList(10643, 10692, 10702, 10835, 10952, 11011);

        for (Order order : orders) {
            int orderId = order.getOrderID();
            assertTrue("orderID: " + orderId, orderIds.contains(orderId));
            assertNull(order.getCustomer());
            assertNull(order.getEmployee());
            assertNull(order.getOrder_Details());
            assertNull(order.getShipper());
            log.debug(order);
        }
    }

    @Test
    public void parseOneCustomerExpandFullOrders() {
        input = this.getClass().getResourceAsStream("one_customer_orders_full_expand.xml");
        Document<Entry> entryDoc = parser.parse(input);
        Entry entry = entryDoc.getRoot();
        ODataEntryParser<Customer> entryParser = new ODataEntryParser<Customer>(new BeanPropertyNameStrategy());
        Customer customer = entryParser.parse(Customer.class, entry);
        log.info(customer);
        IOUtils.closeQuietly(input);
        assertEquals(customer.getCustomerID(), "ALFKI");
        assertEquals(customer.getCompanyName(), "Alfreds Futterkiste");
        assertEquals(customer.getContactName(), "Maria Anders");
        assertEquals(customer.getContactTitle(), "Sales Representative");
        assertNull(customer.getRegion());
        assertEquals(customer.getPostalCode(), "12209");
        assertEquals(customer.getCountry(), "Germany");
        assertEquals(customer.getCity(), "Berlin");
        assertEquals(customer.getFax(), "030-0076545");
        assertNotNull("Orders shouldn't be null", customer.getOrders());
        List<Order> orders = customer.getOrders();
        List<Integer> orderIds = Arrays.asList(10643, 10692, 10702, 10835, 10952, 11011);

        for (Order order : orders) {
            log.info(order);
            int orderId = order.getOrderID();
            assertTrue("orderID: " + orderId, orderIds.contains(orderId));
            assertNotNull(order.getCustomer());
            assertEquals(order.getCustomer().getCustomerID(), "ALFKI");
            assertNotNull(order.getEmployee());
            assertNotNull(order.getOrder_Details());
            assertNotNull(order.getShipper());
            log.debug(order);
        }
    }

    @Test
    @Ignore
    public void parseOneCustomerExpandFullOrdersMore() {
        input = this.getClass().getResourceAsStream("one_customer_orders_full_expand_three.xml");
        Document<Entry> entryDoc = parser.parse(input);
        Entry entry = entryDoc.getRoot();
        ODataEntryParser<Customer> entryParser = new ODataEntryParser<Customer>(new BeanPropertyNameStrategy());
        Customer customer = entryParser.parse(Customer.class, entry);
        log.info(customer);
        IOUtils.closeQuietly(input);
        assertEquals(customer.getCustomerID(), "ALFKI");
        assertEquals(customer.getCompanyName(), "Alfreds Futterkiste");
        assertEquals(customer.getContactName(), "Maria Anders");
        assertEquals(customer.getContactTitle(), "Sales Representative");
        assertNull(customer.getRegion());
        assertEquals(customer.getPostalCode(), "12209");
        assertEquals(customer.getCountry(), "Germany");
        assertEquals(customer.getCity(), "Berlin");
        assertEquals(customer.getFax(), "030-0076545");
        assertNotNull("Orders shouldn't be null", customer.getOrders());
        List<Order> orders = customer.getOrders();
        List<Integer> orderIds = Lists.newArrayList(10643, 10692, 10702, 10835, 10952, 11011);
        List<Integer> collected = Lists.transform(orders, new Function<Order, Integer>() {
            @Override
            public Integer apply(Order input) {
                return input.getOrderID();
            }
        });
        assertEquals(collected, orderIds);

        for (Order order : orders) {
            log.info(order);
            int orderId = order.getOrderID();
            assertTrue("orderID: " + orderId, orderIds.contains(orderId));
            assertNotNull(order.getCustomer());
            assertEquals(order.getCustomer().getCustomerID(), "ALFKI");
            assertNotNull(order.getEmployee());
            assertNotNull(order.getEmployee().getOrders());
            assertNotNull(order.getOrder_Details());
            assertNotNull(order.getShipper());
            for (Order_detail order_detail : order.getOrder_Details()) {
                assertNotNull(order_detail.getOrderID());
                assertNull(order_detail.getProduct());
                log.debug(order_detail);
            }
            for (Order empOrder : order.getEmployee().getOrders()) {
                assertNotNull(empOrder.getOrderID());
                assertNotNull(empOrder.getOrder_Details());
                for (Order_detail order_detail : empOrder.getOrder_Details()) {
                    assertNotNull(order_detail.getOrderID());
                    assertNull(order_detail.getProduct());
                    log.debug(order_detail);
                }
            }
        }
    }
}
