package com.pbaileyapps.android.collegesearch.RetrofitComponents;

import com.google.gson.annotations.SerializedName;

public class WindowItem {
   @SerializedName("name")
    private String name;
   @SerializedName("result")
    private String country;
    public WindowItem(String name, String country){
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }


    public String getCountry() {
        return country;
    }
}
