package com.example.test2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.test2.R;
import com.example.test2.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView app_name, registerUser;
    private EditText editTextFullName, editTextEmailID, editTextAge, editTextPhoneNumber, editTextLicenseNo,editTextAddress, editTextPassword;
    private ProgressBar progressBar;
    public SharedPreferences sh;
    public SharedPreferences.Editor editor;

    private FirebaseDatabase rootNode;      // Reference to root node of the database.
    private DatabaseReference myRef;    // Reference to subelements of the root node.
// ...
// Initialize Firebase Auth




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterUser.this, MainActivity.class));
            }
        });
        mAuth = FirebaseAuth.getInstance();


        registerUser = (Button) findViewById(R.id.registerbtn);
        registerUser.setOnClickListener(this);
        editTextFullName = (EditText) findViewById(R.id.full_name);
        editTextEmailID= (EditText) findViewById(R.id.username);
        editTextAge= (EditText) findViewById(R.id.age);
        editTextPhoneNumber= (EditText) findViewById(R.id.phone_number);
        editTextLicenseNo= (EditText) findViewById(R.id.license_no);
        editTextAddress= (EditText) findViewById(R.id.address);
        editTextPassword= (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

    }

    @Override
    public void onClick(View view) {
    switch(view.getId()){
        case R.id.registerbtn:
            registerUser();  // method with the name of RegisterUser
            break;
    }

    }
    private void registerUser(){
        String email = editTextEmailID.getText().toString().trim();
        String FullName = editTextFullName.getText().toString().trim();
        String Age= editTextAge.getText().toString().trim();
        String PhoneNumber= editTextPhoneNumber.getText().toString().trim();
        String LicenseNo= editTextLicenseNo.getText().toString().trim();
        String Address= editTextAddress.getText().toString().trim();
        String Password= editTextPassword.getText().toString().trim();

        if( FullName.isEmpty()){
            editTextFullName.setError("Full Name is required !");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmailID.setError("Email id is required !");
            editTextEmailID.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailID.setError("Please provide valid email id !");
            editTextEmailID.requestFocus();
            return;
        }
        if(Age.isEmpty()){
            editTextAge.setError("Age is required !");
            editTextAge.requestFocus();
            return;
        }
        if(PhoneNumber.isEmpty()){
            editTextPhoneNumber.setError("Phone number is required !");
            editTextPhoneNumber.requestFocus();
            return;
        }
        if(LicenseNo.isEmpty()){
            editTextLicenseNo.setError("License number is required !");
            editTextLicenseNo.requestFocus();
            return;
        }
        if(Address.isEmpty()){
            editTextAddress.setError("Address is required !");
            editTextAddress.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            editTextPassword.setError("Password is required !");
            editTextPassword.requestFocus();
            return;
        }
        if(Password.length()<6){
            editTextPassword.setError(" Min password length is 6 character!  ");
            editTextPassword.requestFocus();
            return;

        }

     //   rootNode = FirebaseDatabase.getInstance();
      //  myRef=  rootNode.getReference();
       // User user = new User(FullName,email, Age, PhoneNumber, LicenseNo, Address, Password);

       // myRef.child("User").child(email).setValue(user);



        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){

                        if(task.isSuccessful()){
                            User user = new User(FullName,email, Age, PhoneNumber, LicenseNo, Address, Password);


                            FirebaseDatabase.getInstance().getReference( "Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                rootNode = FirebaseDatabase.getInstance();
                                                myRef=  rootNode.getReference();


                                                myRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);

                                                Toast.makeText( RegisterUser.this,"User has been registered successfully ",Toast.LENGTH_LONG ).show();
                                                progressBar.setVisibility(View.GONE);


                                                // Redirect to login layout.
                                                startActivity(new Intent(RegisterUser.this, MainActivity.class));
                                            }else {
                                                 Toast.makeText(RegisterUser.this,"Failed to Register, Try Again !", Toast.LENGTH_LONG).show();
                                                 progressBar.setVisibility((View.GONE));
                                            }
                                        }
                                    });

                        }else {
                            Toast.makeText(RegisterUser.this,"Failed to Register, Try Again !", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility((View.GONE));

                        }
                    }

                                       }
                );


    }


}