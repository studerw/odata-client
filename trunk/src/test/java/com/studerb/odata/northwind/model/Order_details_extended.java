package com.studerb.odata.northwind.model;

public class Order_details_extended {

    private Float discount;
    private Double extendedPrice;
    private Integer orderID;
    private Integer productID;
    private String productName;
    private Short quantity;
    private Double unitPrice;

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

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order_details_extended [discount=");
        builder.append(discount);
        builder.append(", extendedPrice=");
        builder.append(extendedPrice);
        builder.append(", orderID=");
        builder.append(orderID);
        builder.append(", productID=");
        builder.append(productID);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", unitPrice=");
        builder.append(unitPrice);
        builder.append("]");
        return builder.toString();
    }

}
