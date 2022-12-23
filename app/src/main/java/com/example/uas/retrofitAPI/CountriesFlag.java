package com.example.uas.retrofitAPI;

import com.google.gson.annotations.SerializedName;

public class CountriesFlag {
    @SerializedName("flag")
    String flagsURL;

    public CountriesFlag(String flagsURL) {
        this.flagsURL = flagsURL;
    }

    public String getFlagsURL() {
        return flagsURL;
    }

    public void setFlagsURL(String flagsURL) {
        this.flagsURL = flagsURL;
    }
}
