package com.example.kuldip.allinonemart.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kuldip.allinonemart.R;

public class CustomSwipeAdapter extends PagerAdapter {
    Context context;
    private int [] image_resources = {R.mipmap.four, R.mipmap.one,R.mipmap.three,R.mipmap.two};
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.home_fragment,container,false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.image_view);
        imageView.setImageResource(image_resources[position]);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);

    }

}
