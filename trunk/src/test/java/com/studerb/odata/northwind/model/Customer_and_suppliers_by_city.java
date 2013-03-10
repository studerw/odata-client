package com.studerb.odata.northwind.model;

public class Customer_and_suppliers_by_city {

    private String city;
    private String companyName;
    private String contactName;
    private String relationship;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer_and_suppliers_by_city [city=");
        builder.append(city);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", contactName=");
        builder.append(contactName);
        builder.append(", relationship=");
        builder.append(relationship);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Customer_and_suppliers_by_city other = (Customer_and_suppliers_by_city) obj;
        if (companyName == null) {
            if (other.companyName != null) {
                return false;
            }
        }
        else if (!companyName.equals(other.companyName)) {
            return false;
        }
        if (relationship == null) {
            if (other.relationship != null) {
                return false;
            }
        }
        else if (!relationship.equals(other.relationship)) {
            return false;
        }
        return true;
    }

}
