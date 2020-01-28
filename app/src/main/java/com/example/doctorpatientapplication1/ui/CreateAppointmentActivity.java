package com.example.doctorpatientapplication1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doctorpatientapplication1.MainActivity;
import com.example.doctorpatientapplication1.R;
import com.example.doctorpatientapplication1.scheduledAppointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAppointmentActivity extends AppCompatActivity {

    EditText createAppointment,date;
    Button save;
    private FirebaseUser currentUser;
    private DatabaseReference myRef;
    static int appointmentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        date=findViewById(R.id.ed_date);
        createAppointment = findViewById(R.id.create_new_appointment);
        save = findViewById(R.id.button_save_appointment);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAppointmentinFirebase(date.getText().toString(),createAppointment.getText().toString());
           //finish();
             //   Intent intent = new Intent(CreateAppointmentActivity.this, MainActivity.class);
               // startActivity(intent);
            }
        });
    }

    public void createAppointmentinFirebase(String created, String appointmentCreated) {
        scheduledAppointment appointments = new scheduledAppointment(created, appointmentCreated);
        myRef.child("appointment").child(String.valueOf(appointmentId)).setValue(appointments);
        appointmentId++;
    }
}


