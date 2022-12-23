package com.example.uas.retrofitAPI;

public class ContinentsResponse {
    private ContinentsResult[] continent;

    public ContinentsResult[] getContinent() {
        return continent;
    }

    public void setContinent(ContinentsResult[] continent) {
        this.continent = continent;
    }
}
