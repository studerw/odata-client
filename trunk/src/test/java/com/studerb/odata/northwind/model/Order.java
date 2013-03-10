package com.studerb.odata.northwind.model;

import java.util.List;

import org.joda.time.LocalDateTime;

public class Order {

    private String customerID;
    private Integer employeeID;
    private Double freight;
    private LocalDateTime orderDate;
    private Integer orderID;
    private LocalDateTime requiredDate;
    private String shipAddress;
    private String shipCity;
    private String shipCountry;
    private String shipName;
    private LocalDateTime shippedDate;
    private String shipPostalCode;
    private String shipRegion;
    private Integer shipVia;
    private Customer customer;
    private Employee employee;
    private List<Order_detail> order_Details;
    private Shipper shipper;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Order_detail> getOrder_Details() {
        return order_Details;
    }

    public void setOrder_Details(List<Order_detail> orderDetails) {
        order_Details = orderDetails;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [customer=");
        builder.append(customer);
        builder.append(", customerID=");
        builder.append(customerID);
        builder.append(", employee=");
        builder.append(employee);
        builder.append(", employeeID=");
        builder.append(employeeID);
        builder.append(", freight=");
        builder.append(freight);
        builder.append(", orderDate=");
        builder.append(orderDate);
        builder.append(", orderID=");
        builder.append(orderID);
        builder.append(", order_Details=");
        builder.append(order_Details);
        builder.append(", requiredDate=");
        builder.append(requiredDate);
        builder.append(", shipAddress=");
        builder.append(shipAddress);
        builder.append(", shipCity=");
        builder.append(shipCity);
        builder.append(", shipCountry=");
        builder.append(shipCountry);
        builder.append(", shipName=");
        builder.append(shipName);
        builder.append(", shipPostalCode=");
        builder.append(shipPostalCode);
        builder.append(", shipRegion=");
        builder.append(shipRegion);
        builder.append(", shipVia=");
        builder.append(shipVia);
        builder.append(", shippedDate=");
        builder.append(shippedDate);
        builder.append(", shipper=");
        builder.append(shipper);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Order other = (Order) obj;
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
