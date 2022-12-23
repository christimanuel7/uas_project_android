package com.example.uas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uas.R;
import com.example.uas.databinding.ActivityContinentDetailBinding;
import com.example.uas.retrofitAPI.ApiEndpoint;
import com.example.uas.retrofitAPI.ApiService;
import com.example.uas.retrofitAPI.ContinentsResult;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContinentDetailActivity extends AppCompatActivity {
    private ActivityContinentDetailBinding binding;
    private ApiEndpoint apiEndpoint;
    private String continent;
    private TextView txtContinent, txtCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent_detail);

        binding = ActivityContinentDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtContinent=findViewById(R.id.txtContinent);
        txtCountry=findViewById(R.id.countryContinent);


        Retrofit retrofit = ApiService.getClient();
        apiEndpoint = retrofit.create(ApiEndpoint.class);

        continent = getIntent().getStringExtra("continent_name");

        setContinentDetail();
    }

    private void setContinentDetail(){
        String twoDaysAgo = "false";
        String yesterday = "false";
        String allowNull = "false";
        String strict = "false";

        Call<ContinentsResult> call = apiEndpoint.getDetailContinents(continent, yesterday, twoDaysAgo, strict, allowNull);
        call.enqueue(new Callback<ContinentsResult>() {
            @Override
            public void onResponse(@NonNull Call<ContinentsResult> call, @NonNull Response<ContinentsResult> response) {
                if(response.body() != null){


                    txtContinent.setText(response.body().getContinent());

                    String[] countryName = response.body().getCountries();
                    String mCountryName = Arrays.toString(countryName).replace("[", "")
                            .replace("]", "");
                    txtCountry.setText(mCountryName);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ContinentsResult> call, @NonNull Throwable t) {

            }
        });
    }

}