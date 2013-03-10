package com.studerb.odata.northwind.model;

public class Products_by_category {

    private String categoryName;
    private Boolean discontinued;
    private String productName;
    private String quantityPerUnit;
    private Short unitsInStock;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Products_by_category [categoryName=");
        builder.append(categoryName);
        builder.append(", discontinued=");
        builder.append(discontinued);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", quantityPerUnit=");
        builder.append(quantityPerUnit);
        builder.append(", unitsInStock=");
        builder.append(unitsInStock);
        builder.append("]");
        return builder.toString();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Short getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Short unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
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
        Products_by_category other = (Products_by_category) obj;
        if (categoryName == null) {
            if (other.categoryName != null) {
                return false;
            }
        }
        else if (!categoryName.equals(other.categoryName)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        }
        else if (!discontinued.equals(other.discontinued)) {
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
