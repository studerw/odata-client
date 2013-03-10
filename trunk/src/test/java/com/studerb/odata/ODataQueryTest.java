package com.studerb.odata;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.studerb.odata.northwind.model.Customer;

public class ODataQueryTest {
    final static Logger log = Logger.getLogger(ODataQueryTest.class);
    final static String NORTHWIND_URI = "http://services.odata.org/Northwind/Northwind.svc";
    final static String CUSTOMER_URI = "http://services.odata.org/Northwind/Northwind.svc/Customer";

    @Test
    public void createQuery() throws URISyntaxException {
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI));
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customer");
        assertNotNull(query);
        log.debug(query.getFullURI().toASCIIString());
        // we use 'contains' due to the the extra query char '?' at end of URI
        assertTrue(query.getFullURI().toString().contains(CUSTOMER_URI));
    }

    @Test
    public void checkBaseSlash() {
        String withSlash = NORTHWIND_URI + "/";
        ODataClient client = new ODataClient(withSlash);
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customer");
        log.debug(query.getFullURI());
        assertTrue(query.getFullURI().toString().contains(CUSTOMER_URI));
    }

    @Test
    public void checkQuerySlash() {
        ODataClient client = new ODataClient(NORTHWIND_URI);
        ODataQuery<Customer> query = client.createQuery(Customer.class, "/Customer");
        log.debug(query.getFullURI());
        assertTrue(query.getFullURI().toString().contains(CUSTOMER_URI));
    }

    @Test
    public void checkBaseAndQuerySlash() {
        String withSlash = NORTHWIND_URI + "/";
        ODataClient client = new ODataClient(withSlash);
        ODataQuery<Customer> query = client.createQuery(Customer.class, "/Customer");
        log.debug(query.getFullURI());
        assertTrue(query.getFullURI().toString().contains(CUSTOMER_URI));
    }

    @Test(expected = RuntimeException.class)
    public void checkUnexecutedInlineCount() throws URISyntaxException {
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI));
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customer");
        assertNotNull(query);
        query.getInlineCount();
        fail("should have thrown runtime exception on unexecuted query");
    }

    @Test(expected = RuntimeException.class)
    public void checkUnexecutedSkipToken() throws URISyntaxException {
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI));
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customer");
        assertNotNull(query);
        query.getSkipToken();
        fail("should have thrown runtime exception on unexecuted query");
    }

    @Test(expected = RuntimeException.class)
    public void checkUnexecutedSkipTokenLink() throws URISyntaxException {
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI));
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customer");
        assertNotNull(query);
        query.getSkipTokenLink();
        fail("should have thrown runtime exception on unexecuted query");
    }

    @Test
    public void createComplexURI() throws Exception {
        String path = "http://services.odata.org/Northwind/Northwind.svc/Customers?$expand=Orders,Orders/Employee&$top=10&$skip=5&$filter=Country eq France&$select=first_name&$orderby=foo desc&$inlinecount=allpages&foo=bar&cat=sam";
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI));
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customers");
        query.expand("Orders,Orders/Employee").top(10).skip(5).filter("Country eq France").select("first_name").orderby("foo desc").inlinecount("allpages")
                .addQueryParam("foo", "bar").addQueryParam("cat", "sam");
        String createdPath = URLDecoder.decode(query.getFullURI().toASCIIString(), Charsets.UTF_8.toString());
        log.debug(createdPath);
        assertEquals(createdPath, path);
    }

    @Test
    public void createSimpleURI() throws Exception {
        String path = "http://services.odata.org/Northwind/Northwind.svc/Customers?$top=10&$skip=5";
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI));
        ODataQuery<Customer> query = client.createQuery(Customer.class, "Customers").top(10).skip(5);
        String createdPath = URLDecoder.decode(query.getFullURI().toASCIIString(), Charsets.UTF_8.toString());
        log.debug(createdPath);
        assertEquals(createdPath, path);
    }

}
