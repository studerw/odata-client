package com.studerb.odata.northwind.model;

import org.joda.time.LocalDateTime;

public class Sales_totals_by_amount {

    private String companyName;
    private Integer orderID;
    private Double saleAmount;
    private LocalDateTime shippedDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Sales_totals_by_amount [companyName=");
        builder.append(companyName);
        builder.append(", orderID=");
        builder.append(orderID);
        builder.append(", saleAmount=");
        builder.append(saleAmount);
        builder.append(", shippedDate=");
        builder.append(shippedDate);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
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
        Sales_totals_by_amount other = (Sales_totals_by_amount) obj;
        if (companyName == null) {
            if (other.companyName != null) {
                return false;
            }
        }
        else if (!companyName.equals(other.companyName)) {
            return false;
        }
        if (orderID == null) {
            if (other.orderID != null) {
                return false;
            }
        }
        else if (!orderID.equals(other.orderID)) {
            return false;
        }
        return true;
    }

}
