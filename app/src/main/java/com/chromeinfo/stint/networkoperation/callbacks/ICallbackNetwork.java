package com.chromeinfo.stint.networkoperation.callbacks;

import com.chromeinfo.stint.models.facebook.FbUserDetails;
import com.chromeinfo.stint.models.register.User;

/**
 * Created by root on 9/5/17.
 */
/*This Inter face is used as callback for getting the user facebook information */
public interface ICallbackNetwork {
    void userInfoFb(FbUserDetails user);
}
