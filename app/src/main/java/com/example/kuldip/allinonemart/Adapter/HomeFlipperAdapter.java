package com.example.kuldip.allinonemart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.DataModel.HomeImageList;
import com.example.kuldip.allinonemart.DataModel.HomeImageUrl;
import com.example.kuldip.allinonemart.DataModel.ImageUrl;
import com.example.kuldip.allinonemart.R;

import java.util.ArrayList;

public class HomeFlipperAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HomeImageUrl> homeimages;

    public HomeFlipperAdapter(Context context, ArrayList<HomeImageUrl> homeimages) {
        this.context = context;
        this.homeimages = homeimages;
    }

    @Override
    public int getCount() {
        return homeimages.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeImageUrl imageUrl = homeimages.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_flipper_items, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.homeflipperimage);


        Glide.with(context).load(imageUrl.getFrntimg_url()).into(imageView);
        return view;

    }
}
