package com.studerb.odata;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.studerb.odata.atom.ODataEntryParser;
import com.studerb.odata.atom.ODataFeedParser;

/**
 * <p>
 * Class created via the {@link com.studerb.odata.ODataClient ODataClient} that
 * is used to obtain marshalled Pojo objects, inlinecount, next page skip
 * tokens, etc via a query to an OData web service.
 * </p>
 * <p>
 * Unlike the .Net version of the query, this object does <strong>NOT</strong>
 * follow skip links or do paging automatically via the iterator. In other
 * words, when using the iterator, only those results returned in the
 * once-executed HTTP GET are returned; the user must specifically obtain the
 * next page of results using the skip token and a new query manually if
 * available.
 * </p>
 * <p>
 * The {@link com.studerb.odata.ODataClient ODataClient} itself is thread safe,
 * though these query objects are not and should not be shared among multiple
 * threads. They are light weight and can be created once per service call.
 * </p>
 * The top level object to be marshalled must have bean properties that are
 * parsable via the underlying
 * {@link com.studerb.odata.atom.ODataPropertyParser} object. Currently, the
 * default parser is the {@link com.studerb.odata.atom.BeanNamePropertyParser}
 * which expects a Java bean compliant format based on the exact names used by
 * the underlying service and its metadata description doc. </p>
 *
 * @author Bill Studer
 *
 * @param <T>
 *            The type of object that will marhalled and returned via the
 *            iterator interface
 *
 * @see com.studerb.odata.atom.ODataPropertyParser
 * @see <a
 *      href="http://msdn.microsoft.com/en-us/library/cc646677.aspx">Microsoft's
 *      DataServiceQuery Class via MSDN</a>
 */
public class ODataQuery<T> implements java.lang.Iterable<T> {
    private final Logger log = LoggerFactory.getLogger(ODataQuery.class);
    final static String HEADER_VERSION = "DataServiceVersion";
    final static String HEADER_MAX_VERSION = "MaxDataServiceVersion";
    final static String HEADER_ACCEPT_TYPE = "application/atom+xml";
    private ODataFeedParser<T> feedParser;
    boolean executed = false;
    Class<T> type;
    private final ODataClient client;
    private final String queryPath;
    private URI finalURI;
    private List<T> results;
    boolean isFeed = false;
    List<NameValuePair> queryParams = new ArrayList<NameValuePair>();

    protected ODataQuery(Class<T> type, ODataClient odataClient, String queryPath) {
        this.type = type;
        this.client = odataClient;
        this.queryPath = queryPath == null ? "" : queryPath;
    }

