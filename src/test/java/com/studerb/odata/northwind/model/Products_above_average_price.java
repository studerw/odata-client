package com.studerb.odata.northwind.model;

public class Products_above_average_price {

    private String productName;
    private Double unitPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
        Products_above_average_price other = (Products_above_average_price) obj;
        if (productName == null) {
            if (other.productName != null) {
                return false;
            }
        }
        else if (!productName.equals(other.productName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Products_above_average_price [productName=");
        builder.append(productName);
        builder.append(", unitPrice=");
        builder.append(unitPrice);
        builder.append("]");
        return builder.toString();
    }

}
