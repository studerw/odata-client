/*
 * $Id: StringConversionTest.java 5 2013-03-12 06:24:16Z stbill79 $
 *
 * Copyright (c) 2013 William Studer
 */
package com.studerb.odata.generate;

import java.beans.Introspector;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.edm.model.Association;
import com.studerb.odata.edm.model.AssociationEnd;
import com.studerb.odata.edm.model.NavigationProperty;
import com.studerb.odata.edm.model.Schema;

public class NavigationPropertyWrapper {
    final Logger log = LoggerFactory.getLogger(NavigationPropertyWrapper.class);

    private final NavigationProperty navProperty;
    private final Association association;

    public NavigationPropertyWrapper(NavigationProperty navProperty) {
        this.navProperty = navProperty;
        Schema schema = this.navProperty.getEntityType().getSchema();
        this.association = schema.getAssociationByName(navProperty.getRelationship());
        this.log.debug("Got Association: " + this.association.getName());
    }

    public String getJavaNavType() {
        AssociationEnd toEnd = getToEndRole();
        String type = toEnd.getType();
        Schema schema = this.navProperty.getEntityType().getSchema();
        String namespace = schema.getNamespace();
        String packageName = namespace + ".";
        if (StringUtils.startsWith(type, packageName)) {
            String temp = type.substring(packageName.length());
            this.log.debug("removing package name from " + type + " --> " +  temp);
            type = temp;
        }
        return type;
    }


    public boolean isMultiple() {
        AssociationEnd toEnd = getToEndRole();
        return "*".equals(toEnd.getMultiplicity());
    }

    public String getJavaName() {
        return Introspector.decapitalize(this.navProperty.getName());
    }

    public String getGetter() {
        return "get" + StringUtils.capitalize(getJavaName());
    }

    public String getSetter() {
        return "set" + StringUtils.capitalize(getJavaName());
    }

    public Association getAssociation() {
        return this.association;
    }

    private AssociationEnd getToEndRole() {
        String toRole = this.navProperty.getToRole();
        for (AssociationEnd end : this.association.getEnds()) {
            if (end.getRole().equals(toRole)) {
                return end;
            }
        }
        throw new RuntimeException("Error creating navigation property for: " + this.navProperty.getName() + " while looking for nav toRole: " + toRole);
    }

}
