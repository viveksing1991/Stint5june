package com.chromeinfo.stint.services;

import com.chromeinfo.stint.networkoperation.api.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by root on 5/5/17.
 */

/*Class is a service for Api request and response*/

public interface StintService {

    @FormUrlEncoded
    @POST("/register")
    public Call<ApiResponse> setUserRegistration(@Field("email") String email, @Field("first_name") String firstName, @Field("last_name") String lastName,
                                                 @Field("username") String userName, @Field("mobile") String mobile, @Field("password") String password,
                                                 @Field("device_id") String deviceId, @Field("register_id") String registerId, @Field("device_type") String deviceType);

    @FormUrlEncoded
    @POST("/login")
    public Call<ApiResponse> userLogin(@Field("username") String userName, @Field("password") String password, @Field("device_id") String deviceId, @Field("register_id") String registerId, @Field("device_type") String deviceType);


    @FormUrlEncoded
    @POST("checkFacebook")
    public Call<ApiResponse> facebookLogin(@Field("email") String email, @Field("device_id") String deviceId, @Field("register_id") String registerId, @Field("device_type") String deviceType);

    @FormUrlEncoded
    @POST("appuser/job")
    public Call<ApiResponse> browseJob(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("appuser/profile/setLocation")
    public Call<ApiResponse> setUserLocation(@Field("user_id") String email, @Field("lat") String lat, @Field("lng") String lng, @Field("locatoin_name") String locationName, @Field("country") String country, @Field("state") String state);


}
