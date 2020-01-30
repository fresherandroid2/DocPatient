package com.example.doctorpatientapplication1;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorpatientapplication1.ui.RequestActivity;

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

    private List<ScheduledAppointment> myAppointment;

    public MyAdapter(List<ScheduledAppointment> myAppointment) {
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

                if(v.getContext() instanceof PatientActivity) {
                    Intent intent = new Intent(v.getContext(), RequestActivity.class);
                    Log.d("msg","in it");
                    intent.putExtra("AppointmentId", myAppointment.get(holder.getAdapterPosition()).appointmentID);
                    v.getContext().startActivity(intent);
                }
                else {
                    Intent intent = new Intent(v.getContext(), ApprovedActivity.class);
                    v.getContext().startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return myAppointment.size();
    }
}