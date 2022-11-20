package com.example.test2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test2.classes.User;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.test2.R;
public class UserSettings extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Auth = FirebaseAuth.getInstance();

        Button SignOut =  findViewById(R.id.buttonLogout);
        SignOut.setOnClickListener(this);

        Button EditProfile = findViewById(R.id.EditProfile);
        EditProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonLogout:
                Auth.signOut();  // method with the name of RegisterUser
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.EditProfile:
                edit_user_profile();



        }
}

    private void edit_user_profile(){
        setContentView(R.layout.activity_edit_profile);
        //UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();



    }

}