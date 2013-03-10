package com.studerb.odata.northwind.model;

import java.util.List;

public class Supplier {

    private String address;
    private String city;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String country;
    private String fax;
    private String homePage;
    private String phone;
    private String postalCode;
    private String region;
    private Integer supplierID;
    private List<Product> products;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Supplier [address=");
        builder.append(address);
        builder.append(", city=");
        builder.append(city);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", contactName=");
        builder.append(contactName);
        builder.append(", contactTitle=");
        builder.append(contactTitle);
        builder.append(", country=");
        builder.append(country);
        builder.append(", fax=");
        builder.append(fax);
        builder.append(", homePage=");
        builder.append(homePage);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", postalCode=");
        builder.append(postalCode);
        builder.append(", products=");
        builder.append(products);
        builder.append(", region=");
        builder.append(region);
        builder.append(", supplierID=");
        builder.append(supplierID);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((supplierID == null) ? 0 : supplierID.hashCode());
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
        Supplier other = (Supplier) obj;
        if (supplierID == null) {
            if (other.supplierID != null) {
                return false;
            }
        }
        else if (!supplierID.equals(other.supplierID)) {
            return false;
        }
        return true;
    }

}
