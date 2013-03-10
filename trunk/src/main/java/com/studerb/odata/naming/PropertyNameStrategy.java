package com.studerb.odata.naming;

import com.studerb.odata.atom.ODataLinkParser;
import com.studerb.odata.atom.ODataPropertyParser;

public interface PropertyNameStrategy {

    public ODataLinkParser createODataLinkParser();

    public ODataPropertyParser createODataPropertyParser();

}
