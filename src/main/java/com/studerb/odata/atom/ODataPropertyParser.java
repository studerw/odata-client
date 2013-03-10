package com.studerb.odata.atom;

import org.apache.abdera.model.Entry;

/**
 * Simple interface used for marshalling an objects <em>primitive</em>
 * properties to a Java Pojo object based on the returned OData Atom Entry.
 * 
 * @author Bill Studer
 * 
 * @param <T>
 */
public interface ODataPropertyParser<T> {

    /**
     * @param target
     *            the java pojo type to which the properties are to be
     *            marshalled and converted
     * @param entry
     *            The Apache Abdera Atom entry object containing the inline
     *            section of properties
     */
    public void parse(T target, Entry entry);

}
