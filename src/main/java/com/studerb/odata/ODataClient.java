package com.studerb.odata;


import java.net.URI;
import java.net.URISyntaxException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.naming.BeanPropertyNameStrategy;
import com.studerb.odata.naming.PropertyNameStrategy;

/**
 * <p>
 * Thread safe class used to read from an OData Service. Only one of these needs to be created per app and/or OData
 * Service. (in other words, don't create a new ODataClient per query).
 * </p>
 * <p>
 * One is encouraged to use the ODataQuery object for queries that return feeds / entries in the ODATA Atom format.
 * However, to do ad-hoc queries returning basic XML and or text, one should just use the clients underlying Apache
 * HttpClient (4.x) Client via the {@link #getHttpClient() getHttpClient()}
 * </p>
 * 
 * @author William Studer
 * 
 * @see <a href="http://hc.apache.org/">Apache HttpComponents 4 Project</a>
 * @see <a href="http://hc.apache.org/httpcomponents-client-ga/tutorial/html/">Http Client Tutorial</a>
 * @see <a href="http://www.odata.org/">OData (Open Data Protocol)</a>
 */
public class ODataClient {

    protected final Logger log = LoggerFactory.getLogger(ODataClient.class);
    protected final URI serviceURI;
    private final int MAX_CONNECTIONS = 50;
    private HttpClient httpClient;
    private String dataServiceVersion = "1.0";
    private String maxDataServiceVersion = "2.0";
    private SSLContext sslContext;
    private PropertyNameStrategy propertyNameStrategy;

    /**
     * 
     * @param service
     *            the root or base of the OData web service with or without the final slash <code>(e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )</code>
     * @param propertyNameStrategy
     *            Strategy used to map properties between OData and Java
     */
    public ODataClient(URI service, PropertyNameStrategy propertyNameStrategy) {
        this.serviceURI = service;
        this.propertyNameStrategy = propertyNameStrategy;
        init();
    }

    /**
     * Default ODataClient uses a basic ThreadSafe DefaultHttpClient with
     * standard JavaBean Naming strategy to map between the ODATA model and Java
     * beans.
     *
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     */
    public ODataClient(URI service) {
        this.serviceURI = service;
        this.propertyNameStrategy = new BeanPropertyNameStrategy();
        init();
    }

