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

import com.example.test2.R;
import com.example.test2.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   // FirebaseAuth mAuth;
    private TextView register;
    private EditText editTextUsername, editTextPassword;
    private Button SignIn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        SignIn = (Button) findViewById(R.id.loginbtn);
        SignIn.setOnClickListener(this);

        editTextUsername =  (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(this);
        //admin and admin

        //loginbtn.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
            //    if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
            //        Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
             //   }else
                    //incorrect
              //      Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
           // }
       // });


    }

    @Override
    public void onResume() {
        super.onResume();
        user.reload();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.loginbtn:
                if(user.isEmailVerified()){
                    userlogin();
                    break;
                }
                else{
                    Toast.makeText(MainActivity.this,"Please verify your account",Toast.LENGTH_LONG).show();
                }
        }
    }

    private void userlogin() {

        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty()){
            editTextUsername.setError("Username is required !");
            editTextUsername.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            editTextUsername.setError("Please enter valid username/email id !");
            editTextUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required !");
            editTextPassword.requestFocus();
            return;
        }




        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //redirect to user profile
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed to login Please check your credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}