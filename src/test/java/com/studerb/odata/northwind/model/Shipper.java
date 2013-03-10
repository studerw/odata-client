package com.studerb.odata.northwind.model;

import java.util.List;

public class Shipper {

    private String companyName;
    private String phone;
    private Integer shipperID;
    private List<Order> orders;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getShipperID() {
        return shipperID;
    }

    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shipper [companyName=");
        builder.append(companyName);
        builder.append(", orders=");
        builder.append(orders);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", shipperID=");
        builder.append(shipperID);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipperID == null) ? 0 : shipperID.hashCode());
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
        Shipper other = (Shipper) obj;
        if (shipperID == null) {
            if (other.shipperID != null) {
                return false;
            }
        }
        else if (!shipperID.equals(other.shipperID)) {
            return false;
        }
        return true;
    }

}