    /**
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     * @param propertyNameStrategy
     *            Strategy used to map properties between OData and Java
     */
    public ODataClient(String service, PropertyNameStrategy propertyNameStrategy) {
        try {
            this.serviceURI = new URI(service);
            this.propertyNameStrategy = propertyNameStrategy;
            init();
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Default ODataClient uses a basic ThreadSafe DefaultHttpClient with
     * standard JavaBean Naming strategy to map between the ODATA model and Java
     * beans.
     *
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     */
    public ODataClient(String service) {
        try {
            this.serviceURI = new URI(service);
            this.propertyNameStrategy = new BeanPropertyNameStrategy();
            init();
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     * @param sslContext
     *            the customized SSL context to use for secure connections
     * @param propertyNameStrategy
     *            Strategy used to map properties between OData and Java
     */
    public ODataClient(URI service, SSLContext sslContext, PropertyNameStrategy propertyNameStrategy) {
        this.serviceURI = service;
        this.sslContext = sslContext;
        this.propertyNameStrategy = propertyNameStrategy;
        init();
    }

    /**
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     * @param sslContext
     *            the customized SSL context to use for secure connection
     * @param propertyNameStrategy
     *            Strategy used to map properties between OData and Java
     */
    public ODataClient(String service, SSLContext sslContext, PropertyNameStrategy propertyNameStrategy) {
        try {
            this.serviceURI = new URI(service);
            this.sslContext = sslContext;
            this.propertyNameStrategy = propertyNameStrategy;
            init();
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     * @param httpClient
     *            Apache 4 HttpClient appropriate configured
     * @param propertyNameStrategy
     *            Strategy used to map properties between OData and Java
     */
    public ODataClient(URI service, HttpClient httpClient, PropertyNameStrategy propertyNameStrategy) {
        this.serviceURI = service;
        this.httpClient = httpClient;
        this.propertyNameStrategy = propertyNameStrategy;
        init();
    }

    /**
     * @param service
     *            the root or base of the OData web service with or without the
     *            final slash (e.g.
     *            http://services.odata.org/Northwind/Northwind.svc OR
     *            http://services.odata.org/Northwind/Northwind.svc/ )
     * @param httpClient
     *            Apache 4 HttpClient appropriate configured
     * @param propertyNameStrategy
     *            Strategy used to map properties between OData and Java
     */
    public ODataClient(String service, HttpClient httpClient, PropertyNameStrategy propertyNameStrategy) {
        try {
            this.serviceURI = new URI(service);
            this.httpClient = httpClient;
            this.propertyNameStrategy = propertyNameStrategy;
            init();
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param <T>
     *            type of class that the query will create, parse at the top
     *            level, and return via its iterator interface.
     * @param type
     *            class type of results (e.g. Person.class)
     * @param path
     *            the path itself relative to base of service- for example
     *            <code>Categories</code> or <code>Customers('xyz')/Orders</code>
     * @return ODataQuery of type <t>
     */
    public <T> ODataQuery<T> createQuery(Class<T> type, String path) {
        return new ODataQuery<T>(type, this, path);
    }

    /**
     * @return the base URI of the service (e.g.
     *         http://services.odata.org/Northwind/Northwind.svc)
     */
    public URI getServiceURI() {
        return this.serviceURI;
    }

    /**
     * To perform custom configuration, one should extend this class, override
     * this method, and (possibly) call the base class method.
     */
    protected void init() {
        if (this.propertyNameStrategy == null) {
            this.propertyNameStrategy = new BeanPropertyNameStrategy();
        }

        if (this.httpClient == null) {
            this.httpClient = createDefaultHttpClient();
        }
    }

    protected DefaultHttpClient createDefaultHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        // Create and initialize scheme registry
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        // if no https, create generic socket factory
        String schema = this.serviceURI.getScheme();
        if (schema.equalsIgnoreCase("http")) {
            schemeRegistry.register(new Scheme("http", getPort(), PlainSocketFactory.getSocketFactory()));
        }
        else if (schema.equalsIgnoreCase("https")) {
            SSLSocketFactory sslSocketFactory;
            if (this.sslContext != null) {
                sslSocketFactory = new SSLSocketFactory(this.sslContext);
            }
            else {
                sslSocketFactory = SSLSocketFactory.getSocketFactory();
            }
            schemeRegistry.register(new Scheme("https", getPort(), sslSocketFactory));
        }
        else {
            throw new RuntimeException("Must use schema of type http OR https");
        }

        // TODO fix this
        ThreadSafeClientConnManager clientConnMgr = new ThreadSafeClientConnManager(schemeRegistry);
        clientConnMgr.setDefaultMaxPerRoute(this.MAX_CONNECTIONS);
        DefaultHttpClient tempClient = new DefaultHttpClient(clientConnMgr, params);
        tempClient.addRequestInterceptor(new RequestAcceptEncoding());
        tempClient.addResponseInterceptor(new ResponseContentEncoding());
        return tempClient;
    }

    public String getDataServiceVersion() {
        return this.dataServiceVersion;
    }

    public String getMaxDataServiceVersion() {
        return this.maxDataServiceVersion;
    }

    public void setDataServiceVersion(String dataServiceVersion) {
        this.dataServiceVersion = dataServiceVersion;
    }

    public void setMaxDataServiceVersion(String maxDataServiceVersion) {
        this.maxDataServiceVersion = maxDataServiceVersion;
    }

    /**
     * @return an instance of the underlying HttpClient
     * @see <a
     *      href="http://hc.apache.org/httpcomponents-client-ga/index.html">Apache
     *      HttpClient 4</a>
     */
    public HttpClient getHttpClient() {
        return this.httpClient;
    }


    /**
     * Utility method to create a Path using the service root and a query path
     * with a correct single slash between the two segments (e.g. if the root
     * were http://www.google.com/ and the path were /blah, this method would
     * return the string http://www.google.com/blah (without the extra slash)
     *
     * @param path
     * @return root service and query path with correct single slash between the
     *         two
     */
    public String fixSlashes(String path) {
        String fixedPath;
        String root = getServiceURI().toASCIIString();
        if (root.endsWith("/")) {
            if (path.startsWith("/")) {
                fixedPath = root + path.substring(1);
            }
            else {
                fixedPath = root + path;
            }
        }
        else {
            if (path.startsWith("/")) {
                fixedPath = root + path;
            }
            else {
                fixedPath = root + "/" + path;
            }
        }
        return fixedPath;
    }

    private int getPort() {
        int port = this.serviceURI.getPort();
        if (port == -1) {
            if (this.serviceURI.getScheme().equalsIgnoreCase("http")) {
                port = 80;
            }
            else if (this.serviceURI.getScheme().equalsIgnoreCase("https")) {
                port = 443;
            }
            else {
                throw new IllegalArgumentException("Invalid Port / Schema - must be explicit port or HTTP/HTTPS");
            }
        }
        return port;
    }

    public PropertyNameStrategy getPropertyNameStrategy() {
        return this.propertyNameStrategy;
    }
}
