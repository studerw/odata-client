package com.studerb.odata.atom;

import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.studerb.odata.naming.PropertyNameStrategy;

public class ODataFeedParser<T> {
    final Logger log = LoggerFactory.getLogger(ODataFeedParser.class);
    final static String SKIP_TOKEN = "$skiptoken";

    private Feed feed;
    private Class<T> type;
    private Integer inlineCount;
    private Link skipTokenLink;
    private String skipToken;
    private final PropertyNameStrategy propertyNameStrategy;
    boolean parsedInlineCount = false;
    boolean parsedSkipToken = false;

    public ODataFeedParser(PropertyNameStrategy propertyNameStrategy) {
        this.propertyNameStrategy = propertyNameStrategy;
    }

    public List<T> parse(Class<T> type, Feed feed) {
        this.feed = feed;
        this.type = type;
        List<T> entries = new ArrayList<T>(feed.getEntries().size());
        this.log.debug("Parsing feed of type: " + type.getName());
        for (Entry entry : feed.getEntries()) {
            ODataEntryParser<T> parser = new ODataEntryParser<T>(this.propertyNameStrategy);
            T temp = parser.parse(type, entry);
            entries.add(temp);
        }
        this.log.info(String.format("Parsed feed of type: %s to list of %d entities", type.getName(), entries.size()));
        return entries;
    }

    /**
     * @return inline count if exists within the feed.
     */
    public Integer getInlineCount() {
        if (!this.parsedInlineCount) {
            parseInlineCount();
        }
        return this.inlineCount;
    }

    /**
     * @return the Link containing the URL of the next page if server side
     *         paging is being used or null if the Feed did not contain a
     *         <code>next</code> link;
     */
    public Link getSkipTokenLink() {
        if (!this.parsedSkipToken) {
            parseSkipToken();
        }
        return this.skipTokenLink;
    }

    /**
     * @return the logical skiptoken name or null if it doesn't exist within the
     *         feed
     */
    public String getSkipToken() {
        if (!this.parsedSkipToken) {
            parseSkipToken();
        }
        return this.skipToken;
    }

    protected void parseInlineCount() {
        if (!this.parsedInlineCount) {

        }
        for (Element el : this.feed.getElements()) {
            if (el.getQName().equals(Namespaces.M_COUNT)) {
                try {
                    this.inlineCount = Integer.parseInt(el.getText());
                    this.log.debug("Got inline count: " + this.inlineCount);
                    break;
                }
                catch (Exception e) {
                    this.log.warn("Error parsing inline count: " + el.getText());
                }
            }
        }
        this.parsedInlineCount = true;
    }

    protected void parseSkipToken() {
        try {
            Link nextLink = this.feed.getLink("next");
            if (nextLink != null) {
                Link l = this.feed.getLink("next");
                List<NameValuePair> queryPairs = URLEncodedUtils.parse(l.getHref().toURI(), Charsets.UTF_8.name());
                for (NameValuePair nvp : queryPairs) {
                    if (nvp.getName().equals(SKIP_TOKEN)) {
                        this.skipToken = nvp.getValue();
                        this.skipTokenLink = nextLink;
                        this.log.debug("Found SkipToken: " + this.skipToken);
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            this.log.warn("Exception parsing for skiptoken", e);
        }
        this.parsedSkipToken = true;
    }
}
