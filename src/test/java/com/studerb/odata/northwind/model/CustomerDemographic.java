package com.studerb.odata.northwind.model;

import java.util.List;

public class CustomerDemographic {

    private String customerDesc;
    private String customerTypeID;
    private List<Customer> customers;

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    public String getCustomerTypeID() {
        return customerTypeID;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CustomerDemographic [customerDesc=");
        builder.append(customerDesc);
        builder.append(", customerTypeID=");
        builder.append(customerTypeID);
        builder.append(", customers=");
        builder.append(customers);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerTypeID == null) ? 0 : customerTypeID.hashCode());
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
        CustomerDemographic other = (CustomerDemographic) obj;
        if (customerTypeID == null) {
            if (other.customerTypeID != null) {
                return false;
            }
        }
        else if (!customerTypeID.equals(other.customerTypeID)) {
            return false;
        }
        return true;
    }

}
