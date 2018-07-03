package com.example.kuldip.allinonemart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kuldip.allinonemart.DataModel.ImageUrl;
import com.example.kuldip.allinonemart.R;

import java.util.ArrayList;

public class FlipperAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageUrl> images;

    public FlipperAdapter(Context context, ArrayList<ImageUrl> images) {
        this.context = context;
        this.images = images;
    }


    @Override
    public int getCount() {
        return images.size();
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
        ImageUrl imageUrl = images.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pasal_flipper_items, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);


        Glide.with(context).load(imageUrl.getImage_url()).into(imageView);
        return view;

    }
}
