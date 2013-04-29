package com.studerb.odata.atom;

import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @see <a href=http://www.odata.org/documentation/odata-v2-documentation/overview/#6_Primitive_Data_Types>Version 2</a>
 * @author Bill Studer
 * 
 */
public class Namespaces {
    public static final String NS_APP = "http://www.w3.org/2007/app";
    public static final String NS_XML = "http://www.w3.org/XML/1998/namespace";
    public static final String NS_ATOM = "http://www.w3.org/2005/Atom";

    public static final String NS_METADATA = "http://schemas.microsoft.com/ado/2007/08/dataservices/metadata";
    public static final String NS_DATASERVICES = "http://schemas.microsoft.com/ado/2007/08/dataservices";
    public static final String NS_EDM2006 = "http://schemas.microsoft.com/ado/2006/04/edm";
    public static final String NS_EDM2007 = "http://schemas.microsoft.com/ado/2007/05/edm";
    public static final String NS_EDM2008 = "http://schemas.microsoft.com/ado/2008/09/edm";
    public static final String NS_EDM2009 = "http://schemas.microsoft.com/ado/2009/08/edm";
    public final static String NS_EDM2009_2 = "http://schemas.microsoft.com/ado/2009/11/edm";

    public static final String NS_EDMX = "http://schemas.microsoft.com/ado/2007/06/edmx";
    public final static QName EDMX = new QName(NS_EDMX, "Edmx");

    public static final String NS_EDMANNOTATION = "http://schemas.microsoft.com/ado/2009/02/edm/annotation";

    public static final QName EDMX_DATASERVICES = new QName(NS_EDMX, "DataServices");

    public static final QName EDM2006_SCHEMA = new QName(NS_EDM2006, "Schema");
    public static final QName EDM2006_KEY = new QName(NS_EDM2006, "Key");
    public static final QName EDM2006_ENTITYTYPE = new QName(NS_EDM2006, "EntityType");
    public static final QName EDM2006_ASSOCIATION = new QName(NS_EDM2006, "Association");
    public static final QName EDM2006_COMPLEXTYPE = new QName(NS_EDM2006, "ComplexType");
    public static final QName EDM2006_ENTITYCONTAINER = new QName(NS_EDM2006, "EntityContainer");
    public static final QName EDM2006_ENTITYSET = new QName(NS_EDM2006, "EntitySet");
    public static final QName EDM2006_ASSOCIATIONSET = new QName(NS_EDM2006, "AssociationSet");
    public static final QName EDM2006_FUNCTIONIMPORT = new QName(NS_EDM2006, "FunctionImport");
    public static final QName EDM2006_PARAMETER = new QName(NS_EDM2006, "Parameter");
    public static final QName EDM2006_END = new QName(NS_EDM2006, "End");
    public static final QName EDM2006_PROPERTYREF = new QName(NS_EDM2006, "PropertyRef");
    public static final QName EDM2006_PROPERTY = new QName(NS_EDM2006, "Property");
    public static final QName EDM2006_NAVIGATIONPROPERTY = new QName(NS_EDM2006, "NavigationProperty");

    public static final QName EDM2007_SCHEMA = new QName(NS_EDM2007, "Schema");
    public static final QName EDM2007_KEY = new QName(NS_EDM2007, "Key");
    public static final QName EDM2007_ENTITYTYPE = new QName(NS_EDM2007, "EntityType");
    public static final QName EDM2007_ASSOCIATION = new QName(NS_EDM2007, "Association");
    public static final QName EDM2007_COMPLEXTYPE = new QName(NS_EDM2007, "ComplexType");
    public static final QName EDM2007_ENTITYCONTAINER = new QName(NS_EDM2007, "EntityContainer");
    public static final QName EDM2007_ENTITYSET = new QName(NS_EDM2007, "EntitySet");
    public static final QName EDM2007_ASSOCIATIONSET = new QName(NS_EDM2007, "AssociationSet");
    public static final QName EDM2007_FUNCTIONIMPORT = new QName(NS_EDM2007, "FunctionImport");
    public static final QName EDM2007_PARAMETER = new QName(NS_EDM2007, "Parameter");
    public static final QName EDM2007_END = new QName(NS_EDM2007, "End");
    public static final QName EDM2007_PROPERTYREF = new QName(NS_EDM2007, "PropertyRef");
    public static final QName EDM2007_PROPERTY = new QName(NS_EDM2007, "Property");
    public static final QName EDM2007_NAVIGATIONPROPERTY = new QName(NS_EDM2007, "NavigationProperty");

