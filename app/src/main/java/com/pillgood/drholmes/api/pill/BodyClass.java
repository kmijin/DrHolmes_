package com.pillgood.drholmes.api.pill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BodyClass {

    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("numOfRows")
    @Expose
    private Integer numOfRows;
    @SerializedName("items")
    @Expose
    private List<ItemClass> items;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    public List<ItemClass> getItems() {
        return items;
    }

    public void setItems(List<ItemClass> items) {
        this.items = items;
    }
}
