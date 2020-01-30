package com.example.doctorpatientapplication1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorpatientapplication1.R;
import com.example.doctorpatientapplication1.ScheduledAppointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestActivity extends AppCompatActivity {

    TextView name, createdByUser, timeStamp;
    Button requestForAppointment;
    String AppointmentId;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef, myUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Bundle bundle = getIntent().getExtras();
        AppointmentId = bundle.getString("AppointmentId");

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("appointment");
        myUserRef = FirebaseDatabase.getInstance().getReference("users");
        name = findViewById(R.id.appointment_request);
        createdByUser = findViewById(R.id.created_by_user_request);
        timeStamp = findViewById(R.id.timestamp_request);
        requestForAppointment = findViewById(R.id.request_for_appointment_button);

        myRef.child(AppointmentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.;
                ScheduledAppointment scheduledAppointment = dataSnapshot.getValue(ScheduledAppointment.class);
                if(scheduledAppointment != null) {
                    name.setText(scheduledAppointment.name);
                    getUserEmail(AppointmentId);
                    timeStamp.setText(scheduledAppointment.timeStampTask);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        requestForAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Write to database
                myRef.child(AppointmentId).child("createdByUser").setValue(mAuth.getCurrentUser().getUid());
                Toast.makeText(getApplicationContext(), "Request Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUserEmail(String createdBy) {
        myUserRef.child(createdBy).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userEmail = dataSnapshot.getValue(String.class);
                createdByUser.setText(userEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

