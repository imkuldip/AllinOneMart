package com.example.kuldip.allinonemart.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.kuldip.allinonemart.Activities.ViewPasalActivity;
import com.example.kuldip.allinonemart.Adapter.CustomSwipeAdapter;
import com.example.kuldip.allinonemart.R;

public class HomeFragment extends Fragment {
    Context context;
    ViewFlipper viewFlipper;
    ViewPager viewPager;
    Button first,second,third,front;
    int fst,scnd,thrd,frnt;
    CustomSwipeAdapter customSwipeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        View view = inflater.inflate(R.layout.viewpager,null);

        first = view.findViewById(R.id.first_floor);
        second = view.findViewById(R.id.second_floor);
        third = view.findViewById(R.id.third_floor);
        front = view.findViewById(R.id.front_view);

        viewFlipper = view.findViewById(R.id.v_flipper);
//        viewPager = view.findViewById(R.id.viewpager);
        int images[] = {R.mipmap.one,R.mipmap.two,R.mipmap.three,R.mipmap.four};


        for (int i = 0; i<images.length;i++)
            flipperImages(images[i]);

//        for (int image : images)
//        {
//            flipperImages(image);
//        }

//        viewPager = view.findViewById(R.id.viewpager);
//        customSwipeAdapter = new CustomSwipeAdapter(context);
//        viewPager.setAdapter(customSwipeAdapter);
//        new Timer().scheduleAtFixedRate(new MyTimerTask(),2000,4000);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fst=1;
                sendId(fst);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scnd=2;
                sendId(scnd);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thrd=3;
                sendId(thrd);
            }
        });
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frnt=4;
                sendId(frnt);
            }
        });


        return view;
    }

    public void flipperImages (int image){
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(context,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(context,android.R.anim.slide_out_right);
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
    }

//    public class MyTimerTask extends TimerTask{
//        @Override
//        public void run() {
//            getActivity().runOnUiThread(new Runnable(){
//
//                @Override
//                public void run() {
//                    if(viewPager.getCurrentItem()==0)
//                        viewPager.setCurrentItem(1);
//                    else if(viewPager.getCurrentItem()==1)
//                        viewPager.setCurrentItem(2);
//                    else if(viewPager.getCurrentItem()==2)
//                        viewPager.setCurrentItem(3);
//                    else
//                        viewPager.setCurrentItem(0);
//
//                }
//
//            });
//        }
//    }

    public void sendId(int id){
        int categoryId = id;

        Intent intent=new Intent(context, ViewPasalActivity.class);
        intent.putExtra("category_id", categoryId);
        startActivity(intent);
        }
}





