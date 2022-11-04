package com.pillgood.drholmes.info;

public class Pill {
    String icon;
    String name;
    String effect;
    String desc;

    public Pill(String icon, String name, String effect, String desc) {
        this.icon = icon;
        this.name = name;
        this.effect = effect;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}