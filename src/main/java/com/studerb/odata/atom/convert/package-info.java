/**
 * Converter classes used for creating Pojo java-typed properties from string-based OData Atom properties.
 * By default Apache Beanutils is used for converting from strings to Java types. The following types are added:
 * <p>
 * <ol>
 * <li>{@link com.studerb.odata.atom.convert.BinaryArrayByteConverter BinaryArrayByteConverter}: convert Base64 encoded <code>Edm.Binary</code>
 * strings to arrays of Java {{@link java.lang.Byte}.</li>
 * <li>{@link com.studerb.odata.atom.convert.BinaryArrayPrimitiveByteConverter BinaryArrayPrimitiveByteConverter}: convert Base64 encoded <code>Edm.Binary</code>
 * strings to arrays of primitive Java byte.</li>
 * <li>{@link com.studerb.odata.atom.convert.BinaryArrayPrimitiveByteConverter BinaryArrayPrimitiveByteConverter}: convert Base64 encoded <code>Edm.Binary</code>
 * strings to arrays of primitive Java byte.</li>
 *
 * </ol>
 *
 * @see <a href="http://commons.apache.org/proper/commons-beanutils/api/org/apache/commons/beanutils/converters/package-summary.html">BeanUtils Converter API</a>
 */
package com.studerb.odata.atom.convert;

