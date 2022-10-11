package com.pillgood.drholmes.api.pharmacy;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "item")
public class ItemClass {
    @PropertyElement(name="addr")
    String addr;
    @PropertyElement(name="clCd")
    String clCd;
    @PropertyElement(name = "clCdNm")
    String clCdNm;
    @PropertyElement(name = "distance")
    String distance;
    @PropertyElement(name = "emdongNm")
    String emdongNm;
    @PropertyElement(name = "estbDd")
    String estbDd;
    @PropertyElement(name="postNo")
    String postNo;
    @PropertyElement(name="sgguCd")
    String sgguCd;
    @PropertyElement(name = "sgguCdNm")
    String sgguCdNm;
    @PropertyElement(name = "sidoCd")
    String sidoCd;
    @PropertyElement(name = "sidoCdNm")
    String sidoCdNm;
    @PropertyElement(name="telno")
    String telno;
    @PropertyElement(name="XPos")
    Double XPos;
    @PropertyElement(name = "YPos")
    Double YPos;
    @PropertyElement(name = "yadmNm")
    String yadmNm;
    @PropertyElement(name = "ykiho")
    String ykiho;

    public String getAddr() {
        return addr;
    }

    public String getClCd() {
        return clCd;
    }

    public String getClCdNm() {
        return clCdNm;
    }

    public String getdistance() {
        return distance;
    }

    public String getemdongNm() {
        return emdongNm;
    }

    public String getEstbDd() {
        return estbDd;
    }

    public String getPostNo() {
        return postNo;
    }

    public String getSgguCd() {
        return sgguCd;
    }

    public String getSgguCdNm() {
        return sgguCdNm;
    }

    public String getSidoCd() {
        return sidoCd;
    }

    public String getSidoCdNm() {
        return sidoCdNm;
    }

    public String getTelno() {
        return telno;
    }

    public Double getXPos() {
        return XPos;
    }

    public Double getYPos() {
        return YPos;
    }

    public String getYadmNm() {
        return yadmNm;
    }

    public String getYkiho() {
        return ykiho;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setClCd(String clCd) {
        this.clCd = clCd;
    }

    public void setClCdNm(String clCdNm) {
        this.clCdNm = clCdNm;
    }

    public void setdistance(String distance) {
        this.distance = distance;
    }

    public void setemdongNm(String emdongNm) {
        this.emdongNm = emdongNm;
    }

    public void setEstbDd(String estbDd) {
        this.estbDd = estbDd;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public void setSgguCd(String sgguCd) {
        this.sgguCd = sgguCd;
    }

    public void setSgguCdNm(String sgguCdNm) {
        this.sgguCdNm = sgguCdNm;
    }

    public void setSidoCd(String sidoCd) {
        this.sidoCd = sidoCd;
    }

    public void setSidoCdNm(String sidoCdNm) {
        this.sidoCdNm = sidoCdNm;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public void setXPos(Double XPos) {
        this.XPos = XPos;
    }

    public void setYPos(Double YPos) {
        this.YPos = YPos;
    }

    public void setYadmNm(String yadmNm) {
        this.yadmNm = yadmNm;
    }
}