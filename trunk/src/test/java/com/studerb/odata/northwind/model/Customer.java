package com.studerb.odata.northwind.model;

import java.util.List;

public class Customer {

    private String address;
    private String city;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String country;
    private String customerID;
    private String fax;
    private String phone;
    private String postalCode;
    private String region;
    private List<CustomerDemographic> customerDemographics;
    private List<Order> orders;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return this.contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<CustomerDemographic> getCustomerDemographics() {
        return this.customerDemographics;
    }

    public void setCustomerDemographics(List<CustomerDemographic> customerDemographics) {
        this.customerDemographics = customerDemographics;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.customerID == null) ? 0 : this.customerID.hashCode());
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
        Customer other = (Customer) obj;
        if (this.customerID == null) {
            if (other.customerID != null) {
                return false;
            }
        }
        else if (!this.customerID.equals(other.customerID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer [address=").append(this.address).append(", city=").append(this.city).append(", companyName=").append(this.companyName).append(
                ", contactName=").append(this.contactName).append(", contactTitle=").append(this.contactTitle).append(", country=").append(this.country)
                .append(", customerDemographics=").append(this.customerDemographics).append(", customerID=").append(this.customerID).append(", fax=").append(
                        this.fax).append(", orders=").append(this.orders).append(", phone=").append(this.phone).append(", postalCode=").append(this.postalCode)
                .append(", region=").append(this.region).append("]");
        return builder.toString();
    }

}
