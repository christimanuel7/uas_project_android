package com.example.uas.retrofitAPI;

import com.google.gson.annotations.SerializedName;

public class ContinentsResult {

    @SerializedName("continent")
    String continent;

    @SerializedName("cases")
    int cases;

    @SerializedName("deaths")
    int deaths;

    @SerializedName("recovered")
    int recovered;

    @SerializedName("countries")
    String [] countries;

    public ContinentsResult(String continent, int cases, int deaths, int recovered, String[] countries) {
        this.continent = continent;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.countries = countries;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }
}
