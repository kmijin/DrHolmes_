package com.pillgood.drholmes.api.hospital;


import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "response")
public class ResponseClass {
    @Element(name = "header")
    HeaderClass header;
    @Element(name = "body")
    BodyClass body;

    public HeaderClass getHeader() {
        return header;
    }

    public BodyClass getBody() {
        return body;
    }

    public void setHeader(HeaderClass header) {
        this.header = header;
    }

    public void setBody(BodyClass body) {
        this.body = body;
    }
}
