package com.studerb.odata.naming;

import com.studerb.odata.atom.ODataLinkParser;
import com.studerb.odata.atom.ODataPropertyParser;
import com.studerb.odata.atom.RestletLinkParser;
import com.studerb.odata.atom.RestletPropertyParser;

/**
 * Property Name Strategy that uses 'Restlet' style naming of
 *         properties and links
 */
public class RestletPropertyNameStrategy implements PropertyNameStrategy {

    public ODataLinkParser createODataLinkParser() {
        return new RestletLinkParser(this);
    }

    public ODataPropertyParser createODataPropertyParser() {
        return new RestletPropertyParser();
    }

}
