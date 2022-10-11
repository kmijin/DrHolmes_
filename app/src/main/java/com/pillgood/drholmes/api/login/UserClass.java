package com.pillgood.drholmes.api.login;

import com.google.gson.annotations.SerializedName;

public class UserClass {

    public UserClass(String id, String pw) {
        this.userId = id;
        this.userPassword = pw;
        this.userName = "id";
        this.userBirth = "pw";
        this.userGender = "id";
        this.emailAgree = "pw";
        this.joinDate = "id";
    }

    @SerializedName("userId") private String userId;
    @SerializedName("userPassword") private String userPassword;
    @SerializedName("userName") private String userName;
    @SerializedName("userBirth") private String userBirth;
    @SerializedName("userGender") private String userGender;
    @SerializedName("emailAgree") private String emailAgree;
    @SerializedName("joinDate") private String joinDate;

}
