package com.pillgood.drholmes.api.device;

import com.google.gson.annotations.SerializedName;

public class DeviceClass {

    public DeviceClass(String id, String state) {
        this.id = id;
        this.state = state;
    }

    @SerializedName("id") private String id;
    @SerializedName("state") private String state;

}
