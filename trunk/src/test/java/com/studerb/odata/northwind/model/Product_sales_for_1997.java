package com.studerb.odata.northwind.model;

public class Product_sales_for_1997 {

    private String categoryName;
    private String productName;
    private Double productSales;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductSales() {
        return productSales;
    }

    public void setProductSales(Double productSales) {
        this.productSales = productSales;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
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
        Product_sales_for_1997 other = (Product_sales_for_1997) obj;
        if (categoryName == null) {
            if (other.categoryName != null) {
                return false;
            }
        }
        else if (!categoryName.equals(other.categoryName)) {
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
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product_sales_for_1997 [categoryName=");
        builder.append(categoryName);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", productSales=");
        builder.append(productSales);
        builder.append("]");
        return builder.toString();
    }

}
