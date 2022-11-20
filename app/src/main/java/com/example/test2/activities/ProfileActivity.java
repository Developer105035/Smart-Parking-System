package com.example.test2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.test2.R;
import com.example.test2.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference  reference;
    private String userID;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                String fullName = userProfile.getFullName();
                mToolbar.setTitle("Welcome"+fullName.toUpperCase());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something went wrong!", Toast.LENGTH_LONG).show();

            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        // Code to show full name of the user



       // setContentView(R.layout.activity_profile);
       // preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
      //  TextView textView = (TextView) findViewById(R.id.welcome);
      //  textView.setText("Welcome, "+preferences.getString("name","user"));

        Button booking = (Button) findViewById(R.id.book);
        Button history = (Button) findViewById(R.id.history);
        Button settings = (Button) findViewById(R.id.settings);

        booking.setOnClickListener(this);
        history.setOnClickListener(this);
        settings.setOnClickListener(this);

    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book:
                startActivity(new Intent(this, Booking.class));
                break;

            case R.id.history:
                startActivity(new Intent(this, History.class));
                break;

            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                break;
        }
    }

}