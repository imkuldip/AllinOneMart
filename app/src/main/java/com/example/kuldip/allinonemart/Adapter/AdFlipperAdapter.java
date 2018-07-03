package com.example.kuldip.allinonemart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.DataModel.AdImageUrl;
import com.example.kuldip.allinonemart.DataModel.HomeImageUrl;
import com.example.kuldip.allinonemart.R;

import java.util.ArrayList;

public class AdFlipperAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AdImageUrl> adimages;

    public AdFlipperAdapter(Context context, ArrayList<AdImageUrl> adimages) {
        this.context = context;
        this.adimages = adimages;
    }

    @Override
    public int getCount() {
        return adimages.size();
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
        AdImageUrl imageUrl = adimages.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ad_flipper_items, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.adflipperimage);

        Glide.with(context).load(imageUrl.getAdvertise_url()).into(imageView);
//        Glide.with(context).load(imageUrl.getFrntimg_url()).into(imageView);
        return view;
    }
}
