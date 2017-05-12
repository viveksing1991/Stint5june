package com.chromeinfo.stint.models.register;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 11/5/17.
 */
/*Class is model of User information*/
public class UserLocation implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    @SerializedName("state")
    private String state;

    @SerializedName("country")
    private String country;

    @SerializedName("locatoin_name")
    private String locationName;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public static Creator<UserLocation> getCREATOR() {
        return CREATOR;
    }

    @SerializedName("created_date")
    private String creditDate;

    @SerializedName("update_date")
    private String updateDate;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
        dest.writeString(this.state);
        dest.writeString(this.country);
        dest.writeString(this.locationName);
        dest.writeString(this.creditDate);
        dest.writeString(this.updateDate);
    }

    public UserLocation() {
    }

    protected UserLocation(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.lat = in.readString();
        this.lng = in.readString();
        this.state = in.readString();
        this.country = in.readString();
        this.locationName = in.readString();
        this.creditDate = in.readString();
        this.updateDate = in.readString();
    }

    public static final Parcelable.Creator<UserLocation> CREATOR = new Parcelable.Creator<UserLocation>() {
        @Override
        public UserLocation createFromParcel(Parcel source) {
            return new UserLocation(source);
        }

        @Override
        public UserLocation[] newArray(int size) {
            return new UserLocation[size];
        }
    };
}
