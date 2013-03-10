package com.studerb.odata.northwind.model;

import org.joda.time.LocalDateTime;

public class Invoice {

    private String address;
    private String city;
    private String country;
    private String customerID;
    private String customerName;
    private Float discount;
    private Double extendedPrice;
    private Double freight;
    private LocalDateTime orderDate;
    private Integer orderID;
    private String postalCode;
    private Integer productID;
    private String productName;
    private Short quantity;
    private String region;
    private LocalDateTime requiredDate;
    private String salesperson;
    private String shipAddress;
    private String shipCity;
    private String shipCountry;
    private String shipName;
    private LocalDateTime shippedDate;
    private String shipperName;
    private String shipPostalCode;
    private String shipRegion;
    private Double unitPrice;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
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

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
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

    public String getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
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

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Invoice [address=");
        builder.append(address);
        builder.append(", city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", customerID=");
        builder.append(customerID);
        builder.append(", customerName=");
        builder.append(customerName);
        builder.append(", discount=");
        builder.append(discount);
        builder.append(", extendedPrice=");
        builder.append(extendedPrice);
        builder.append(", freight=");
        builder.append(freight);
        builder.append(", orderDate=");
        builder.append(orderDate);
        builder.append(", orderID=");
        builder.append(orderID);
        builder.append(", postalCode=");
        builder.append(postalCode);
        builder.append(", productID=");
        builder.append(productID);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", region=");
        builder.append(region);
        builder.append(", requiredDate=");
        builder.append(requiredDate);
        builder.append(", salesperson=");
        builder.append(salesperson);
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
        builder.append(", shippedDate=");
        builder.append(shippedDate);
        builder.append(", shipperName=");
        builder.append(shipperName);
        builder.append(", unitPrice=");
        builder.append(unitPrice);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
        result = prime * result + ((productID == null) ? 0 : productID.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((salesperson == null) ? 0 : salesperson.hashCode());
        result = prime * result + ((shipperName == null) ? 0 : shipperName.hashCode());
        result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
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
        Invoice other = (Invoice) obj;
        if (customerName == null) {
            if (other.customerName != null) {
                return false;
            }
        }
        else if (!customerName.equals(other.customerName)) {
            return false;
        }
        if (discount == null) {
            if (other.discount != null) {
                return false;
            }
        }
        else if (!discount.equals(other.discount)) {
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
        if (productID == null) {
            if (other.productID != null) {
                return false;
            }
        }
        else if (!productID.equals(other.productID)) {
            return false;
        }
        if (productName == null) {
            if (other.productName != null) {
                return false;
            }
        }
        else if (!productName.equals(other.productName)) {
            return false;
        }
        if (quantity == null) {
            if (other.quantity != null) {
                return false;
            }
        }
        else if (!quantity.equals(other.quantity)) {
            return false;
        }
        if (salesperson == null) {
            if (other.salesperson != null) {
                return false;
            }
        }
        else if (!salesperson.equals(other.salesperson)) {
            return false;
        }
        if (shipperName == null) {
            if (other.shipperName != null) {
                return false;
            }
        }
        else if (!shipperName.equals(other.shipperName)) {
            return false;
        }
        if (unitPrice == null) {
            if (other.unitPrice != null) {
                return false;
            }
        }
        else if (!unitPrice.equals(other.unitPrice)) {
            return false;
        }
        return true;
    }
}
