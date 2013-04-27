package com.studerb.odata.edm;


/**
 * Mostly static variables representing the CSDL Entity namespace used by Microsoft's framework. Versions 1, 2, and 3
 * and included. There were two point releases released, though these schemas are not included and will not parse.
 *
 * @author William Studer
 * @see <a href="http://www.odata.org/documentation/IndexV2">OData Version 2</a>
 * @see <a href="http://www.odata.org/documentation">OData Version 3</a>
 *
 */
public class EdmUtil {
    //    public final static QName EDMX = new QName("http://schemas.microsoft.com/ado/2007/06/edmx", "Edmx");
    //    public final static QName DATA_SERVICES = new QName("http://schemas.microsoft.com/ado/2007/06/edmx", "DataServices");
    //
    //    public final static QName SCHEMA1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "Schema");
    //    public final static QName SCHEMA2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "Schema");
    //    public final static QName SCHEMA3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "Schema");
    //    public final static QName[] SCHEMAS = new QName[] { SCHEMA1, SCHEMA2, SCHEMA3 };
    //
    //    public final static QName ENTITY_TYPE1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "EntityType");
    //    public final static QName ENTITY_TYPE2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "EntityType");
    //    public final static QName ENTITY_TYPE3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "EntityType");
    //    public final static QName[] ENTITY_TYPES = new QName[] { ENTITY_TYPE1, ENTITY_TYPE2, ENTITY_TYPE3 };
    //
    //    public final static QName PROPERTY1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "Property");
    //    public final static QName PROPERTY2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "Property");
    //    public final static QName PROPERTY3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "Property");
    //    public final static QName[] PROPERTIES = new QName[] { PROPERTY1, PROPERTY2, PROPERTY3 };
    //
    //    public final static QName KEY1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "Key");
    //    public final static QName KEY2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "Key");
    //    public final static QName KEY3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "Key");
    //    public final static QName[] KEYS = new QName[] { KEY1, KEY2, KEY3 };
    //
    //    public final static QName NAV_PROP1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "NavigationProperty");
    //    public final static QName NAV_PROP2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "NavigationProperty");
    //    public final static QName NAV_PROP3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "NavigationProperty");
    //    public final static QName[] NAV_PROPS = new QName[] { NAV_PROP1, NAV_PROP2, NAV_PROP3 };
    //
    //    public final static QName PROP_REF1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "PropertyRef");
    //    public final static QName PROP_REF2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "PropertyRef");
    //    public final static QName PROP_REF3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "PropertyRef");
    //    public final static QName[] PROP_REFS = new QName[] { PROP_REF1, PROP_REF2, PROP_REF3 };
    //
    //    public final static QName ASSOCIATION1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "Association");
    //    public final static QName ASSOCIATION2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "Association");
    //    public final static QName ASSOCIATION3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "Association");
    //    public final static QName[] ASSOCIATIONS = new QName[] {ASSOCIATION1, ASSOCIATION2, ASSOCIATION3};
    //
    //    public final static QName END1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "End");
    //    public final static QName END2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "End");
    //    public final static QName END3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "End");
    //    public final static QName[] ENDS = new QName[] { END1, END2, END3 };
    //
    //    public final static QName COMPLEX_TYPE1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "ComplexType");
    //    public final static QName COMPLEX_TYPE2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "ComplexType");
    //    public final static QName COMPLEX_TYPE3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "ComplexType");
    //    public final static QName[] COMPLEX_TYPES = new QName[] { COMPLEX_TYPE1, COMPLEX_TYPE2, COMPLEX_TYPE3 };
    //
    //    public final static QName ENTITY_CONTAINER1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "EntityContainer");
    //    public final static QName ENTITY_CONTAINER2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "EntityContainer");
    //    public final static QName ENTITY_CONTAINER3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "EntityContainer");
    //    public final static QName[] ENTITY_CONTAINERS = new QName[] { ENTITY_CONTAINER1, ENTITY_CONTAINER2, ENTITY_CONTAINER3 };
    //
    //    public final static QName ENTITY_SET1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "EntitySet");
    //    public final static QName ENTITY_SET2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "EntitySet");
    //    public final static QName ENTITY_SET3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "EntitySet");
    //    public final static QName[] ENTITY_SETS = new QName[] { ENTITY_SET1, ENTITY_SET2, ENTITY_SET3 };
    //
    //    public final static QName ASSOCIATION_SET1 = new QName("http://schemas.microsoft.com/ado/2006/04/edm", "AssociationSet");
    //    public final static QName ASSOCIATION_SET2 = new QName("http://schemas.microsoft.com/ado/2008/09/edm", "AssociationSet");
    //    public final static QName ASSOCIATION_SET3 = new QName("http://schemas.microsoft.com/ado/2009/11/edm", "AssociationSet");
    //    public final static QName[] ASSOCIATION_SETS = new QName[] { ASSOCIATION_SET1, ASSOCIATION_SET2, ASSOCIATION_SET3 };
    //
    //
    //    public static boolean isStartElement(XMLEvent event, QName... names) {
    //        return event.isStartElement() && Arrays.asList(names).contains(event.asStartElement().getName());
    //    }
    //
    //    public static boolean isEndElement(XMLEvent event, QName... names) {
    //        return event.isEndElement() && Arrays.asList(names).contains(event.asEndElement().getName());
    //    }
    //
    //    public static String printStartElement(StartElement el) {
    //        return new ToStringBuilder(el).append("Name", el.getName().toString()).append("NamespaceContext", el.getNamespaceContext()).append("SchemaType", el.getSchemaType())
    //                        .toString();
    //
    //    }
    //
}
