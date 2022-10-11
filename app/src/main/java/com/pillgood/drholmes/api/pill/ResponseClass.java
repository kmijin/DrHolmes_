package com.pillgood.drholmes.api.pill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseClass {
    @SerializedName("header")
    @Expose
    private HeaderClass header;
    @SerializedName("body")
    @Expose
    private BodyClass body;

    public HeaderClass getHeader() {
        return header;
    }

    public void setHeader(HeaderClass header) {
        this.header = header;
    }

    public BodyClass getBody() {
        return body;
    }

    public void setBody(BodyClass body) {
        this.body = body;
    }
}
