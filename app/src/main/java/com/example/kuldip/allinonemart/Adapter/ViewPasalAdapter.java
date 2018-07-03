package com.example.kuldip.allinonemart.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kuldip.allinonemart.Activities.ViewDetailActivity;
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

        // holder.btn.setText(status);

         if(status.equals("1"))
         {
             holder.btn.setBackgroundResource(R.drawable.home_green);
             //holder.btn.setBackgroundColor(Color.parseColor("#4fea76"));;
         }else if (status.equals("2")){
//             holder.btn.setBackgroundColor(Color.parseColor("#f50408"));;
             holder.btn.setBackgroundResource(R.drawable.if_home_red);


         }
         else if (status.equals("3")){
//             holder.btn.setBackgroundColor(Color.parseColor("#fff200"));;
             holder.btn.setBackgroundResource(R.drawable.if_home_yellow);
         }

//         if((!status.equals("Booked"))) {

             holder.btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, ViewDetailActivity.class);
                     intent.putExtra("pasalName", pasalName);
                     intent.putExtra("categoryId", categoryId);
                     intent.putExtra("status", status);
                     intent.putExtra("description", description);
                     intent.putExtra("price", price);
                     intent.putExtra("phone", phone);
                     intent.putExtra("email", email);
                     context.startActivity(intent);
//                     ((Activity)context).finish();

                 }
             });
//         }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewPasalHolder extends RecyclerView.ViewHolder{
        ImageButton btn;

        public ViewPasalHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}
