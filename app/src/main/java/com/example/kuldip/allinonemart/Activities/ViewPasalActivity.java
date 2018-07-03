package com.example.kuldip.allinonemart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kuldip.allinonemart.Adapter.ViewPasalAdapter;
import com.example.kuldip.allinonemart.DataManager.ApiClient;
import com.example.kuldip.allinonemart.DataManager.ApiInterface;
import com.example.kuldip.allinonemart.DataModel.NumberOfView;
import com.example.kuldip.allinonemart.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPasalActivity extends CommonMenuActivity {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ProgressBar progressBar;

    String categoryId;
    private ApiInterface apiInterface;
    int numberofView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pasal);
        getSupportActionBar().setTitle("View Available Shutters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiInterface =  ApiClient.getClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progressDialog);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recyclerViewPasal);

        gridLayoutManager = new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(gridLayoutManager);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null) {
            categoryId =  bd.getString("categoryId");
        }
        Call<List<NumberOfView>> call = apiInterface.getView(categoryId);
        call.enqueue(new Callback<List<NumberOfView>>() {
            @Override
            public void onResponse(Call<List<NumberOfView>> call, Response<List<NumberOfView>> response) {
                progressBar.setVisibility(View.GONE);
                List<NumberOfView> viewList = response.body();
                ViewPasalAdapter viewPasalAdapter = new ViewPasalAdapter(ViewPasalActivity.this,viewList );
                recyclerView.setAdapter(viewPasalAdapter);
            }
            @Override
            public void onFailure(Call<List<NumberOfView>> call, Throwable t) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
//                startActivity(new Intent (ViewPasalActivity.this,NavDrawerActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent (ViewPasalActivity.this,NavDrawerActivity.class));
//
//        super.onBackPressed();
//        finish();
    }
}
