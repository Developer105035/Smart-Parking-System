package com.example.test2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.test2.classes.User;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.test2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserSettings extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth Auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserSettings.this, ProfileActivity.class));
            }
        });
        Auth = FirebaseAuth.getInstance();

        Button SignOut =  findViewById(R.id.buttonLogout);
        SignOut.setOnClickListener(this);

        Button EditProfile = findViewById(R.id.edit_profile);
        EditProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonLogout:
                Auth.signOut();  // method with the name of RegisterUser
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.edit_profile:
                startActivity(new Intent(this, EditProfile.class));

        }
}

    private void edit_user_profile(){
        setContentView(R.layout.activity_edit_profile);


        String UserID = (FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference = FirebaseDatabase.getInstance().getReference("Users").child(UserID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                String fullName = userProfile.getFullName();
                String email = userProfile.getEmail();
                String age = userProfile.getAge();
                String phone_number = userProfile.getPhoneNumber();
                String license_no = userProfile.getLicenseNo();
                String address = userProfile.getAddress();

                TextView FullNameTextView = (TextView) findViewById(R.id.full_name);
                TextView emailTextView  = (TextView) findViewById(R.id.username);
                TextView AgeTextView  = (TextView) findViewById(R.id.age);
                TextView PhoneNumberTextView  = (TextView) findViewById(R.id.phone_number);
                TextView LicenseNoTextView  = (TextView) findViewById(R.id.license_no);
                TextView AddressTextView  = (TextView) findViewById(R.id.address);
                TextView PasswordTextView  = (TextView) findViewById(R.id.password);
                Button submit = findViewById(R.id.submit);
//                submit.setOnClickListener(this);


                FullNameTextView.setHint(fullName);
                emailTextView.setHint(email);
                AgeTextView.setHint(age);
                PhoneNumberTextView.setHint(phone_number);
                LicenseNoTextView.setHint(license_no);
                AddressTextView.setHint(address);
                PasswordTextView.setHint("Password");
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserSettings.this,"Something went wrong!", Toast.LENGTH_LONG).show();

            }
        });







    }

}