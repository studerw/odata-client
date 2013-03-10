package com.studerb.odata.atom;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;
import java.util.Set;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.abdera.util.MimeTypeHelper;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.studerb.odata.naming.PropertyNameStrategy;

/*
 * <p>
 * This Link will look for those expanded objects within the Atom Entry and
 * attemtp to create a new Pojo (or Collection) and attach them to passed parent
 * object.
 * </p>
 * @author Bill Studer
 *
 * @param <T>
 */
public abstract class AbstractAbderaLinkParser<T> implements ODataLinkParser<T> {
    final Logger log = LoggerFactory.getLogger(AbstractAbderaLinkParser.class);
    private Element inlineElement;
    Link link;
    T parent;
    Class<T> parentType;
    Class<?> targetType;
    Object target;
    Method method;
    Field parentField;
    boolean list = false;
    boolean set = false;
    boolean success = false;
    boolean inline = false;
    boolean linkToFeed;
    boolean linkToEntry;

    PropertyNameStrategy propNameStrategy;

    public abstract String getPropertyName();

    /**
     * @param parentType
     *            the Java type of the parent object
     * @param parent
     *            the actual instantiated parent object for which associated
     *            objects will be marshalled to Pojos from the expanded OData
     *            Atom entries
     * @param link
     *            the Apache Abdera Link object itself
     * @throws UnsupportedEncodingException
     */
    @Override
    public void parse(Class<T> parentType, T parent, Link link) throws UnsupportedEncodingException {
        this.parent = parent;
        this.parentType = parentType;
        this.link = link;
        // this checks if the feed link itself is labeled with a
        // 'type="application/atom+xml;type=feed"'
        checkFeedOrEntry();
        // see if the link is inlined which occurs when expanded
        checkInline();
        if (!this.isInline()) {
            this.log.trace("Ignoring non-inline link of title: " + this.link.getTitle());
            this.success = true;
            return;
        }
        try {
            String propName = getPropertyName();
            this.targetType = getType(propName);
            if (this.targetType == null) {
                this.log.warn("Target Type for prop: " + propName + " is null - not parsing");
            }
            if (this.targetType != null) {
                if (this.isLinkToFeed() && this.isCollection()) {
                    this.log.debug(String.format("Recursing for FEED of TYPE: List<%s>", this.targetType.getName()));
                    createListFromFeed(this.targetType);
                }
                else if (this.isLinkToEntry() && !this.isCollection()) {
                    this.log.info(String.format("Recursing for ENTRY of TYPE: %s", this.targetType.getName()));
                    createObjectFromEntry(this.targetType);
                }
                else {
                    String errorMessage = String.format("Mismath - isLinkToFeed = %b, isCollection = %b", this.isLinkToFeed(), this.isCollection());
                    throw new Exception(errorMessage);
                }
            }
            this.success = true;
        }
        catch (Exception e) {
            this.log.error("Error parsing inline link: " + link.getTitle() + " :  " + URLDecoder.decode(link.getHref().toASCIIString(), Charsets.UTF_8.name()));
            this.success = false;
            e.printStackTrace();
        }
    }

    private <U> void createListFromFeed(Class<U> type) throws Exception {
        List<U> children = null;
        Feed feed = getFeedFromInlineElement();
        if (feed == null) {
            children = Lists.newArrayList();
        }
        else {
            ODataFeedParser<U> feedParser = new ODataFeedParser<U>(this.propNameStrategy);
            children = feedParser.parse(type, feed);
        }
        this.log.debug(String.format("Created new List<%s>: %s", type.getName(), children));
        PropertyUtils.setSimpleProperty(this.parent, getPropertyName(), children);
        // this.method.invoke(this.parent, children);
    }

    private <U> void createObjectFromEntry(Class<U> type) throws Exception {
        U child = null;
        Entry entry = getEntryFromInlineElement();
        if (entry != null) {
            ODataEntryParser<U> entryParser = new ODataEntryParser<U>(this.propNameStrategy);
            child = entryParser.parse(type, entry);
        }
        this.log.debug(String.format("Created new %s: %s", type.getName(), child));
        PropertyUtils.setSimpleProperty(this.parent, getPropertyName(), child);
        // this.method.invoke(this.parent, child);
    }

