package com.example.bpn.myapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bpn on 20/09/17.
 */

public class BeanJsonData implements Parcelable {

    private String Name;
    private String Version;
    private String Api;

    public BeanJsonData(String Name, String Version, String Api) {
        this.Name = Name;
        this.Api = Api;
        this.Version = Version;
    }


    protected BeanJsonData(Parcel in) {
        Name = in.readString();
        Version = in.readString();
        Api = in.readString();
    }

    public static final Creator<BeanJsonData> CREATOR = new Creator<BeanJsonData>() {
        @Override
        public BeanJsonData createFromParcel(Parcel in) {
            return new BeanJsonData(in);
        }

        @Override
        public BeanJsonData[] newArray(int size) {
            return new BeanJsonData[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getApi() {
        return Api;
    }

    public void setApi(String api) {
        Api = api;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Version);
        parcel.writeString(Api);
    }
}
