package com.pillgood.drholmes.api.hospital;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "items")
public class ItemsClass {
    @Element(name="item")
    List<ItemClass> item;

    public List<ItemClass> getItem() {
        return item;
    }

    public void setItem(List<ItemClass> item) {
        this.item = item;
    }
}