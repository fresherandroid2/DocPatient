package com.example.doctorpatientapplication1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorpatientapplication1.MyAdapter;
import com.example.doctorpatientapplication1.PatientActivity;
import com.example.doctorpatientapplication1.R;
import com.example.doctorpatientapplication1.scheduledAppointment;
import com.example.doctorpatientapplication1.ui.CreateAppointmentActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{


    private HomeViewModel homeViewModel;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    TextView textView, textView1;

    private RecyclerView recyclerView;
    private MyAdapter homeAdapter;
    private LinearLayoutManager linearLayoutManager;
    List<scheduledAppointment> homeAppointment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("appointment");

        recyclerView = root.findViewById(R.id.homeRecyclerView);
        setHomeAppointment();
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        myRef.child(String.valueOf(0)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                 scheduledAppointment appointments = dataSnapshot.getValue(scheduledAppointment.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



        FloatingActionButton doctorappointment = root.findViewById(R.id.fab_appointments);
        doctorappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CreateAppointmentActivity.class);
                startActivity(intent);
            }
        });
            if(getActivity() instanceof PatientActivity) {
                doctorappointment.hide();
            }
        return root;
    }

    public void setHomeAppointment() {
        homeAppointment = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                homeAppointment.clear();
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    scheduledAppointment scheduledAppointment = dataSnapshot.child(String.valueOf(i)).getValue(scheduledAppointment.class);
                    homeAppointment.add(scheduledAppointment);
                    homeAdapter = new MyAdapter(homeAppointment);
                    recyclerView.setAdapter(homeAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }
}

