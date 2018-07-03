package com.example.kuldip.allinonemart.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.Adapter.FlipperAdapter;
import com.example.kuldip.allinonemart.Adapter.ViewPasalAdapter;
import com.example.kuldip.allinonemart.DataManager.ApiClient;
import com.example.kuldip.allinonemart.DataManager.ApiInterface;
import com.example.kuldip.allinonemart.DataModel.ImageList;
import com.example.kuldip.allinonemart.DataModel.ImageUrl;
import com.example.kuldip.allinonemart.DataModel.NumberOfView;
import com.example.kuldip.allinonemart.DataModel.ViewImageSelected;
import com.example.kuldip.allinonemart.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDetailActivity extends CommonMenuActivity {


    private AdapterViewFlipper adapterViewFlipper;
    private ApiInterface apiInterface;
    String pasalName,status,description,price,phone,email,categoryId;
    TextView descrip,pri,ph,ema,stat,pasalnam;
    ViewFlipper viewFlipper;

    Button book_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        getSupportActionBar().setTitle("Shutters Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.adapterViewFlipper);

        descrip = findViewById(R.id.description);
        pri = findViewById(R.id.price);
        ph = findViewById(R.id.phone);
        ema = findViewById(R.id.email);
        stat = findViewById(R.id.status);
        pasalnam = findViewById(R.id.shutterno);
        book_btn =findViewById(R.id.book_btn);

//        viewFlipper = findViewById(R.id.v_Palaslflipper);

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

            if(status.equals("1"))
                stat.setText("Available");
            else if(status.equals("2"))
                stat.setText("Sold Out");
            else if (status.equals("3"))
                stat.setText("Reserved");
            ema.setText(email);

            if(status.equals("Booked")){
                book_btn.setText("Already Booked!!");
                book_btn.setEnabled(false);
            }
         }

//        Call<List<ViewImageSelected>> call = apiInterface.getImage(pasalName);
//        call.enqueue(new Callback<List<ViewImageSelected>>() {
//            @Override
//            public void onResponse(Call<List<ViewImageSelected>> call, Response<List<ViewImageSelected>> response) {
//                List<ViewImageSelected> imageList = response.body();
//                int pasalImages[] = new int[imageList.size()];
//                for (int i=0; i<imageList.size();i++){
//                Glide.with(ViewDetailActivity.this).load(imageList.get(i).getImage_url()).into(detailimg);
//                    pasalImages[i]= Glide.with(ViewDetailActivity.this).load(imageList.get(i).getImage_url());
//                    pasalImages[i]= Picasso.with(ViewDetailActivity.this).load(imageList.get(i).getImage_url());
//                }
//                for (int i = 0; i<pasalImages.length;i++)
//                    flipperImages(pasalImages[i]);
//            }
//            @Override
//            public void onFailure(Call<List<ViewImageSelected>> call, Throwable t) {
//            }
//        });
        Call<ImageList> call = apiInterface.getImageList(pasalName);
        call.enqueue(new Callback<ImageList>() {
            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                //getting list of heroes
                ArrayList<ImageUrl> heros = response.body().getImageList();
                //creating adapter object
                FlipperAdapter adapter = new FlipperAdapter(getApplicationContext(), heros);

                //adding it to adapterview flipper
                adapterViewFlipper.setAdapter(adapter);

                adapterViewFlipper.setFlipInterval(5000);
                adapterViewFlipper.startFlipping();

            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "No Image Available!!", Toast.LENGTH_LONG).show();
            }
        });

        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDetailActivity.this,UserDetailActivity.class);
                intent.putExtra("pasalName",pasalName);
                intent.putExtra("categoryId",categoryId);
                startActivity(intent);
//                finish();
            }
        });


//        adapterViewFlipper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapterViewFlipper.showNext();
//            }
//        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
//                startActivity(new Intent (ViewDetailActivity.this,ViewPasalActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent (ViewDetailActivity.this,ViewPasalActivity.class));
////
////        super.onBackPressed();
//        finish();
//    }

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
