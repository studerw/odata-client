package com.studerb.odata.northwind.model;

import java.util.Arrays;
import java.util.List;

public class Category {

    private Integer categoryID;
    private String categoryName;
    private String description;
    private Byte[] picture;
    private List<Product> products;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Category [categoryID=");
        builder.append(categoryID);
        builder.append(", categoryName=");
        builder.append(categoryName);
        builder.append(", description=");
        builder.append(description);
        builder.append(", picture=");
        builder.append(Arrays.toString(picture));
        builder.append(", products=");
        builder.append(products);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryID == null) ? 0 : categoryID.hashCode());
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
        Category other = (Category) obj;
        if (categoryID == null) {
            if (other.categoryID != null) {
                return false;
            }
        }
        else if (!categoryID.equals(other.categoryID)) {
            return false;
        }
        return true;
    }

}
