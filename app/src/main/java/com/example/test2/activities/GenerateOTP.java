package com.example.test2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.test2.R;

public class GenerateOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GenerateOTP.this, ProfileActivity.class));
            }
        });
        Button send= (Button) findViewById(R.id.button);
        EditText phoneNumber= (EditText) findViewById(R.id.editText);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


//                Uri uri = Uri.parse("smsto:4388553288");
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
//                String number=phoneNumber.getText().toString();
//                String msg="Message";
//                try {
//                    SmsManager smsManager=SmsManager.getDefault();
//                    smsManager.sendTextMessage(number,null,msg,pending,null);
//                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
//                }catch (Exception e)
//                {
//                    System.out.println(e);
//                    Toast.makeText(getApplicationContext(),"Some fields is Empty",Toast.LENGTH_LONG).show();
//                }
            }
        });
    }
}