    protected Class<?> getType(String propertyName) throws Exception {
        boolean canWrite = PropertyUtils.isWriteable(this.parent, propertyName);
        if (!canWrite) {
            this.log.warn("Cannot write to property: " + propertyName);
            return null;
        }
        Method writeMethod = PropertyUtils.getPropertyDescriptor(this.parent, propertyName).getWriteMethod();
        if (writeMethod == null) {
            this.log.warn("NO write method for property: " + propertyName);
            return null;
        }
        Class<?> theTarget = null;
        this.method = writeMethod;
        this.log.debug("Method: " + this.method.toGenericString());
        Class<?>[] params = this.method.getParameterTypes();
        theTarget = params[0];
        this.list = List.class.isAssignableFrom(theTarget);
        this.set = Set.class.isAssignableFrom(theTarget);
        this.log.trace(String.format("Is List / Set: (%b / %b)", this.list, this.set));
        if (this.isCollection()) {
            Type[] genericParams = this.method.getGenericParameterTypes();
            if (genericParams.length == 1) {
                Type t = genericParams[0];
                this.log.trace("targetType: " + t.toString());
                if (t instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) t;
                    this.log.trace(pt.toString());
                    theTarget = (Class) pt.getActualTypeArguments()[0];
                    this.log.trace("Actual targetType: " + theTarget);
                }
            }
        }
        return theTarget;
    }

    protected void checkInline() {
        if (!this.isLinkToEntry() && !this.isLinkToFeed()) {
            this.inline = false;
            return;
        }
        else {
            for (Element el : this.link.getElements()) {
                if (el.getQName().equals(Namespaces.M_INLINE)) {
                    this.inline = true;
                    this.inlineElement = el;
                }
            }
        }
    }

    private Feed getFeedFromInlineElement() {
        for (Element el : this.inlineElement.getElements()) {
            if (el.getQName().equals(Namespaces.ATOM_FEED)) {
                String feedText = el.toString();
                Document<Feed> doc = Abdera.getInstance().getParser().parse(IOUtils.toInputStream(feedText));
                return doc.getRoot();
            }
        }
        return null;
    }

    private Entry getEntryFromInlineElement() {
        for (Element el : this.inlineElement.getElements()) {
            if (el.getQName().equals(Namespaces.ATOM_ENTRY)) {
                String entryText = el.toString();
                Document<Entry> doc = Abdera.getInstance().getParser().parse(IOUtils.toInputStream(entryText));
                return doc.getRoot();
            }
        }
        return null;
    }

    private void checkFeedOrEntry() {
        String linkType = this.link.getAttributeValue("type");
        if (!StringUtils.isBlank(linkType)) {
            this.linkToEntry = MimeTypeHelper.isAtom(linkType) && linkType.contains("type=entry");
            this.linkToFeed = MimeTypeHelper.isAtom(linkType) && linkType.contains("type=feed");
        }
    }

    /**
     * @return whether the link refers to an embedded Collection of objects
     *         versus a single object. In other words, does this relationship
     *         describe a one-to-one or one-to-many type relationship.
     */
    public boolean isCollection() {
        return (this.list || this.set);
    }

    @Override
    public Element getInlineElement() {
        return this.inlineElement;
    }

    @Override
    public Link getLink() {
        return this.link;
    }

    @Override
    public T getParent() {
        return this.parent;
    }

    @Override
    public Class<T> getParentType() {
        return this.parentType;
    }

    @Override
    public Class<?> getTargetType() {
        return this.targetType;
    }

    @Override
    public Object getTarget() {
        return this.target;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Field getParentField() {
        return this.parentField;
    }

    @Override
    public boolean isList() {
        return this.list;
    }

    @Override
    public boolean isSet() {
        return this.set;
    }

    @Override
    public boolean isInline() {
        return this.inline;
    }

    @Override
    public boolean isLinkToFeed() {
        return this.linkToFeed;
    }

    @Override
    public boolean isLinkToEntry() {
        return this.linkToEntry;
    }

    @Override
    public boolean isSuccess() {
        return this.success;
    }
}
