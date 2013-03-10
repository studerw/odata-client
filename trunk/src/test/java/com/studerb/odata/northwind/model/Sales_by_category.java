package com.studerb.odata.northwind.model;

public class Sales_by_category {

    private Integer categoryID;
    private String categoryName;
    private String productName;
    private Double productSales;

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Sales_by_category [categoryID=");
        builder.append(categoryID);
        builder.append(", categoryName=");
        builder.append(categoryName);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", productSales=");
        builder.append(productSales);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryID == null) ? 0 : categoryID.hashCode());
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
        Sales_by_category other = (Sales_by_category) obj;
        if (categoryID == null) {
            if (other.categoryID != null) {
                return false;
            }
        }
        else if (!categoryID.equals(other.categoryID)) {
            return false;
        }
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

}
