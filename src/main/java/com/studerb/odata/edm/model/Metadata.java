package com.studerb.odata.edm.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class Metadata {
    final Logger logger = LoggerFactory.getLogger(Metadata.class);

    List<DataService> dataServices = Lists.newArrayList();

    public List<DataService> getDataServices() {
        return this.dataServices;
    }

    public void setDataServices(List<DataService> dataServices) {
        this.dataServices = dataServices;
    }

}
