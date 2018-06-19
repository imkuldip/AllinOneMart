package com.example.kuldip.allinonemart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuldip.allinonemart.Activities.ViewDetailActivity;
import com.example.kuldip.allinonemart.Activities.ViewPasalActivity;
import com.example.kuldip.allinonemart.DataModel.NumberOfView;
import com.example.kuldip.allinonemart.R;

import java.util.List;

public class ViewPasalAdapter extends RecyclerView.Adapter<ViewPasalAdapter.ViewPasalHolder>{

    private Context context;
    private List<NumberOfView> list;


    public ViewPasalAdapter(Context context,List<NumberOfView> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewPasalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.viewpasal_view,parent,false);
        ViewPasalHolder viewPasalHolder = new ViewPasalHolder(layoutView);
        return viewPasalHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPasalHolder holder, int position) {

         final String pasalName =list.get(position).getP_no();
         final String categoryId = list.get(position).getC_id();
         final String status = list.get(position).getStatus();
         final String description = list.get(position).getDescription();
         final String price = list.get(position).getPrice();
         final String phone = list.get(position).getPhone();
         final String email= list.get(position).getEmail();

         holder.btn.setText(status);
//         if (list.get(position).getStatus().equals("BOOKED"))
//         {
//             holder.btn.setBackgroundColor(Color.parseColor("#FFFF000D"));
//         }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewDetailActivity.class);
                intent.putExtra("pasalName", pasalName);
                intent.putExtra("categoryId",categoryId);
                intent.putExtra("status", status);
                intent.putExtra("description", description);
                intent.putExtra("price", price);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewPasalHolder extends RecyclerView.ViewHolder{
        Button btn;

        public ViewPasalHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}
