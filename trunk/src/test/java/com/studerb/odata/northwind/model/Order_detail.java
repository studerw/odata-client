package com.studerb.odata.northwind.model;

public class Order_detail {

    private Float discount;
    private Integer orderID;
    private Integer productID;
    private Short quantity;
    private Double unitPrice;
    private Order order;
    private Product product;

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order_detail [discount=");
        builder.append(discount);
        builder.append(", order=");
        builder.append(order);
        builder.append(", orderID=");
        builder.append(orderID);
        builder.append(", product=");
        builder.append(product);
        builder.append(", productID=");
        builder.append(productID);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", unitPrice=");
        builder.append(unitPrice);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
        result = prime * result + ((productID == null) ? 0 : productID.hashCode());
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
        Order_detail other = (Order_detail) obj;
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
        return true;
    }

}
