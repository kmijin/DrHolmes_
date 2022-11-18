package com.pillgood.drholmes.home;

public class PillModel {
    private String pillName;
    private String pillHour;
    private String pillMinute;
    private String pillCount;

    public PillModel(){}

    public PillModel(String pillName, String pillHour, String pillMinute, String pillCount) {
        this.pillName = pillName;
        this.pillHour = pillHour;
        this.pillMinute = pillMinute;
        this.pillCount = pillCount;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getPillHour() {
        return pillHour;
    }

    public void setPillHour(String pillHour) {
        this.pillHour = pillHour;
    }

    public String getPillMinute() {
        return pillMinute;
    }

    public void setPillMinute(String pillMinute) {
        this.pillMinute = pillMinute;
    }

    public String getPillCount() {
        return pillCount;
    }

    public void setPillCount(String pillCount) {
        this.pillCount = pillCount;
    }
}
