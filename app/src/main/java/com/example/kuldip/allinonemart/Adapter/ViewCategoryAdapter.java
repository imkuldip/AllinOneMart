package com.example.kuldip.allinonemart.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kuldip.allinonemart.Activities.ViewPasalActivity;
import com.example.kuldip.allinonemart.DataModel.GetCategory;

import com.example.kuldip.allinonemart.R;

import java.util.List;

public class ViewCategoryAdapter extends  RecyclerView.Adapter<ViewCategoryAdapter.ViewCategoryHolder> {
    private Context context;
    private List<GetCategory> list;

    public ViewCategoryAdapter(Context context, List<GetCategory> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.viewcategory_view,parent,false);
        ViewCategoryHolder viewCategoryHolder = new ViewCategoryHolder(layoutView);
        return viewCategoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCategoryHolder holder, int position) {

        final String categoryName =list.get(position).getCategory();
        final String categoryId = list.get(position).getC_id();

        holder.categoryBtn.setText(categoryName);

        holder.categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewPasalActivity.class);
                intent.putExtra("categoryId", categoryId);
                context.startActivity(intent);
//                ((Activity)context).finish();


            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewCategoryHolder extends RecyclerView.ViewHolder{
        Button  categoryBtn;
        public ViewCategoryHolder(View itemView) {
            super(itemView);
            categoryBtn = itemView.findViewById(R.id.categoryBtn);

        }
    }
}
