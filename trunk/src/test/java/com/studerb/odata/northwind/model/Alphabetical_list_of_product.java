package com.studerb.odata.northwind.model;

public class Alphabetical_list_of_product {

    private Integer categoryID;
    private String categoryName;
    private Boolean discontinued;
    private Integer productID;
    private String productName;
    private String quantityPerUnit;
    private Short reorderLevel;
    private Integer supplierID;
    private Double unitPrice;
    private Short unitsInStock;
    private Short unitsOnOrder;

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

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
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

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Short getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Short reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Short getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Short unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Short getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Short unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Alphabetical_list_of_product [categoryID=");
        builder.append(categoryID);
        builder.append(", categoryName=");
        builder.append(categoryName);
        builder.append(", discontinued=");
        builder.append(discontinued);
        builder.append(", productID=");
        builder.append(productID);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", quantityPerUnit=");
        builder.append(quantityPerUnit);
        builder.append(", reorderLevel=");
        builder.append(reorderLevel);
        builder.append(", supplierID=");
        builder.append(supplierID);
        builder.append(", unitPrice=");
        builder.append(unitPrice);
        builder.append(", unitsInStock=");
        builder.append(unitsInStock);
        builder.append(", unitsOnOrder=");
        builder.append(unitsOnOrder);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + ((productID == null) ? 0 : productID.hashCode());
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
        Alphabetical_list_of_product other = (Alphabetical_list_of_product) obj;
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
        return true;
    }
}
