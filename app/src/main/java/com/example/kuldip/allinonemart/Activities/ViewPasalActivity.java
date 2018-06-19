package com.example.kuldip.allinonemart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class ViewPasalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int categoryId;
    private ApiInterface apiInterface;
    private LinearLayoutManager linearLayoutManager;
    int numberofView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pasal);

        apiInterface =  ApiClient.getClient().create(ApiInterface.class);

        recyclerView = findViewById(R.id.recyclerViewPasal);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null) {
            categoryId = (Integer) bd.get("category_id");
//            Toast.makeText(this, "id"+categoryId, Toast.LENGTH_SHORT).show();
        }

        Call<List<NumberOfView>> call = apiInterface.getView(categoryId);
        call.enqueue(new Callback<List<NumberOfView>>() {
            @Override
            public void onResponse(Call<List<NumberOfView>> call, Response<List<NumberOfView>> response) {
                List<NumberOfView> viewList = response.body();
                numberofView = viewList.size();
                Toast.makeText(ViewPasalActivity.this, "Number of view:"+viewList.size(), Toast.LENGTH_SHORT).show();
                ViewPasalAdapter viewPasalAdapter = new ViewPasalAdapter(ViewPasalActivity.this,viewList );
                recyclerView.setAdapter(viewPasalAdapter);
            }

            @Override
            public void onFailure(Call<List<NumberOfView>> call, Throwable t) {

            }
        });

    }
}
