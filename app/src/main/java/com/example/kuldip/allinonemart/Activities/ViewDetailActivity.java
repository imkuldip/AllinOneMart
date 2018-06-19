package com.example.kuldip.allinonemart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.Adapter.ViewPasalAdapter;
import com.example.kuldip.allinonemart.DataManager.ApiClient;
import com.example.kuldip.allinonemart.DataManager.ApiInterface;
import com.example.kuldip.allinonemart.DataModel.NumberOfView;
import com.example.kuldip.allinonemart.DataModel.ViewImageSelected;
import com.example.kuldip.allinonemart.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDetailActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    String pasalName,status,description,price,phone,email,categoryId;
    TextView descrip,pri,ph,ema,stat,pasalnam;
    ViewFlipper viewFlipper;
    ImageView detailimg;
    Button book_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        descrip = findViewById(R.id.description);
        pri = findViewById(R.id.price);
        ph = findViewById(R.id.phone);
        ema = findViewById(R.id.email);
        stat = findViewById(R.id.status);
        pasalnam = findViewById(R.id.shutterno);
        book_btn =findViewById(R.id.book_btn);

//        viewFlipper = findViewById(R.id.v_Palaslflipper);
        detailimg = findViewById(R.id.detailimg);
        book_btn = findViewById(R.id.book_btn);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
             pasalName = (String) bd.get("pasalName");
             status = (String) bd.get("status");
             description = (String) bd.get("description");
             price = (String) bd.get("price");
             phone = (String) bd.get("phone");
             email = (String) bd.get("email");
             categoryId = (String) bd.get("categoryId");

            descrip.setText(description);
            pasalnam.setText(pasalName);
            ph.setText(phone);
            pri.setText(price);
            stat.setText(status);
            ema.setText(email);
         }

        Call<List<ViewImageSelected>> call = apiInterface.getImage(pasalName);
        call.enqueue(new Callback<List<ViewImageSelected>>() {
            @Override
            public void onResponse(Call<List<ViewImageSelected>> call, Response<List<ViewImageSelected>> response) {
                List<ViewImageSelected> imageList = response.body();
                int pasalImages[] = new int[imageList.size()];
                for (int i=0; i<imageList.size();i++){
                Glide.with(ViewDetailActivity.this).load(imageList.get(i).getImage_url()).into(detailimg);
//                    pasalImages[i]= Glide.with(ViewDetailActivity.this).load(imageList.get(i).getImage_url());
//                    pasalImages[i]= Picasso.with(ViewDetailActivity.this).load(imageList.get(i).getImage_url());
                }
//                for (int i = 0; i<pasalImages.length;i++)
//                    flipperImages(pasalImages[i]);
            }
            @Override
            public void onFailure(Call<List<ViewImageSelected>> call, Throwable t) {
            }
        });


        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDetailActivity.this,UserDetailActivity.class);
                intent.putExtra("pasalName",pasalName);
                intent.putExtra("categoryId",categoryId);
                startActivity(intent);
            }
        });


    }
//    public void flipperImages (int image){
//        ImageView imageView = new ImageView(ViewDetailActivity.this);
//        imageView.setBackgroundResource(image);
//
//        viewFlipper.addView(imageView);
//        viewFlipper.setFlipInterval(5000);
//        viewFlipper.setAutoStart(true);
//
//        viewFlipper.setInAnimation(ViewDetailActivity.this,android.R.anim.slide_in_left);
//        viewFlipper.setOutAnimation(ViewDetailActivity.this,android.R.anim.slide_out_right);
//    }

}
