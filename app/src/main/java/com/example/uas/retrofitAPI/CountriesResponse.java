package com.example.uas.retrofitAPI;

public class CountriesResponse {
    private CountriesResult[] countries;

    public CountriesResult[] getCountries() {
        return countries;
    }

    public void setCountries(CountriesResult[] countries) {
        this.countries = countries;
    }
}
