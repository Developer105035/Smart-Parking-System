package com.example.test2.activities;

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

import com.example.test2.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        TextView textView = (TextView) findViewById(R.id.welcome);
        textView.setText("Welcome, "+preferences.getString("name","user"));

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