package com.studerb.odata.atom;

import java.net.URLDecoder;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.studerb.odata.naming.PropertyNameStrategy;

public class ODataEntryParser<T> {
    final Logger log = LoggerFactory.getLogger(ODataEntryParser.class);

    String entryId;
    Entry entry;
    Class<T> type;
    T target;
    PropertyNameStrategy propNameStrategy;

    public ODataEntryParser(PropertyNameStrategy strategy) {
        this.propNameStrategy = strategy;
    }

    public T parse(Class<T> type, Entry entry) {
        this.entry = entry;
        this.type = type;

        try {
            this.target = this.type.newInstance();
            ODataPropertyParser propParser = this.propNameStrategy.createODataPropertyParser();
            propParser.parse(this.target, entry);
            parseLinks();
            this.log.info(String.format("Parsing entry with ID: %s to type: %s", URLDecoder.decode(entry.getId().toASCIIString(), Charsets.UTF_8.name()), this.type
                    .getName()));
            return this.target;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void parseLinks() throws Exception {
        for (Link link : this.entry.getLinks()) {
            ODataLinkParser<T> linkParser = this.propNameStrategy.createODataLinkParser();
            linkParser.parse(this.type, this.target, link);
        }
    }

}