    public static final QName EDM2008_SCHEMA = new QName(NS_EDM2008, "Schema");
    public static final QName EDM2008_KEY = new QName(NS_EDM2008, "Key");
    public static final QName EDM2008_ENTITYTYPE = new QName(NS_EDM2008, "EntityType");
    public static final QName EDM2008_ASSOCIATION = new QName(NS_EDM2008, "Association");
    public static final QName EDM2008_COMPLEXTYPE = new QName(NS_EDM2008, "ComplexType");
    public static final QName EDM2008_ENTITYCONTAINER = new QName(NS_EDM2008, "EntityContainer");
    public static final QName EDM2008_ENTITYSET = new QName(NS_EDM2008, "EntitySet");
    public static final QName EDM2008_ASSOCIATIONSET = new QName(NS_EDM2008, "AssociationSet");
    public static final QName EDM2008_FUNCTIONIMPORT = new QName(NS_EDM2008, "FunctionImport");
    public static final QName EDM2008_PARAMETER = new QName(NS_EDM2008, "Parameter");
    public static final QName EDM2008_END = new QName(NS_EDM2008, "End");
    public static final QName EDM2008_PROPERTYREF = new QName(NS_EDM2008, "PropertyRef");
    public static final QName EDM2008_PROPERTY = new QName(NS_EDM2008, "Property");
    public static final QName EDM2008_NAVIGATIONPROPERTY = new QName(NS_EDM2008, "NavigationProperty");

    public static final QName EDM2009_SCHEMA = new QName(NS_EDM2009, "Schema");
    public static final QName EDM2009_KEY = new QName(NS_EDM2009, "Key");
    public static final QName EDM2009_ENTITYTYPE = new QName(NS_EDM2009, "EntityType");
    public static final QName EDM2009_ASSOCIATION = new QName(NS_EDM2009, "Association");
    public static final QName EDM2009_COMPLEXTYPE = new QName(NS_EDM2009, "ComplexType");
    public static final QName EDM2009_ENTITYCONTAINER = new QName(NS_EDM2009, "EntityContainer");
    public static final QName EDM2009_ENTITYSET = new QName(NS_EDM2009, "EntitySet");
    public static final QName EDM2009_ASSOCIATIONSET = new QName(NS_EDM2009, "AssociationSet");
    public static final QName EDM2009_FUNCTIONIMPORT = new QName(NS_EDM2009, "FunctionImport");
    public static final QName EDM2009_PARAMETER = new QName(NS_EDM2009, "Parameter");
    public static final QName EDM2009_END = new QName(NS_EDM2009, "End");
    public static final QName EDM2009_PROPERTYREF = new QName(NS_EDM2009, "PropertyRef");
    public static final QName EDM2009_PROPERTY = new QName(NS_EDM2009, "Property");
    public static final QName EDM2009_NAVIGATIONPROPERTY = new QName(NS_EDM2009, "NavigationProperty");

