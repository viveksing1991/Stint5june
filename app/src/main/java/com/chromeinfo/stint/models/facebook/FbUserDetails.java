package com.chromeinfo.stint.models.facebook;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 9/5/17.
 */

public class FbUserDetails implements Parcelable {

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    private String name;
    private String id;
    private String email;

    public FbUserDetails(String id, String name, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.email);
    }

    protected FbUserDetails(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<FbUserDetails> CREATOR = new Parcelable.Creator<FbUserDetails>() {
        @Override
        public FbUserDetails createFromParcel(Parcel source) {
            return new FbUserDetails(source);
        }

        @Override
        public FbUserDetails[] newArray(int size) {
            return new FbUserDetails[size];
        }
    };
}

