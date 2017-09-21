package com.example.bpn.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by bpn on 20/09/17.
 */

public class BeanJsonData  {

    private String Name;
    private String Version;
    private String Api;

    public BeanJsonData(String Name , String Version , String Api){
        this.Name = Name;
        this.Api = Api ;
        this.Version = Version;
    }



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


}
