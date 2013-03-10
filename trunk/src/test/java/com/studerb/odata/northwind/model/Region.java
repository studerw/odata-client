package com.studerb.odata.northwind.model;

import java.util.List;

public class Region {

    private String regionDescription;
    private Integer regionId;
    private List<Territory> territories;

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
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
        Region other = (Region) obj;
        if (regionId == null) {
            if (other.regionId != null) {
                return false;
            }
        }
        else if (!regionId.equals(other.regionId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Region [regionDescription=");
        builder.append(regionDescription);
        builder.append(", regionId=");
        builder.append(regionId);
        builder.append(", territories=");
        builder.append(territories);
        builder.append("]");
        return builder.toString();
    }

}
