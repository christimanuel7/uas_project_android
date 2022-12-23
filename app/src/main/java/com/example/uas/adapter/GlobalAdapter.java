package com.example.uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uas.R;
import com.example.uas.retrofitAPI.CountriesResult;

import java.util.ArrayList;

public class GlobalAdapter extends RecyclerView.Adapter<GlobalAdapter.ViewHolder> {
    private final ArrayList<CountriesResult> countriesResults;
    private final Context context;

    public GlobalAdapter(ArrayList<CountriesResult> countriesResults, Context context) {
        this.countriesResults = countriesResults;
        this.context = context;
    }

    @NonNull
    @Override
    public GlobalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GlobalAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_global, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GlobalAdapter.ViewHolder holder, int position) {
        holder.txtCountries.setText(countriesResults.get(position).getCountry());
        Glide.with(context)
                .load(countriesResults.get(position).getCountriesFlag().getFlagsURL())
                .into(holder.imgFlag);
        holder.txtCases.setText(String.valueOf(countriesResults.get(position).getCases()));
        holder.txtDeath.setText(String.valueOf(countriesResults.get(position).getDeaths()));
        holder.txtRecovered.setText(String.valueOf(countriesResults.get(position).getRecovered()));
    }

    @Override
    public int getItemCount() {
        return countriesResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtCountries, txtCases, txtDeath, txtRecovered;
        private final ImageView imgFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFlag=itemView.findViewById(R.id.flagCountries);
            txtCountries = itemView.findViewById(R.id.datCountries);
            txtCases = itemView.findViewById(R.id.datCases);
            txtDeath = itemView.findViewById(R.id.datDeath);
            txtRecovered = itemView.findViewById(R.id.datRecovered);
        }
    }
}
