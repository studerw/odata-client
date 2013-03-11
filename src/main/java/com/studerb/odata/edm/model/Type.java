package com.studerb.odata.edm.model;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class Type {
    protected List<Property> properties = Lists.newArrayList();
    protected String name;
    protected Schema schema;

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getName() {
        return this.name;
    }

    public Schema getSchema() {
        return this.schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

}
