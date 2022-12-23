package com.example.uas.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.uas.adapter.ContinentAdapter;
import com.example.uas.databinding.FragmentHomeBinding;
import com.example.uas.retrofitAPI.ApiEndpoint;
import com.example.uas.retrofitAPI.ContinentsResult;
import com.example.uas.R;
import com.example.uas.retrofitAPI.ApiService;
import com.example.uas.ui.activity.MainActivitySearch;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    private ApiEndpoint apiEndpoint;

    private FragmentHomeBinding binding;

    private RecyclerView recyclerView;
    private ContinentAdapter continentAdapter;
    private ArrayList<ContinentsResult> continentResults;

    private final String yesterday = "false";
    private final String twoDaysAgo = "false";
    private final String sort = "cases";
    private final String allowNull = "false";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        //binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        //View root = binding.getRoot();
        Button button;

        Retrofit retrofit=ApiService.getClient();
        apiEndpoint=retrofit.create(ApiEndpoint.class);
        continentResults = new ArrayList<>();

        getContinentsData(view);


        button = (Button)view.findViewById(R.id.BtnCari);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.this.getActivity(),MainActivitySearch.class);
                HomeFragment.this.startActivity(intent);
            }
        });
        return view;
    }


    private void getContinentsData(View view){
        recyclerView = view.findViewById(R.id.recyclerView);

        Call<ArrayList<ContinentsResult>> call = apiEndpoint.getDataContinents(yesterday, twoDaysAgo, sort, allowNull);
        call.enqueue(new Callback<ArrayList<ContinentsResult>>(){

            @Override
            public void onResponse(@NonNull Call<ArrayList<ContinentsResult>> call,
                                   @NonNull Response<ArrayList<ContinentsResult>> response) {
                if(response.isSuccessful()){
                    continentResults = response.body();

                    for(int i = 0; i < Objects.requireNonNull(continentResults).size(); i++){
                        continentAdapter = new ContinentAdapter(continentResults, getContext());
                        recyclerView.setAdapter(continentAdapter);
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ArrayList<ContinentsResult>> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}