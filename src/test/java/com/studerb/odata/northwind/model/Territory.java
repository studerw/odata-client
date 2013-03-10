package com.studerb.odata.northwind.model;

import java.util.List;

public class Territory {

    private Integer regionID;
    private String territoryDescription;
    private String territoryID;
    private List<Employee> employees;
    private Region region;

    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public String getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(String territoryID) {
        this.territoryID = territoryID;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Territory [employees=");
        builder.append(employees);
        builder.append(", region=");
        builder.append(region);
        builder.append(", regionID=");
        builder.append(regionID);
        builder.append(", territoryDescription=");
        builder.append(territoryDescription);
        builder.append(", territoryID=");
        builder.append(territoryID);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((territoryID == null) ? 0 : territoryID.hashCode());
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
        Territory other = (Territory) obj;
        if (territoryID == null) {
            if (other.territoryID != null) {
                return false;
            }
        }
        else if (!territoryID.equals(other.territoryID)) {
            return false;
        }
        return true;
    }

}