    public static final QName EDM2009_2_SCHEMA = new QName(NS_EDM2009_2, "Schema");
    public static final QName EDM2009_2_KEY = new QName(NS_EDM2009_2, "Key");
    public static final QName EDM2009_2_ENTITYTYPE = new QName(NS_EDM2009_2, "EntityType");
    public static final QName EDM2009_2_ASSOCIATION = new QName(NS_EDM2009_2, "Association");
    public static final QName EDM2009_2_COMPLEXTYPE = new QName(NS_EDM2009_2, "ComplexType");
    public static final QName EDM2009_2_ENTITYCONTAINER = new QName(NS_EDM2009_2, "EntityContainer");
    public static final QName EDM2009_2_ENTITYSET = new QName(NS_EDM2009_2, "EntitySet");
    public static final QName EDM2009_2_ASSOCIATIONSET = new QName(NS_EDM2009_2, "AssociationSet");
    public static final QName EDM2009_2_FUNCTIONIMPORT = new QName(NS_EDM2009_2, "FunctionImport");
    public static final QName EDM2009_2_PARAMETER = new QName(NS_EDM2009_2, "Parameter");
    public static final QName EDM2009_2_END = new QName(NS_EDM2009_2, "End");
    public static final QName EDM2009_2_PROPERTYREF = new QName(NS_EDM2009_2, "PropertyRef");
    public static final QName EDM2009_2_PROPERTY = new QName(NS_EDM2009_2, "Property");
    public static final QName EDM2009_2_NAVIGATIONPROPERTY = new QName(NS_EDM2009_2, "NavigationProperty");

    public static final QName ATOM_FEED = new QName(NS_ATOM, "feed");
    public static final QName ATOM_ENTRY = new QName(NS_ATOM, "entry");
    public static final QName ATOM_ID = new QName(NS_ATOM, "id");
    public static final QName ATOM_TITLE = new QName(NS_ATOM, "title");
    public static final QName ATOM_SUMMARY = new QName(NS_ATOM, "summary");
    public static final QName ATOM_UPDATED = new QName(NS_ATOM, "updated");
    public static final QName ATOM_CATEGORY = new QName(NS_ATOM, "category");
    public static final QName ATOM_CONTENT = new QName(NS_ATOM, "content");
    public static final QName ATOM_LINK = new QName(NS_ATOM, "link");

    public static final QName APP_WORKSPACE = new QName(NS_APP, "workspace");
    public static final QName APP_SERVICE = new QName(NS_APP, "service");
    public static final QName APP_COLLECTION = new QName(NS_APP, "collection");
    public static final QName APP_ACCEPT = new QName(NS_APP, "accept");

    public static final QName M_ETAG = new QName(NS_METADATA, "etag");
    public static final QName M_PROPERTIES = new QName(NS_METADATA, "properties");
    public static final QName M_INLINE = new QName(NS_METADATA, "inline");
    public static final QName M_COUNT = new QName(NS_METADATA, "count");
    public static final QName M_TYPE = new QName(NS_METADATA, "type");
    public static final QName M_NULL = new QName(NS_METADATA, "null");
    public static final QName M_FC_TARGETPATH = new QName(NS_METADATA, "FC_TargetPath");
    public static final QName M_FC_CONTENTKIND = new QName(NS_METADATA, "FC_ContentKind");
    public static final QName M_FC_KEEPINCONTENT = new QName(NS_METADATA, "FC_KeepInContent");
    public static final QName M_FC_EPMCONTENTKIND = new QName(NS_METADATA, "FC_EpmContentKind");
    public static final QName M_FC_EPMKEEPINCONTENT = new QName(NS_METADATA, "FC_EpmKeepInContent");

