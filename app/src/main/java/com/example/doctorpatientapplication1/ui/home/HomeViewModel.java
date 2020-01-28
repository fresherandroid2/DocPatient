package com.example.doctorpatientapplication1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeViewModel extends ViewModel {


    public LiveData<String> getText;
    private MutableLiveData<String> mText;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

    }
    public LiveData<String> getText() {
        return mText;
    }
}
