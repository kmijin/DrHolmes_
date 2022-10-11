package com.pillgood.drholmes.map.hospital;

public class Hospital {
    String name;
    String department;
    String address;
    String tel;
    Double XPos;
    Double YPos;

    public Hospital(String name, String department, String address, String tel, Double XPos, Double YPos) {
        this.name = name;
        this.department = department;
        this.address = address;
        this.tel = tel;
        this.XPos = XPos;
        this.YPos = YPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Double getXPos() {
        return XPos;
    }

    public void setXPos(Double XPos) {
        this.XPos = XPos;
    }

    public Double getYPos() {
        return YPos;
    }

    public void setYPos(Double YPos) {
        this.YPos = YPos;
    }
}
