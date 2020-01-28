package com.example.doctorpatientapplication1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView showAppointment, showdate;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.showAppointment = itemView.findViewById(R.id.tx_appointment);
            this.showdate = itemView.findViewById(R.id.textview_date);
            this.imageView = itemView.findViewById(R.id.image_icon);
        }
    }

    private List<scheduledAppointment> myAppointment;

    public MyAdapter(List<scheduledAppointment> myAppointment) {
        this.myAppointment = myAppointment;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.showAppointment.setText(myAppointment.get(position).name);
        holder.showdate.setText(myAppointment.get(position).date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the functionality
            }
        });
    }

    @Override
    public int getItemCount() {
        return myAppointment.size();
    }
}