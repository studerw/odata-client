package com.studerb.odata.atom;

import java.beans.Introspector;

import com.studerb.odata.naming.PropertyNameStrategy;

public class BeanNameLinkParser<T> extends AbstractAbderaLinkParser<T> {

    public BeanNameLinkParser(PropertyNameStrategy propNameStrategy) {
        this.propNameStrategy = propNameStrategy;
    }

    @Override
    public String getPropertyName() {
        return Introspector.decapitalize(getLink().getTitle());
    }

}
