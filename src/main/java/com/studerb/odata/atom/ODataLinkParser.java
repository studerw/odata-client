package com.studerb.odata.atom;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Link;

/**
 * <p>
 * the OData model uses Atom links within an Atom entry to describe the logical
 * relationships between an objects embedded entities. For example, if a
 * 'Customer' has an associated list of Orders, this relationship will be
 * described using an Atom Link. When OData expansions are used, the embedded
 * relationships are essentially loaded on demand and able to be marshalled to
 * full Java Pojos and attached to the parent object's object graph. This is
 * similar to Hibernate or other ORM frameworks' use of on-demand versus
 * <em>lazy loading</em> of related entities.
 * </p>
 * 
 * @param <T>
 */
public interface ODataLinkParser<T> {
    public void parse(Class<T> parentType, T parent, Link link) throws Exception;

    public boolean isCollection();

    public Element getInlineElement();

    public Link getLink();

    public T getParent();

    public Class<T> getParentType();

    public Class<?> getTargetType();

    public Object getTarget();

    public Method getMethod();

    public Field getParentField();

    public boolean isList();

    public boolean isSet();

    public boolean isInline();

    public boolean isLinkToFeed();

    public boolean isLinkToEntry();

    public boolean isSuccess();
}
