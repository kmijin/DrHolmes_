package com.pillgood.drholmes.api.pill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemClass {
    @SerializedName("ITEM_SEQ")
    @Expose
    private String itemSeq;
    @SerializedName("ITEM_NAME")
    @Expose
    private String itemName;
    @SerializedName("ENTP_SEQ")
    @Expose
    private String entpSeq;
    @SerializedName("ENTP_NAME")
    @Expose
    private String entpName;
    @SerializedName("CHART")
    @Expose
    private String chart;
    @SerializedName("ITEM_IMAGE")
    @Expose
    private String itemImage;
    @SerializedName("PRINT_FRONT")
    @Expose
    private String printFront;
    @SerializedName("PRINT_BACK")
    @Expose
    private String printBack;
    @SerializedName("DRUG_SHAPE")
    @Expose
    private String drugShape;
    @SerializedName("COLOR_CLASS1")
    @Expose
    private String colorClass1;
    @SerializedName("COLOR_CLASS2")
    @Expose
    private String colorClass2;
    @SerializedName("LINE_FRONT")
    @Expose
    private String lineFront;
    @SerializedName("LINE_BACK")
    @Expose
    private String lineBack;
    @SerializedName("LENG_LONG")
    @Expose
    private String lengLong;
    @SerializedName("LENG_SHORT")
    @Expose
    private String lengShort;
    @SerializedName("THICK")
    @Expose
    private String thick;
    @SerializedName("IMG_REGIST_TS")
    @Expose
    private String imgRegistTs;
    @SerializedName("CLASS_NO")
    @Expose
    private String classNo;
    @SerializedName("CLASS_NAME")
    @Expose
    private String className;
    @SerializedName("ETC_OTC_NAME")
    @Expose
    private String etcOtcName;
    @SerializedName("ITEM_PERMIT_DATE")
    @Expose
    private String itemPermitDate;
    @SerializedName("FORM_CODE_NAME")
    @Expose
    private String formCodeName;
    @SerializedName("MARK_CODE_FRONT_ANAL")
    @Expose
    private String markCodeFrontAnal;
    @SerializedName("MARK_CODE_BACK_ANAL")
    @Expose
    private String markCodeBackAnal;
    @SerializedName("MARK_CODE_FRONT_IMG")
    @Expose
    private String markCodeFrontImg;
    @SerializedName("MARK_CODE_BACK_IMG")
    @Expose
    private String getMarkCodeBackImg;
    @SerializedName("ITEM_ENG_NAME")
    @Expose
    private String itemEngName;
    @SerializedName("CHANGE_DATE")
    @Expose
    private String changeDate;
    @SerializedName("MARK_CODE_FRONT")
    @Expose
    private String markCodeFront;
    @SerializedName("MARK_CODE_BACK")
    @Expose
    private String markCodeBack;
    @SerializedName("EDI_CODE")
    @Expose
    private String ediCode;

    public String getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(String itemSeq) {
        this.itemSeq = itemSeq;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getEntpSeq() {
        return entpSeq;
    }

    public void setEntpSeq(String entpSeq) {
        this.entpSeq = entpSeq;
    }

    public String getEntpName() {
        return entpName;
    }

    public void setEntpName(String entpName) {
        this.entpName = entpName;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getPrintFront() {
        return printFront;
    }

    public void setPrintFront(String printFront) {
        this.printFront = printFront;
    }

    public String getPrintBack() {
        return printBack;
    }

    public void setPrintBack(String printBack) {
        this.printBack = printBack;
    }

    public String getDrugShape() {
        return drugShape;
    }

    public void setDrugShape(String drugShape) {
        this.drugShape = drugShape;
    }

    public String getColorClass1() {
        return colorClass1;
    }

    public void setColorClass1(String colorClass1) {
        this.colorClass1 = colorClass1;
    }

    public String getColorClass2() {
        return colorClass2;
    }

    public void setColorClass2(String colorClass2) {
        this.colorClass2 = colorClass2;
    }

    public String getLineFront() {
        return lineFront;
    }

    public void setLineFront(String lineFront) {
        this.lineFront = lineFront;
    }

    public String getLineBack() {
        return lineBack;
    }

    public void setLineBack(String lineBack) {
        this.lineBack = lineBack;
    }

    public String getLengLong() {
        return lengLong;
    }

    public void setLengLong(String lengLong) {
        this.lengLong = lengLong;
    }

    public String getLengShort() {
        return lengShort;
    }

    public void setLengShort(String lengShort) {
        this.lengShort = lengShort;
    }

    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick;
    }

    public String getImgRegistTs() {
        return imgRegistTs;
    }

    public void setImgRegistTs(String imgRegistTs) {
        this.imgRegistTs = imgRegistTs;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEtcOtcName() {
        return etcOtcName;
    }

    public void setEtcOtcName(String etcOtcName) {
        this.etcOtcName = etcOtcName;
    }

    public String getItemPermitDate() {
        return itemPermitDate;
    }

    public void setItemPermitDate(String itemPermitDate) {
        this.itemPermitDate = itemPermitDate;
    }

    public String getFormCodeName() {
        return formCodeName;
    }

    public void setFormCodeName(String formCodeName) {
        this.formCodeName = formCodeName;
    }

    public String getMarkCodeFrontAnal() {
        return markCodeFrontAnal;
    }

    public void setMarkCodeFrontAnal(String markCodeFrontAnal) {
        this.markCodeFrontAnal = markCodeFrontAnal;
    }

    public String getMarkCodeBackAnal() {
        return markCodeBackAnal;
    }

    public void setMarkCodeBackAnal(String markCodeBackAnal) {
        this.markCodeBackAnal = markCodeBackAnal;
    }

    public String getMarkCodeFrontImg() {
        return markCodeFrontImg;
    }

    public void setMarkCodeFrontImg(String markCodeFrontImg) {
        this.markCodeFrontImg = markCodeFrontImg;
    }

    public String getGetMarkCodeBackImg() {
        return getMarkCodeBackImg;
    }

    public void setGetMarkCodeBackImg(String getMarkCodeBackImg) {
        this.getMarkCodeBackImg = getMarkCodeBackImg;
    }

    public String getItemEngName() {
        return itemEngName;
    }

    public void setItemEngName(String itemEngName) {
        this.itemEngName = itemEngName;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getMarkCodeFront() {
        return markCodeFront;
    }

    public void setMarkCodeFront(String markCodeFront) {
        this.markCodeFront = markCodeFront;
    }

    public String getMarkCodeBack() {
        return markCodeBack;
    }

    public void setMarkCodeBack(String markCodeBack) {
        this.markCodeBack = markCodeBack;
    }

    public String getEdiCode() {
        return ediCode;
    }

    public void setEdiCode(String ediCode) {
        this.ediCode = ediCode;
    }
}
