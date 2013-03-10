package com.studerb.odata.northwind.model;

public class Category_sales_for_1997 {

    private String categoryName;
    private Double categorySales;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getCategorySales() {
        return categorySales;
    }

    public void setCategorySales(Double categorySales) {
        this.categorySales = categorySales;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
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
        Category_sales_for_1997 other = (Category_sales_for_1997) obj;
        if (categoryName == null) {
            if (other.categoryName != null) {
                return false;
            }
        }
        else if (!categoryName.equals(other.categoryName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Category_sales_for_1997 [categoryName=");
        builder.append(categoryName);
        builder.append(", categorySales=");
        builder.append(categorySales);
        builder.append("]");
        return builder.toString();
    }

}
