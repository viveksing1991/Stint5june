package com.chromeinfo.stint.networkoperation.callbacks;

import com.chromeinfo.stint.modles.Registration;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by root on 4/5/17.
 */

public interface IApiResponse {

public void getUserRegistration(Call<Registration> response);
}
