package com.studerb.odata.northwind.model;

import org.joda.time.LocalDateTime;

public class Summary_of_sales_by_quarter {

    private Integer orderID;
    private LocalDateTime shippedDate;
    private Double subtotal;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
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
        builder.append("Summary_of_sales_by_quarter [orderID=");
        builder.append(orderID);
        builder.append(", shippedDate=");
        builder.append(shippedDate);
        builder.append(", subtotal=");
        builder.append(subtotal);
        builder.append("]");
        return builder.toString();
    }

}
