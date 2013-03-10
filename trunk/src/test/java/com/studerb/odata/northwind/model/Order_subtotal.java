package com.studerb.odata.northwind.model;

public class Order_subtotal {

    private Integer orderID;
    private Double subtotal;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order_subtotal [orderID=");
        builder.append(orderID);
        builder.append(", subtotal=");
        builder.append(subtotal);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Order_subtotal other = (Order_subtotal) obj;
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
