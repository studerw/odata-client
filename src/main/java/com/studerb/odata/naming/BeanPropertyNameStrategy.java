package com.studerb.odata.naming;

import com.studerb.odata.atom.BeanNameLinkParser;
import com.studerb.odata.atom.BeanNamePropertyParser;
import com.studerb.odata.atom.ODataLinkParser;
import com.studerb.odata.atom.ODataPropertyParser;

/**
 * Property Naming Strategy that uses traditional JavaBean style naming
 * conversion to and from the OData model.
 *
 * @author William Studer
 */
public class BeanPropertyNameStrategy implements PropertyNameStrategy {

	public ODataLinkParser createODataLinkParser() {
        return new BeanNameLinkParser(this);
    }

    public ODataPropertyParser createODataPropertyParser() {
        return new BeanNamePropertyParser();
    }

}
