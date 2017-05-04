package com.chromeinfo.stint.modles.register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 4/5/17.
 */


/* This class is a model of user registration page */
public class User {

    @SerializedName("email")
    String email;
    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("username")
    String userName;

    @SerializedName("moblie")
    String mobile;

    @SerializedName("password")
    String password;


    @SerializedName("device_type")
    String deviceType;

    public String getDeviceType() {
        return deviceType;
    }

    public User(){


    }


    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

}
