package com.chromeinfo.stint.models.register;

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


    @SerializedName("mobile")
    String mobile;

    @SerializedName("password")
    String password;


    @SerializedName("device_type")

    String deviceType;
    @SerializedName("register_id")
    String registerId;

    @SerializedName("device_id")
    String deviceId;

    @SerializedName("user_location")
    public UserLocation userLocation;


    public User(String email, String firstName, String lastName, String userName, String mobile, String password, String deviceType) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.mobile = mobile;
        this.password = password;
        this.deviceType = deviceType;

    }

    public String getDeviceType() {
        return deviceType;
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


    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }
}
