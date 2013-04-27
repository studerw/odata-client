package com.studerb.odata.atom;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author William Studer
 * 
 */
public class RestletPropertyParser<T> extends AbstractPropertyParser<T> {
    final Logger log = LoggerFactory.getLogger(RestletPropertyParser.class);

    @Override
    public String getPropName(String name) {
        String restletName = normalize(name);
        this.log.trace(String.format("Restlet normalized name: %s -> %s", name, restletName));
        return restletName;
    }

    /**
     * Code used by Restlet needed for classes named using the Restlet OData Generator class
     * 
     * @param name
     * @return the normalized name of the property using Restlets normalize method
     */
    public String normalize(String name) {
        String result = null;
        if (name != null) {
            // Build the normalized name according to the java naming rules
            StringBuilder b = new StringBuilder();
            boolean upperCase = false;
            char oldChar = 0;
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (Character.isDigit(c)) {
                    b.append(c);
                    oldChar = c;
                }
                else if (c >= 'a' && c <= 'z') {
                    if (upperCase) {
                        b.append(Character.toUpperCase(c));
                        upperCase = false;
                    }
                    else {
                        b.append(c);
                    }
                    oldChar = c;
                }
                else if (c >= 'A' && c <= 'Z') {
                    if (upperCase) {
                        b.append(c);
                        upperCase = false;
                    }
                    else if (oldChar != 0 && Character.isLowerCase(oldChar)) {
                        b.append(c);
                    }
                    else {
                        b.append(Character.toLowerCase(c));
                    }
                    oldChar = c;
                }
                else if (c == '.') {
                    upperCase = true;
                }
                else if (Character.isJavaIdentifierPart(c)) {
                    b.append(c);
                    oldChar = c;
                }
                else {
                    upperCase = true;
                }
            }
            result = b.toString();
        }
        if (!PropertyUtils.isWriteable(this.target, result)) {
            this.log.info("Prop: " + result + " is not writable. Checking case where first letter lower followed by upper");
            result = checkLowerUpper(result);
        }
        return result;
    }

    /**
     * Seems to be a bug when going from setters / getters back to props when the original property is something like
     * 'vTasks'. For example, to a getter, we'd get getVTasks(). When going the other way (which BeanUtils
     * PropertyBeanUtils does to figure out the correct method per prop), you get the prop VTask.
     * 
     * getVTasks -> VTasks getvTasks->vTasks
     * 
     * 
     * @param property
     *            String that may need to corrected if it contains two upper case letters followed by standard lower
     *            case.
     * @return String with corrected case
     */
    private String checkLowerUpper(String property) {
        String replaced = property;
        char first = replaced.charAt(0);
        char sec = replaced.charAt(1);
        if (CharUtils.isAsciiAlphaLower(first) && CharUtils.isAsciiAlphaUpper(sec)) {
            String firstTwo = Character.toString(Character.toUpperCase(first)) + Character.toString(sec);
            replaced = firstTwo + replaced.substring(2);
            this.log.info(String.format("Changing prop name due to abnormal java bean rule: %s -> %s", property, replaced));
        }
        return replaced;
    }
}
