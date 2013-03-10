package com.studerb.odata.atom;

import com.studerb.odata.naming.RestletPropertyNameStrategy;

public class RestletLinkParser<T> extends AbstractAbderaLinkParser<T> {

    public RestletLinkParser(RestletPropertyNameStrategy s) {
        this.propNameStrategy = s;
    }

    @Override
    public String getPropertyName() {
        String propTitle = getLink().getTitle();
        String restletName = normalize(propTitle);
        return restletName;
    }

    /**
     * Code used by Restlet needed for classes named using the Restlet OData
     * Generator class
     * 
     * @param name
     * @return the normalized name of the property using Restlet's normalize
     *         method
     */
    public static String normalize(String name) {
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

        return result;
    }

}
