package com.example.kuldip.allinonemart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.DataManager.ApiClient;
import com.example.kuldip.allinonemart.DataManager.ApiInterface;
import com.example.kuldip.allinonemart.DataModel.InsertUserDetail;
import com.example.kuldip.allinonemart.DataModel.ViewImageSelected;
import com.example.kuldip.allinonemart.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener{
    EditText userAddress,userPhone,userName;
    String pasalName,categoryId,u_name,u_address,u_phone;
    Button userDetailBtn;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        userName =findViewById(R.id.uName);
        userPhone = findViewById(R.id.uPhone);
        userAddress = findViewById(R.id.uAddress);
        userDetailBtn  = findViewById(R.id.submitDetail);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null){
            pasalName = (String)bd.get("pasalName");
            categoryId = (String)bd.get("categoryId");
        }

        userDetailBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
         u_name = userName.getText().toString();
         u_phone = userPhone.getText().toString();
         u_address = userAddress.getText().toString();

        Toast.makeText(this, "hello!!"+categoryId, Toast.LENGTH_SHORT).show();

        Call<InsertUserDetail> call = apiInterface.insertUserDetail(pasalName,categoryId,u_name,u_phone,u_address);
        call.enqueue(new Callback<InsertUserDetail>() {
            @Override
            public void onResponse(Call<InsertUserDetail> call, Response<InsertUserDetail> response) {
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(UserDetailActivity.this, "Sucess!!", Toast.LENGTH_SHORT).show();
                }
                else if (response.body().getResponse().equals("error"))
                {
                    Toast.makeText(UserDetailActivity.this, "Failed!!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<InsertUserDetail> call, Throwable t) {

            }
        });

    }
}
