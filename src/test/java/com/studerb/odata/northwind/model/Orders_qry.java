package com.studerb.odata.northwind.model;

import org.joda.time.LocalDateTime;

public class Orders_qry {

    private String address;
    private String city;
    private String companyName;
    private String country;
    private String customerID;
    private Integer employeeID;
    private Double freight;
    private LocalDateTime orderDate;
    private Integer orderID;
    private String postalCode;
    private String region;
    private LocalDateTime requiredDate;
    private String shipAddress;
    private String shipCity;
    private String shipCountry;
    private String shipName;
    private LocalDateTime shippedDate;
    private String shipPostalCode;
    private String shipRegion;
    private Integer shipVia;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Orders_qry [address=");
        builder.append(address);
        builder.append(", city=");
        builder.append(city);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", country=");
        builder.append(country);
        builder.append(", customerID=");
        builder.append(customerID);
        builder.append(", employeeID=");
        builder.append(employeeID);
        builder.append(", freight=");
        builder.append(freight);
        builder.append(", orderDate=");
        builder.append(orderDate);
        builder.append(", orderID=");
        builder.append(orderID);
        builder.append(", postalCode=");
        builder.append(postalCode);
        builder.append(", region=");
        builder.append(region);
        builder.append(", requiredDate=");
        builder.append(requiredDate);
        builder.append(", shipAddress=");
        builder.append(shipAddress);
        builder.append(", shipCity=");
        builder.append(shipCity);
        builder.append(", shipCountry=");
        builder.append(shipCountry);
        builder.append(", shipName=");
        builder.append(shipName);
        builder.append(", shipPostalCode=");
        builder.append(shipPostalCode);
        builder.append(", shipRegion=");
        builder.append(shipRegion);
        builder.append(", shipVia=");
        builder.append(shipVia);
        builder.append(", shippedDate=");
        builder.append(shippedDate);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
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
        Orders_qry other = (Orders_qry) obj;
        if (companyName == null) {
            if (other.companyName != null) {
                return false;
            }
        }
        else if (!companyName.equals(other.companyName)) {
            return false;
        }
        if (orderID == null) {
            if (other.orderID != null) {
                return false;
            }
        }
        else if (!orderID.equals(other.orderID)) {
            return false;
        }
        return true;
    }

}
