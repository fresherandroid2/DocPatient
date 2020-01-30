package com.example.doctorpatientapplication1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doctorpatientapplication1.R;
import com.example.doctorpatientapplication1.ScheduledAppointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAppointmentActivity extends AppCompatActivity {

    EditText createAppointment,date;
    Button save;
    private FirebaseUser currentUser;
    private DatabaseReference myRef;

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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                String format = simpleDateFormat.format(new Date());
                createAppointmentinFirebase(createAppointment.getText().toString(), date.getText().toString(), format);
           //finish();
             //   Intent intent = new Intent(CreateAppointmentActivity.this, MainActivity.class);
               // startActivity(intent);
            }
        });
    }

    public void createAppointmentinFirebase(String appointmentCreated, String date, String timeStamp) {
        ScheduledAppointment appointments = new ScheduledAppointment(appointmentCreated, currentUser.getUid(), timeStamp);
        appointments.date = date;
        myRef.child("appointment").push().setValue(appointments);
    }
}


