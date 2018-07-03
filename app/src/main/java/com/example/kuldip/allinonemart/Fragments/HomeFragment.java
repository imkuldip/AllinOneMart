package com.example.kuldip.allinonemart.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.kuldip.allinonemart.Adapter.AdFlipperAdapter;
import com.example.kuldip.allinonemart.Adapter.CustomSwipeAdapter;
import com.example.kuldip.allinonemart.Adapter.HomeFlipperAdapter;
import com.example.kuldip.allinonemart.Adapter.ViewCategoryAdapter;
import com.example.kuldip.allinonemart.DataManager.ApiClient;
import com.example.kuldip.allinonemart.DataManager.ApiInterface;
import com.example.kuldip.allinonemart.DataModel.AdImageList;
import com.example.kuldip.allinonemart.DataModel.AdImageUrl;
import com.example.kuldip.allinonemart.DataModel.ConnectionDetector;
import com.example.kuldip.allinonemart.DataModel.GetCategory;
import com.example.kuldip.allinonemart.DataModel.HomeImageList;
import com.example.kuldip.allinonemart.DataModel.HomeImageUrl;
import com.example.kuldip.allinonemart.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    Context context;
    ViewFlipper viewFlipper;
    LinearLayoutManager linearLayoutManager;
    CustomSwipeAdapter customSwipeAdapter;
    RecyclerView recyclerView;
    private ApiInterface apiInterface;
    ConnectionDetector cd;
    AdapterViewFlipper homeAdapterViewFlipper,adAdapterViewFlipper;
    TextView ic;
    ProgressBar homeProgressBar,adProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        View view = inflater.inflate(R.layout.home_fragment,null);

        recyclerView = view.findViewById(R.id.categoryRecycler);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        cd = new ConnectionDetector(context);
        ic = view.findViewById(R.id.internetconnection);
        homeProgressBar = view.findViewById(R.id.homeProgressBar);
        adProgressBar = view.findViewById(R.id.adProgressBar);
        homeProgressBar.setVisibility(View.VISIBLE);
        adProgressBar.setVisibility(View.VISIBLE);

        homeAdapterViewFlipper = view.findViewById(R.id.homeAdapterViewFlipper);
        adAdapterViewFlipper = view.findViewById(R.id.adAdapterViewFlipper);

        apiInterface =  ApiClient.getClient().create(ApiInterface.class);

        if (cd.isConnected()) {
            Call<HomeImageList> homeimg = apiInterface.getHomeImageList();
            homeimg.enqueue(new Callback<HomeImageList>() {
                @Override
                public void onResponse(Call<HomeImageList> homeimg, Response<HomeImageList> response) {
                    homeProgressBar.setVisibility(View.GONE);
                    //getting list of heroes
                    ArrayList<HomeImageUrl> homeImages = response.body().getHomeImageList();
                    //creating adapter object
                    HomeFlipperAdapter homeadapter = new HomeFlipperAdapter(context, homeImages);

                    //adding it to adapterview flipper
                    homeAdapterViewFlipper.setAdapter(homeadapter);
                    homeAdapterViewFlipper.setFlipInterval(5000);
                    homeAdapterViewFlipper.startFlipping();
                }
                @Override
                public void onFailure(Call<HomeImageList> homeimg, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            Call<AdImageList> adimg = apiInterface.getAdImageList();
            adimg.enqueue(new Callback<AdImageList>() {
                @Override
                public void onResponse(Call<AdImageList> adimg, Response<AdImageList> response) {
                    adProgressBar.setVisibility(View.GONE);
                    //getting list of heroes
                    ArrayList<AdImageUrl> adImages = response.body().getAdImageList();
                    //creating adapter object
                    AdFlipperAdapter adadapter = new AdFlipperAdapter(context, adImages);
                    //adding it to adapterview flipper
                    adAdapterViewFlipper.setAdapter(adadapter);
                    adAdapterViewFlipper.setFlipInterval(3000);
                    adAdapterViewFlipper.startFlipping();
                }
                @Override
                public void onFailure(Call<AdImageList> adimg, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            Call<List<GetCategory>> call = apiInterface.getCategory();
            call.enqueue(new Callback<List<GetCategory>>() {
                @Override
                public void onResponse(Call<List<GetCategory>> call, Response<List<GetCategory>> response) {
                    List<GetCategory> categoryList = response.body();
                    ViewCategoryAdapter viewCategoryAdapter = new ViewCategoryAdapter(context,categoryList );
                    recyclerView.setAdapter(viewCategoryAdapter);
                }
                @Override
                public void onFailure(Call<List<GetCategory>> call, Throwable t) {
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            ic.setText("No Internet Connection!!");
        }

        return view;

    }


}





