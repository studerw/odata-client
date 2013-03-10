package com.studerb.odata.northwind;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.studerb.odata.ODataClient;
import com.studerb.odata.ODataQuery;
import com.studerb.odata.naming.BeanPropertyNameStrategy;
import com.studerb.odata.northwind.model.*;

public class NwHttpClientTest {
    final static Logger log = Logger.getLogger(NwHttpClientTest.class);
    final static String NORTHWIND_SERVICE = "http://services.odata.org/Northwind/Northwind.svc";
    final static String BIG_EXPAND = "Orders, Orders/Employee, Orders/Order_Details, Orders/Shipper,Orders/Customer,Orders/Employee/Orders,Orders/Employee/Territories,Orders/Shipper,Orders/Employee/Orders/Order_Details";
    static ODataClient odataClient;

    @BeforeClass
    public static void beforeClass() {
        odataClient = new ODataClient(NORTHWIND_SERVICE, new BeanPropertyNameStrategy());
    }

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public void baseService() {
        ODataQuery<Customer> query = odataClient.createQuery(Customer.class, "");
        log.debug(query.getFullURI().toASCIIString());
        assertTrue(query.getFullURI().toASCIIString().contains(NORTHWIND_SERVICE));
    }

    @Test
    public void getCustomers() {
        ODataQuery<Customer> query = odataClient.createQuery(Customer.class, "Customers").top(10).inlinecount(
                "allpages");
        List<Customer> customers = Lists.newArrayList(query.iterator());
        assertTrue(customers.size() == 10);
        assertNull(query.getSkipToken());
        assertNull(query.getSkipTokenLink());
        assertTrue(query.getInlineCount() > 0);
        log.debug("inlineCount: " + query.getInlineCount());
        for (Customer customer : customers) {
            assertFalse(StringUtils.isBlank(customer.getCustomerID()));
        }
        log.debug(customers);
    }

    @Test
    public void getTenErrorsInARow() {
        int errorCount = 0;
        for (int i = 0; i < 10; ++i) {
            String guid = UUID.randomUUID().toString();
            ODataQuery<Customer> query = odataClient
                    .createQuery(Customer.class, String.format("Customers('%s')", guid));
            try {
                query.execute();
                fail("Should have caught exception");
            }
            catch (Exception e) {
                errorCount++;
            }
        }
        assertTrue(errorCount == 10);
    }

    @Test
    public void getPhotos() throws IOException {
        ODataQuery<Employee> query = odataClient.createQuery(Employee.class, "Employees").top(5)
                .inlinecount("allpages");
        List<Employee> employees = Lists.newArrayList(query.iterator());
        assertTrue(employees.size() == 5);
        for (Employee employee : employees) {
            String suffix = FilenameUtils.getName(employee.getPhotoPath());
            File f = File.createTempFile("ODATA_CLIENT", suffix);
            FileUtils.writeByteArrayToFile(f, ArrayUtils.toPrimitive(employee.getPhoto()));
            log.debug("Wrote file to: " + f.getPath());
        }
    }

    @Test
    public void getOneSimpleCustomer() {
        ODataQuery<Customer> query = odataClient.createQuery(Customer.class, "Customers('ALFKI')");
        Customer customer = Iterators.getOnlyElement(query.iterator());
        log.debug(customer);
        assertNotNull(customer);
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
    }

    @Test
    public void getOneCustomerExpanded() {
        ODataQuery<Customer> query = odataClient.createQuery(Customer.class, "Customers('ALFKI')").expand("Orders");
        Customer customer = Iterators.getOnlyElement(query.iterator());
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

    @Ignore
    @Test
    public void getOneCustomerExpandFullOrdersMore() {
        ODataQuery<Customer> query = odataClient.createQuery(Customer.class, "Customers('ALFKI')").expand(BIG_EXPAND);
        Customer customer = Iterators.getOnlyElement(query.iterator());
        assertNotNull(customer);
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
