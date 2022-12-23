package com.example.uas.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas.R;
import com.example.uas.adapter.GlobalAdapter;
import com.example.uas.databinding.FragmentGlobalBinding;
import com.example.uas.retrofitAPI.ApiEndpoint;
import com.example.uas.retrofitAPI.ApiService;
import com.example.uas.retrofitAPI.CountriesResult;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GlobalFragment extends Fragment {
    private ApiEndpoint apiEndpoint;

    private FragmentGlobalBinding binding;

    private RecyclerView recyclerView;
    private GlobalAdapter globalAdapter;
    private ArrayList<CountriesResult> countriesResults;

    private final String yesterday = "false";
    private final String twoDaysAgo = "false";
    private final String sort = "cases";
    private final String allowNull = "false";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentGlobalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Retrofit retrofit= ApiService.getClient();
        apiEndpoint=retrofit.create(ApiEndpoint.class);
        countriesResults = new ArrayList<>();

        getCountriesData(root);

        return root;
    }

    private void getCountriesData(View view){
        recyclerView = view.findViewById(R.id.recyclerView);

        Call<ArrayList<CountriesResult>> call = apiEndpoint.getDataCountries(yesterday, twoDaysAgo, sort, allowNull);
        call.enqueue(new Callback<ArrayList<CountriesResult>>(){

            @Override
            public void onResponse(@NonNull Call<ArrayList<CountriesResult>> call,
                                   @NonNull Response<ArrayList<CountriesResult>> response) {
                if(response.isSuccessful()){
                    countriesResults = response.body();

                    for(int i = 0; i < Objects.requireNonNull(countriesResults).size(); i++){
                        globalAdapter = new GlobalAdapter(countriesResults, getContext());
                        recyclerView.setAdapter(globalAdapter);
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ArrayList<CountriesResult>> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}