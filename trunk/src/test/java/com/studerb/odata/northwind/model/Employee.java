package com.studerb.odata.northwind.model;

import java.util.List;

import org.joda.time.LocalDateTime;

public class Employee {

    private String address;
    private LocalDateTime birthDate;
    private String city;
    private String country;
    private Integer employeeID;
    private String extension;
    private String firstName;
    private LocalDateTime hireDate;
    private String homePhone;
    private String lastName;
    private String notes;
    private Byte[] photo;
    private String photoPath;
    private String postalCode;
    private String region;
    private Integer reportsTo;
    private String title;
    private String titleOfCourtesy;
    private Employee employee1;
    private List<Employee> employees1;
    private List<Order> orders;
    private List<Territory> territories;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDateTime getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getReportsTo() {
        return this.reportsTo;
    }

    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return this.titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public Employee getEmployee1() {
        return this.employee1;
    }

    public void setEmployee1(Employee employee1) {
        this.employee1 = employee1;
    }

    public List<Employee> getEmployees1() {
        return this.employees1;
    }

    public void setEmployees1(List<Employee> employees1) {
        this.employees1 = employees1;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Territory> getTerritories() {
        return this.territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee [address=");
        builder.append(this.address);
        builder.append(", birthDate=");
        builder.append(this.birthDate);
        builder.append(", city=");
        builder.append(this.city);
        builder.append(", country=");
        builder.append(this.country);
        builder.append(", employee1=");
        builder.append(this.employee1);
        builder.append(", employeeID=");
        builder.append(this.employeeID);
        builder.append(", employees1=");
        builder.append(this.employees1);
        builder.append(", extension=");
        builder.append(this.extension);
        builder.append(", firstName=");
        builder.append(this.firstName);
        builder.append(", hireDate=");
        builder.append(this.hireDate);
        builder.append(", homePhone=");
        builder.append(this.homePhone);
        builder.append(", lastName=");
        builder.append(this.lastName);
        builder.append(", notes=");
        builder.append(this.notes);
        builder.append(", orders=");
        builder.append(this.orders);
        builder.append(", photoPath=");
        builder.append(this.photoPath);
        builder.append(", postalCode=");
        builder.append(this.postalCode);
        builder.append(", region=");
        builder.append(this.region);
        builder.append(", reportsTo=");
        builder.append(this.reportsTo);
        builder.append(", territories=");
        builder.append(this.territories);
        builder.append(", title=");
        builder.append(this.title);
        builder.append(", titleOfCourtesy=");
        builder.append(this.titleOfCourtesy);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.employeeID == null) ? 0 : this.employeeID.hashCode());
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        if (this.employeeID == null) {
            if (other.employeeID != null) {
                return false;
            }
        }
        else if (!this.employeeID.equals(other.employeeID)) {
            return false;
        }
        return true;
    }

}
