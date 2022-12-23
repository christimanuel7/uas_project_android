package com.example.uas.retrofitAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("/v3/covid-19/continents")
    Call<ArrayList<ContinentsResult>> getDataContinents(@Query("yesterday") String yesterday, @Query("twoDaysAgo")
            String twoDaysAgo, @Query("sort") String sort, @Query("allowNull") String allowNull);

    @GET("/v3/covid-19/countries")
    Call<ArrayList<CountriesResult>> getDataCountries(@Query("yesterday") String yesterday, @Query("twoDaysAgo")
            String twoDaysAgo, @Query("sort") String sort, @Query("allowNull") String allowNull);

    @GET("/v3/covid-19/continents/{continent}")
    Call<ContinentsResult> getDetailContinents(@Path("continent") String continent, @Query("yesterday")
            String yesterday, @Query("twoDaysAgo") String twoDaysAgo, @Query("strict") String strict, @Query("allowNull") String allowNull);
}