    /**
     * Add a general query parameter
     *
     * @param key
     *            query parameter key
     * @param value
     *            query parameter value
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementation may return
     */
    public ODataQuery addQueryParam(String key, String value) {
        this.queryParams.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * <p>
     * This method will perform the actual HTTP call to the underlying web
     * service and marshall the results as one or more POJO objects obtainable
     * via the iterator. However, using the iterator itself will peform this
     * call itself if not manually done.
     * </p>
     * Attempts to call this method more than once for the same ODataQuery
     * object will result in a RuntimeException.
     */
    public void execute() {
        if (this.executed) {
            throw new IllegalStateException("Cannot execute a ODataQuery object more than one time");
        }
        this.executed = true;
        URI finalUri = this.getFullURI();
        HttpGet request = new HttpGet(finalUri);
        request.setHeader("Accept", HEADER_ACCEPT_TYPE);
        // request.setHeader(HEADER_VERSION,
        // this.client.getDataServiceVersion());
        request.setHeader(HEADER_MAX_VERSION, this.client.getMaxDataServiceVersion());
        HttpClient client = this.client.getHttpClient();
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                EntityUtils.consume(entity);
                throw new RuntimeException("Bad Response to query: " + finalUri + " / "
                        + response.getStatusLine().toString());
            }
            if (entity != null) {
                InputStream in = entity.getContent();
                Document doc = Abdera.getInstance().getParser().parse(in);
                String qname = doc.getRoot().getQName().getLocalPart();
                this.log.debug(qname);
                if ("entry".equals(qname)) {
                    Entry entry = (Entry) doc.getRoot();
                    ODataEntryParser<T> parser = new ODataEntryParser<T>(this.client.getPropertyNameStrategy());
                    T result = parser.parse(this.type, entry);
                    EntityUtils.consume(entity);
                    this.results = Lists.newArrayList(result);
                }
                else if ("feed".equals(qname)) {
                    this.isFeed = true;
                    Feed feed = (Feed) doc.getRoot();
                    this.feedParser = new ODataFeedParser<T>(this.client.getPropertyNameStrategy());
                    this.results = this.feedParser.parse(this.type, feed);
                    EntityUtils.consume(entity);
                }
                else {
                    throw new RuntimeException("Parsing atom at: " + this.finalURI.toASCIIString() + "  - "
                            + "neither feed nor entry");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            this.log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param expansion
     *            OData expansion string (e.g. $expand=Orders,Orders/Employee)
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementations may return
     */
    public ODataQuery expand(String expansion) {
        this.queryParams.add(new BasicNameValuePair("$expand", expansion));
        return this;
    }

    /**
     * @param filter
     *            OData filter parameter (e.g. $filter=Country eq France)
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementations may return)
     */
    public ODataQuery filter(String filter) {
        this.queryParams.add(new BasicNameValuePair("$filter", filter));
        return this;
    }

    /**
     * @return URI based on current base service URL, query path, query
     *         parameters, etc. This can be called to obtain the full URI that
     *         was or will be used to make the query.
     */
    public URI getFullURI() {
        URI srvcRoot = this.client.getServiceURI();
        String finalPath = this.fixSlashes();

        try {
            return URIUtils.createURI(srvcRoot.getScheme(), srvcRoot.getHost(), srvcRoot.getPort(), finalPath,
                    URLEncodedUtils.format(this.queryParams, Charsets.UTF_8.toString()), null);
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Error creating final URI with base and path: %s / $s", srvcRoot
                    .toASCIIString(), this.queryPath));
        }
    }

    /**
     * @return the inline count returned within the Feed or null if it didn't
     *         exist. RuntimeException thrown when response is not of type Feed
     *         or the query has yet to be executed.
     */
    public Integer getInlineCount() {
        if (!this.executed) {
            throw new RuntimeException("Must execute the query before obtaining the results");
        }
        if (!this.isFeed) {
            throw new RuntimeException("Response was not of type FEED - inline count only contained within Feed docs");
        }
        return this.feedParser.getInlineCount();
    }

    /**
     * @return the logical skiptoken name or null if it doesn't exist within the
     *         feed.
     * @throws RuntimeException thrown when response is not of type Feed
     *         and/or the query has yet to be executed.
     */
    public String getSkipToken() {
        if (!this.executed) {
            throw new RuntimeException("Must execute the query before obtaining the results");
        }
        if (!this.isFeed) {
            throw new RuntimeException("Response was not of type FEED - skip tokens only contained within Feed docs");
        }
        return this.feedParser.getSkipToken();
    }

    /**
     * @return the Link containing the URL of the next page if server side
     *         paging is being used or null if the Feed did not contain a
     *         <code>next</code> link; RuntimeException thrown when response is
     *         not of type Feed and/or the query has yet to be executed.
     */
    public Link getSkipTokenLink() {
        if (!this.executed) {
            throw new RuntimeException("Must execute the query before obtaining the results");
        }
        if (!this.isFeed) {
            throw new RuntimeException("Response was not of type FEED - skip tokens only contained within Feed docs");
        }
        return this.feedParser.getSkipTokenLink();
    }

    /**
     * @param inlinecount
     *            OData filter parameter (e.g. $inlinecount=allpages)
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementations may return)
     */
    public ODataQuery inlinecount(String inlinecount) {
        this.queryParams.add(new BasicNameValuePair("$inlinecount", inlinecount));
        return this;
    }

    public Iterator<T> iterator() {
        if (!this.executed) {
            this.execute();
        }
        return this.results.iterator();
    }

    /*
     * @param select OData select parameter (e.g. $select=Country)
     *
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     * other implementations may return)
     */
    public ODataQuery select(String select) {
        this.queryParams.add(new BasicNameValuePair("$select", select));
        return this;
    }

    /**
     * @param skip
     *            OData skip count (e.g. $skip=10)
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementation may return
     */
    public ODataQuery skip(int skip) {
        this.queryParams.add(new BasicNameValuePair("$skip", String.valueOf(skip)));
        return this;
    }

    /**
     * @param top
     *            OData top parameter (e.g. $top=10)
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementations may return)
     */
    public ODataQuery top(int top) {
        this.queryParams.add(new BasicNameValuePair("$top", String.valueOf(top)));
        return this;
    }

    /**
     * @param orderby
     *            OData queryby paramater
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementations may return)
     */
    public ODataQuery orderby(String orderby) {
        this.queryParams.add(new BasicNameValuePair("$orderby", orderby));
        return this;
    }

    /**
     * @param skiptoken
     *            OData skiptoken paramater
     * @return the same ODataQuery (this is <strong>NOT</strong> a new object as
     *         other implementations may return)
     */
    public ODataQuery skiptoken(String skiptoken) {
        this.queryParams.add(new BasicNameValuePair("$skiptoken", skiptoken));
        return this;
    }

    /**
     * @return root service and query path with correct single slash between the
     *         two
     */
    private String fixSlashes() {
        String fixedPath;
        String root = this.client.getServiceURI().getPath();
        if (root.endsWith("/")) {
            if (this.queryPath.startsWith("/")) {
                fixedPath = root + this.queryPath.substring(1);
            }
            else {
                fixedPath = root + this.queryPath;
            }
        }
        else {
            if (this.queryPath.startsWith("/")) {
                fixedPath = root + this.queryPath;
            }
            else {
                fixedPath = root + "/" + this.queryPath;
            }
        }
        return fixedPath;
    }

}
