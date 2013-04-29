package com.studerb.odata.atom;

import java.beans.Introspector;

/**
 * <p>
 * The is an implementation of the {@link com.studerb.odata.atom.ODataPropertyParser ODataPropertyParser} interface that
 * parses an Abdera Atom Entry object and sets and converts the appropriate properties on a created Java pojo
 * </p>
 * 
 * <p>
 * This instance performs parsing as follows:
 * <ol>
 * <li>Pull out the <em>inline</em> element contain the EDM primitive properties with the Atom Entry</li>
 * <li>For each property, find an appropriate bean setter based on the <strong>exact</strong> name used within the
 * propery element itself. The only modification to this <em>exact</em> rule is that properties whose names begin with a
 * <strong>single</strong> uppercase letter should be lowercase in the java pojo, as expected by the Javabeans spec.</li>
 * <li>If an approprately named and accessible setter is availble, convert the EDM type to the Java type. See below for
 * type conversion formats.</li>
 * <li>If an appropriate setter method does not exist for the property, a warning message is logged (using log4j's WARN
 * level) and parsing is continued.</li>
 * </ol>
 * </p>
 * <p>
 * Conversion Rules: Basically the default conversions used by Apache Commons BeanUtils are performed with several
 * additions listed below.
 * <ol>
 * <li>EDM DateTime - can be converted to either a {@link java.util.Date Java Date} or a
 * {@link org.joda.time.LocalDateTime Joda LocalDateTime}</li>
 * <li>EDM Binary - will by default be Base64 decoded if the underlying POJO type is an array of Java Bytes (i.e. Byte[]
 * and <strong>not</strong> primitive byte[])</li>
 * </ol>
 * 
 * For all other conversions, only those types that have a default string -> Pojo type named appropriately and
 * accessible by the BeanUtils converted will be made.
 * 
 * @author William Studer
 * 
 * @param <T>
 * 
 * @see <a href="http://www.odata.org/developers/protocols/overview">OData Specification </a>
 * @see <a href="http://commons.apache.org/beanutils/">Apache Commons BeanUtils</a>
 * @see <a href="http://www.oracle.com/technetwork/java/javase/tech/index-jsp-138795.html">Java Beans Documentation from
 *      Oracle / Sun</a>
 */
public class BeanNamePropertyParser<T> extends AbstractPropertyParser<T> {

    @Override
    public String getPropName(String name) {
        String propName = Introspector.decapitalize(name);
        return propName;
    }

}
