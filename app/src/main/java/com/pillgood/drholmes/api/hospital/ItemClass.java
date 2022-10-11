package com.pillgood.drholmes.api.hospital;

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
    @PropertyElement(name = "cmdcGdrCnt")
    Integer cmdcGdrCnt;
    @PropertyElement(name = "cmdcIntnCnt")
    Integer cmdcIntnCnt;
    @PropertyElement(name = "cmdcResdntCnt")
    Integer cmdcResdntCnt;
    @PropertyElement(name = "cmdcSdrCnt")
    Integer cmdcSdrCnt;
    @PropertyElement(name = "detyGdrCnt")
    Integer detyGdrCnt;
    @PropertyElement(name = "detyIntnCnt")
    Integer detyIntnCnt;
    @PropertyElement(name = "detyResdntCnt")
    Integer detyResdntCnt;
    @PropertyElement(name = "detySdrCnt")
    Integer detySdrCnt;
    @PropertyElement(name = "drTotCnt")
    Integer drTotCnt;
    @PropertyElement(name = "estbDd")
    String estbDd;
    @PropertyElement(name = "hospUrl")
    String hospUrl;
    @PropertyElement(name = "mdeptGdrCnt")
    Integer mdeptGdrCnt;
    @PropertyElement(name = "mdeptIntnCnt")
    Integer mdeptIntnCnt;
    @PropertyElement(name = "mdeptResdntCnt")
    Integer mdeptResdntCnt;
    @PropertyElement(name = "mdeptSdrCnt")
    Integer mdeptSdrCnt;
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

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getClCd() {
        return clCd;
    }

    public void setClCd(String clCd) {
        this.clCd = clCd;
    }

    public String getClCdNm() {
        return clCdNm;
    }

    public void setClCdNm(String clCdNm) {
        this.clCdNm = clCdNm;
    }

    public Integer getCmdcGdrCnt() {
        return cmdcGdrCnt;
    }

    public void setCmdcGdrCnt(Integer cmdcGdrCnt) {
        this.cmdcGdrCnt = cmdcGdrCnt;
    }

    public Integer getCmdcIntnCnt() {
        return cmdcIntnCnt;
    }

    public void setCmdcIntnCnt(Integer cmdcIntnCnt) {
        this.cmdcIntnCnt = cmdcIntnCnt;
    }

    public Integer getCmdcResdntCnt() {
        return cmdcResdntCnt;
    }

    public void setCmdcResdntCnt(Integer cmdcResdntCnt) {
        this.cmdcResdntCnt = cmdcResdntCnt;
    }

    public Integer getCmdcSdrCnt() {
        return cmdcSdrCnt;
    }

    public void setCmdcSdrCnt(Integer cmdcSdrCnt) {
        this.cmdcSdrCnt = cmdcSdrCnt;
    }

    public Integer getDetyGdrCnt() {
        return detyGdrCnt;
    }

    public void setDetyGdrCnt(Integer detyGdrCnt) {
        this.detyGdrCnt = detyGdrCnt;
    }

    public Integer getDetyIntnCnt() {
        return detyIntnCnt;
    }

    public void setDetyIntnCnt(Integer detyIntnCnt) {
        this.detyIntnCnt = detyIntnCnt;
    }

    public Integer getDetyResdntCnt() {
        return detyResdntCnt;
    }

    public void setDetyResdntCnt(Integer detyResdntCnt) {
        this.detyResdntCnt = detyResdntCnt;
    }

    public Integer getDetySdrCnt() {
        return detySdrCnt;
    }

    public void setDetySdrCnt(Integer detySdrCnt) {
        this.detySdrCnt = detySdrCnt;
    }

    public Integer getDrTotCnt() {
        return drTotCnt;
    }

    public void setDrTotCnt(Integer drTotCnt) {
        this.drTotCnt = drTotCnt;
    }

    public String getEstbDd() {
        return estbDd;
    }

    public void setEstbDd(String estbDd) {
        this.estbDd = estbDd;
    }

    public String getHospUrl() {
        return hospUrl;
    }

    public void setHospUrl(String hospUrl) {
        this.hospUrl = hospUrl;
    }

    public Integer getMdeptGdrCnt() {
        return mdeptGdrCnt;
    }

    public void setMdeptGdrCnt(Integer mdeptGdrCnt) {
        this.mdeptGdrCnt = mdeptGdrCnt;
    }

    public Integer getMdeptIntnCnt() {
        return mdeptIntnCnt;
    }

    public void setMdeptIntnCnt(Integer mdeptIntnCnt) {
        this.mdeptIntnCnt = mdeptIntnCnt;
    }

    public Integer getMdeptResdntCnt() {
        return mdeptResdntCnt;
    }

    public void setMdeptResdntCnt(Integer mdeptResdntCnt) {
        this.mdeptResdntCnt = mdeptResdntCnt;
    }

    public Integer getMdeptSdrCnt() {
        return mdeptSdrCnt;
    }

    public void setMdeptSdrCnt(Integer mdeptSdrCnt) {
        this.mdeptSdrCnt = mdeptSdrCnt;
    }

    public String getPostNo() {
        return postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getSgguCd() {
        return sgguCd;
    }

    public void setSgguCd(String sgguCd) {
        this.sgguCd = sgguCd;
    }

    public String getSgguCdNm() {
        return sgguCdNm;
    }

    public void setSgguCdNm(String sgguCdNm) {
        this.sgguCdNm = sgguCdNm;
    }

    public String getSidoCd() {
        return sidoCd;
    }

    public void setSidoCd(String sidoCd) {
        this.sidoCd = sidoCd;
    }

    public String getSidoCdNm() {
        return sidoCdNm;
    }

    public void setSidoCdNm(String sidoCdNm) {
        this.sidoCdNm = sidoCdNm;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
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

    public String getYadmNm() {
        return yadmNm;
    }

    public void setYadmNm(String yadmNm) {
        this.yadmNm = yadmNm;
    }

    public String getYkiho() {
        return ykiho;
    }

    public void setYkiho(String ykiho) {
        this.ykiho = ykiho;
    }
}