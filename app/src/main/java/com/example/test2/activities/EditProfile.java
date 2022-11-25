package com.example.test2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.test2.R;
import com.example.test2.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference;
    private EditText editTextFullName, editTextEmailID, editTextAge, editTextPhoneNumber, editTextLicenseNo,editTextAddress, editTextPassword;
    private  String fullName,email, age, phone_number, license_no, address;
    private String UserID;
    private FirebaseDatabase rootNode;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile.this, UserSettings.class));
            }
        });
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        editTextFullName = (EditText) findViewById(R.id.full_name);
        editTextEmailID= (EditText) findViewById(R.id.username);
        editTextAge= (EditText) findViewById(R.id.age);
        editTextPhoneNumber= (EditText) findViewById(R.id.phone_number);
        editTextLicenseNo= (EditText) findViewById(R.id.license_no);
        editTextAddress = (EditText) findViewById(R.id.address);
        editTextPassword= (EditText) findViewById(R.id.password);

        UserID = (FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference = FirebaseDatabase.getInstance().getReference("Users").child(UserID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                 fullName = userProfile.getFullName();
                 email = userProfile.getEmail();
                 age = userProfile.getAge();
                 phone_number = userProfile.getPhoneNumber();
                 license_no = userProfile.getLicenseNo();
                 address = userProfile.getAddress();

                TextView FullNameTextView = (TextView) findViewById(R.id.full_name);
                TextView emailTextView  = (TextView) findViewById(R.id.username);
                TextView AgeTextView  = (TextView) findViewById(R.id.age);
                TextView PhoneNumberTextView  = (TextView) findViewById(R.id.phone_number);
                TextView LicenseNoTextView  = (TextView) findViewById(R.id.license_no);
                TextView AddressTextView  = (TextView) findViewById(R.id.address);
                TextView PasswordTextView  = (TextView) findViewById(R.id.password);





                FullNameTextView.setHint(fullName);
                emailTextView.setHint(email);
                AgeTextView.setHint(age);
                PhoneNumberTextView.setHint(phone_number);
                LicenseNoTextView.setHint(license_no);
                AddressTextView.setHint(address);
                PasswordTextView.setHint("***********");
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfile.this,"Something went wrong!", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.submit:
                 UpdateProfile();
                 break;
        }
    }

    private void UpdateProfile(){
        String email = editTextEmailID.getText().toString().trim();
        String FullName = editTextFullName.getText().toString().trim();
        String Age= editTextAge.getText().toString().trim();
        String PhoneNumber= editTextPhoneNumber.getText().toString().trim();
        String LicenseNo= editTextLicenseNo.getText().toString().trim();
        String Address= editTextAddress.getText().toString().trim();
        String Password= editTextPassword.getText().toString().trim();


        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(UserID);
        if (FullName != null) {
            reference.child("fullName").setValue(FullName);
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailID.setError("Please provide valid email id !");
            editTextEmailID.requestFocus();
            return;
        }
        else if(email != null){

            reference.child("email").setValue(email);
        }
        if (Age != null) {
            reference.child("age").setValue(age);
        }
        if (PhoneNumber != null) {
            reference.child("phoneNumber").setValue(PhoneNumber);
        }
        if (LicenseNo != null) {
            reference.child("licenseNo").setValue(LicenseNo);
        }
        if (Address != null) {
            reference.child("address").setValue(Address);
        }
        if(Password != null){
            reference.child("password").setValue(Password);
        }

    }


}