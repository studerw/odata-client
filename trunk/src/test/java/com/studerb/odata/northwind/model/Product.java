package com.studerb.odata.northwind.model;

import java.util.List;

public class Product {

    private Integer categoryID;
    private Boolean discontinued;
    private Integer productID;
    private String productName;
    private String quantityPerUnit;
    private Short reorderLevel;
    private Integer supplierID;
    private Double unitPrice;
    private Short unitsInStock;
    private Short unitsOnOrder;
    private Category category;
    private List<Order_detail> order_Details;
    private Supplier supplier;

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order_detail> getOrder_Details() {
        return order_Details;
    }

    public void setOrder_Details(List<Order_detail> orderDetails) {
        order_Details = orderDetails;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product [category=");
        builder.append(category);
        builder.append(", categoryID=");
        builder.append(categoryID);
        builder.append(", discontinued=");
        builder.append(discontinued);
        builder.append(", order_Details=");
        builder.append(order_Details);
        builder.append(", productID=");
        builder.append(productID);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", quantityPerUnit=");
        builder.append(quantityPerUnit);
        builder.append(", reorderLevel=");
        builder.append(reorderLevel);
        builder.append(", supplier=");
        builder.append(supplier);
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
        Product other = (Product) obj;
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
