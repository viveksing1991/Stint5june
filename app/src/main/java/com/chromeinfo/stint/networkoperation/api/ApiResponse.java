package com.chromeinfo.stint.networkoperation.api;

import com.chromeinfo.stint.models.register.User;
import com.chromeinfo.stint.models.register.UserLocation;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 4/5/17.
 */


public class ApiResponse {


    @SerializedName("success")
    public boolean success;

    @SerializedName("error")
    public Integer error;

    @SerializedName("user_info")
    public User user;
    @SerializedName("errordetails")
    public String errorMessage;

    @SerializedName("isNew")
    public boolean isNew;

    @SerializedName("job_list")

    public List<User> jobList;

}