    public final static QName[] SCHEMAS = new QName[] { EDM2006_SCHEMA, EDM2007_SCHEMA, EDM2008_SCHEMA, EDM2009_SCHEMA,
        EDM2009_2_SCHEMA };
    public final static QName[] KEYS = new QName[] { EDM2006_KEY, EDM2007_KEY, EDM2008_KEY, EDM2009_KEY,
        EDM2009_2_KEY};
    public final static QName[] ENTITY_TYPES = new QName[] { EDM2006_ENTITYTYPE, EDM2007_ENTITYTYPE,
        EDM2008_ENTITYTYPE, EDM2009_ENTITYTYPE, EDM2009_2_ENTITYTYPE };
    public final static QName[] PROPERTIES = new QName[] { EDM2006_PROPERTY, EDM2007_PROPERTY, EDM2008_PROPERTY,
        EDM2009_PROPERTY, EDM2009_2_PROPERTY };
    public final static QName[] NAV_PROPS = new QName[] { EDM2006_NAVIGATIONPROPERTY, EDM2007_NAVIGATIONPROPERTY,
        EDM2008_NAVIGATIONPROPERTY, EDM2009_NAVIGATIONPROPERTY, EDM2009_2_NAVIGATIONPROPERTY };
    public final static QName[] PROP_REFS = new QName[] { EDM2006_PROPERTYREF, EDM2007_PROPERTYREF,
        EDM2008_PROPERTYREF, EDM2009_PROPERTYREF, EDM2009_2_PROPERTYREF };
    public final static QName[] ASSOCIATIONS = new QName[] { EDM2006_ASSOCIATION, EDM2007_ASSOCIATION,
        EDM2008_ASSOCIATION, EDM2009_ASSOCIATION, EDM2009_2_ASSOCIATION };
    public final static QName[] ENDS = new QName[] { EDM2006_END, EDM2007_END,
        EDM2008_END, EDM2009_END, EDM2009_2_END};
    public final static QName[] COMPLEX_TYPES = new QName[] { EDM2006_COMPLEXTYPE, EDM2007_COMPLEXTYPE,
        EDM2008_COMPLEXTYPE, EDM2009_COMPLEXTYPE, EDM2009_2_COMPLEXTYPE };
    public final static QName[] ENTITY_CONTAINERS = new QName[] { EDM2006_ENTITYCONTAINER, EDM2007_ENTITYCONTAINER,
        EDM2008_ENTITYCONTAINER, EDM2009_ENTITYCONTAINER, EDM2009_2_ENTITYCONTAINER };
    public final static QName[] ENTITY_SETS = new QName[] { EDM2006_ENTITYSET, EDM2007_ENTITYSET, EDM2008_ENTITYSET,
        EDM2009_ENTITYSET, EDM2009_2_ENTITYSET };
    public final static QName[] ASSOCIATION_SETS = new QName[] { EDM2006_ASSOCIATIONSET, EDM2007_ASSOCIATIONSET,
        EDM2008_ASSOCIATIONSET, EDM2009_ASSOCIATIONSET, EDM2009_2_ASSOCIATIONSET };


    // Example 1: null
    public final static String NULL = "null";
    // Example 1: X'23AB' Example 2: binary'23ABFF'
    public final static String EDM_BINARY = "Edm.Binary";
    // Example 1: true Example 2: false
    public final static String EDM_BOOLEAN = "Edm.Boolean";
    // Example 1: FF
    public final static String EDM_BYTE = "Edm.Byte";
    public final static String EDM_DATE_TIME = "Edm.DateTime";
    public final static String EDM_DECIMAL = "Edm.Decimal";
    public final static String EDM_DOUBLE = "Edm.Double";
    public final static String EDM_SINGLE = "Edm.Single";
    public final static String EDM_GUID = "Edm.Guid";
    public final static String EDM_INT_16 = "Edm.Int16";
    public final static String EDM_INT_32 = "Edm.Int32";
    public final static String EDM_INT_64 = "Edm.Int64";
    public final static String EDM_S_BYTE = "Edm.SByte";
    public final static String EDM_STRING = "Edm.String";
    public final static String EDM_TIME = "Edm.Time";
    public final static String EDM_DATE_TIME_OFFSET = "Edm.DateTimeOffset";

    public static final QName XML_BASE = new QName(NS_XML, "base");

    public static boolean isStartElement(XMLEvent event, QName... names) {
        return event.isStartElement() && Arrays.asList(names).contains(event.asStartElement().getName());
    }

    public static boolean isEndElement(XMLEvent event, QName... names) {
        return event.isEndElement() && Arrays.asList(names).contains(event.asEndElement().getName());
    }

    public static String printStartElement(StartElement el) {
        return new ToStringBuilder(el).append("Name", el.getName().toString())
                        .append("NamespaceContext", el.getNamespaceContext()).append("SchemaType", el.getSchemaType())
                        .toString();

    }

}
