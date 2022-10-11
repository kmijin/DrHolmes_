package com.pillgood.drholmes.api.hospital;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "body")
public class BodyClass {
    @Element(name="items")
    ItemsClass items;
    @PropertyElement(name="numOfRows")
    String numOfRows;
    @PropertyElement(name = "pageNo")
    String pageNo;
    @PropertyElement(name = "totalCount")
    Integer totalCount;

    public ItemsClass getItems() {
        return items;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public String getPageNo() {
        return pageNo;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setItems(ItemsClass items) {
        this.items = items;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
