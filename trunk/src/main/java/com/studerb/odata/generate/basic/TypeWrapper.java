package com.studerb.odata.generate.basic;

import java.beans.Introspector;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.studerb.odata.edm.model.EntityType;
import com.studerb.odata.edm.model.NavigationProperty;
import com.studerb.odata.edm.model.Property;
import com.studerb.odata.edm.model.Type;

public class TypeWrapper {
    final Logger log = LoggerFactory.getLogger(TypeWrapper.class);
    private Type type;
    private List<PropertyWrapper> propWrappers = Lists.newArrayList();
    private List<NavigationPropertyWrapper> navPropWrappers = Lists.newArrayList();

    final static List<String> reservedWords = Lists.newArrayList("assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
            "default", "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while");

    public TypeWrapper(Type type) {
        this.type = type;
        for (Property p : this.type.getProperties()) {
            this.propWrappers.add(new PropertyWrapper(p));
        }
        if (type instanceof EntityType) {
            EntityType et = (EntityType) type;
            for (NavigationProperty navProp : et.getNavigationProperties()) {
                this.navPropWrappers.add(new NavigationPropertyWrapper(navProp));
            }
        }
    }

    public String getJavaName() {
        String name = this.type.getName();
        if (isReserved(name)) {
            this.log.warn("Type name: " + name + " is Java reserved word.");
            name = "RESERVED_" + name;
        }
        return Introspector.decapitalize(name);
    }

    public String getClassName() {
        return StringUtils.capitalize(getJavaName());
    }

    public String getFullClassName() {
        return getPackageName() + "." + StringUtils.capitalize(getJavaName());
    }

    public String getPackageName() {
        String pack = this.type.getSchema().getNamespace();
        if (isReserved(pack)) {
            this.log.warn("Namespace: " + pack + " is Java reserved word...");
            pack = "RESERVED_" + pack;
        }
        return pack;
    }

    public String print() {
        StringBuilder bldr = new StringBuilder();
        bldr.append(getFullClassName()).append("\n");
        for (PropertyWrapper pw : this.propWrappers) {
            bldr.append("\t").append(pw.getJavaClassType()).append(" ").append(pw.getJavaName()).append("\n");
        }
        return bldr.toString();
    }

    public static boolean isReserved(String name) {
        return (reservedWords.contains(name));
    }

    public List<PropertyWrapper> getPropWrappers() {
        return this.propWrappers;
    }

    public List<NavigationPropertyWrapper> getNavPropWrappers() {
        return this.navPropWrappers;
    }

}
