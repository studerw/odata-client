package com.studerb.odata;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.studerb.odata.naming.BeanPropertyNameStrategy;

public class ODataClientTest {
    private static final Logger log = Logger.getLogger(ODataClientTest.class);
    static final String NORTHWIND_URI = "http://services.odata.org/Northwind/Northwind.svc";

    @Test
    public void initializeByString() {
        ODataClient client = new ODataClient(NORTHWIND_URI, new BeanPropertyNameStrategy());
        assertNotNull(client);
        assertNotNull(client.getHttpClient());
        String uri = client.getServiceURI().toString();
        log.debug(uri);
        assertEquals(uri, NORTHWIND_URI);
    }

    @Test
    public void initializeByURI() throws URISyntaxException {
        ODataClient client = new ODataClient(new URI(NORTHWIND_URI), new BeanPropertyNameStrategy());
        assertNotNull(client);
        assertNotNull(client.getHttpClient());
        String uri = client.getServiceURI().toString();
        log.debug(uri);
        log.debug(client.getServiceURI().getPort());
        assertEquals(uri, NORTHWIND_URI);
    }

    /**
     * Non-explicit ports cause URI#getPort to return -1, even though the
     * underlying ports will be 443, 80, or whatever default.
     * 
     * @throws URISyntaxException
     */
    @Test
    public void schemaCheck() throws URISyntaxException {
        URI uri = new URI("https://www.google.com/blah/abc.txt");
        log.debug("URI SCHEMA: " + uri.getScheme());
        assertEquals(uri.getScheme(), "https");
        log.debug("URI PORT: " + uri.getPort());
        assertTrue(uri.getPort() == -1);

    }
}
