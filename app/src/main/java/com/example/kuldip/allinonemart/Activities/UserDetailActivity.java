package com.example.kuldip.allinonemart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.DataManager.ApiClient;
import com.example.kuldip.allinonemart.DataManager.ApiInterface;
import com.example.kuldip.allinonemart.DataModel.InsertUserDetail;
import com.example.kuldip.allinonemart.DataModel.UpdateStatus;
import com.example.kuldip.allinonemart.DataModel.ViewImageSelected;
import com.example.kuldip.allinonemart.Fragments.HomeFragment;
import com.example.kuldip.allinonemart.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends CommonMenuActivity implements View.OnClickListener{
    EditText userAddress,userPhone,userName,userPurpose;
    private String pasalName,categoryId,u_name,u_address,u_phone,u_purpose;
    Button userDetailBtn;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().setTitle("Your Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userName =findViewById(R.id.uName);
        userPhone = findViewById(R.id.uPhone);
        userAddress = findViewById(R.id.uAddress);
        userDetailBtn  = findViewById(R.id.submitDetail);
        userPurpose = findViewById(R.id.purpose);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null){
            pasalName = (String)bd.get("pasalName");
            categoryId = (String)bd.get("categoryId");
        }

        Toast.makeText(this, "pasal name "+pasalName+" category "+categoryId, Toast.LENGTH_SHORT).show();
        userDetailBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Detail();
    }

    public void Detail(){
        initialize();
        if(!validate())
            Toast.makeText(this, "Please Enter Correct Values", Toast.LENGTH_SHORT).show();
        else {
            sendRequest();
        }
        }

    public void initialize(){
        u_name = userName.getText().toString();
        u_phone = userPhone.getText().toString();
        u_address = userAddress.getText().toString();
        u_purpose = userPurpose.getText().toString();
    }
    public boolean validate(){
        boolean valid = true;
        if (u_name.isEmpty()) {
            userName.setError("Please Enter Valid Name");
            valid = false;
        }
        else if (u_address.isEmpty()) {
            userAddress.setError("Please Enter Valid Address");
            valid = false;
        }
        else if (u_phone.isEmpty()|| u_phone.length()!= 10  || u_phone.charAt(0)!='9' ) {
                valid = false;
        }

        else if (u_purpose.isEmpty()) {
            userPurpose.setError("Please Mention Your Purpose");
            valid = false;
        }
        return valid;
    }

    public void sendRequest(){
        String name = userName.getText().toString();
        String phone = userPhone.getText().toString();
        String address = userAddress.getText().toString();
        String purpose = userPurpose.getText().toString();

        Call<InsertUserDetail> call = apiInterface.insertUserDetail(pasalName,categoryId,name,phone,address,purpose);
        call.enqueue(new Callback<InsertUserDetail>() {
            @Override
            public void onResponse(Call<InsertUserDetail> call, Response<InsertUserDetail> response) {
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(UserDetailActivity.this, "request sent", Toast.LENGTH_SHORT).show();
                    updateStatus();
                }
                else if (response.body().getResponse().equals("error"))
                {
                    Toast.makeText(UserDetailActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
                }
                else if (response.body().getResponse().equals("exist"))
                {
                    Toast.makeText(UserDetailActivity.this, "You have already reserved!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<InsertUserDetail> call, Throwable t) {

            }
        });
    }

    public void updateStatus(){
        Call<UpdateStatus> call = apiInterface.updateStatus(pasalName,categoryId);
        call.enqueue(new Callback<UpdateStatus>() {
            @Override
            public void onResponse(Call<UpdateStatus> call, Response<UpdateStatus> response) {
                if(response.body().getResponse().equals("ok")){
                    Toast.makeText(UserDetailActivity.this, "updated status", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserDetailActivity.this,NavDrawerActivity.class);
                    startActivity(intent);
                    finish();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new HomeFragment());


                }
            }

            @Override
            public void onFailure(Call<UpdateStatus> call, Throwable t) {
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
