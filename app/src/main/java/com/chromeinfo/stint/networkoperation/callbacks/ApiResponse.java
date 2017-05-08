package com.chromeinfo.stint.networkoperation.callbacks;

import com.chromeinfo.stint.modles.register.User;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by root on 4/5/17.
 */

public class ApiResponse {


        @SerializedName("success")
        public boolean success;

        @SerializedName("session")
        public boolean isSessionExpire;

        @SerializedName("message")
        public Messages messages;

        @SerializedName("error")
        public ApiResponseError apiResponseError;


}
