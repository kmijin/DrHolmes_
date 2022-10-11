package com.pillgood.drholmes.info.disease;

public class Disease {
    String name;
    String filterName;
    String desc;


    public Disease(String name, String filterName, String desc) {
        this.name = name;
        this.filterName = filterName;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
