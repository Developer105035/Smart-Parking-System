package com.example.test2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.graphics.Color;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test2.R;
import com.example.test2.classes.SlotBooking;
import com.example.test2.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Calendar;

public class Booking extends AppCompatActivity implements View.OnClickListener{
    int a_day,a_month, a_year;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TableLayout tbl;
    EditText arriveTimestamp;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        tbl = (TableLayout) findViewById(R.id.slots);
        tbl.setStretchAllColumns(true);
        Button search = (Button) findViewById(R.id.search);
        search.setEnabled(false);
        search.setOnClickListener(this);
        arriveTimestamp = (EditText) findViewById(R.id.arriveTimestamp);
        arriveTimestamp.setRawInputType((InputType.TYPE_NULL));
        arriveTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                 a_day = cldr.get(Calendar.DAY_OF_MONTH);
                 a_month = cldr.get(Calendar.MONTH);
                 a_year = cldr.get(Calendar.YEAR);
                DatePickerDialog start =new DatePickerDialog(Booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        arriveTimestamp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        search.setEnabled(true);
                                    }

                        }, a_year, a_month, a_day);
                    start.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    start.show();
            }
        });

    }
    public void populateTable(){
        reference = FirebaseDatabase.getInstance().getReference("Booking");
        reference.child("2022-11-19").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SlotBooking slot = dataSnapshot.getValue(SlotBooking.class);
                System.out.println(slot.getBookingStart());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        long date,cur;
        int timeStart = 1;
        try{
            LocalDateTime ldt = LocalDateTime.now();
            String formattedDateStr = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(ldt);
            date = format.parse(arriveTimestamp.getText().toString()).getTime();
            cur = format.parse(formattedDateStr).getTime();
            if(date == cur){
                DateFormat df = new SimpleDateFormat("HH");
                df.setTimeZone(TimeZone.getTimeZone("EST"));
                timeStart = Integer.parseInt(df.format(new Date()))+1;
                System.out.println(timeStart);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        TableRow header = new TableRow(getApplicationContext());
        for(int j=1;j<=3;j++)
        {
            TextView t = new TextView(getApplicationContext());
            t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            t.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.font_size));
            t.setTextColor(Color.rgb(255,255,255));
            t.setGravity(Gravity.CENTER);
            t.setText("Slot"+Integer.toString(j));
            header.addView(t);
        }
        tbl.addView(header,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
       for(int i=timeStart; i<=23;i++){

            TableRow tr = new TableRow(getApplicationContext());

            for(int j=1;j<=3;j++)
            {
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10,10,10,10);
                Button b = new Button(getApplicationContext());
                b.setLayoutParams(lp);
                b.setText(Integer.toString(i));
                b.setBackgroundColor(Color.rgb(0,255,0));
                tr.addView(b);
            }

            tbl.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                tbl.removeAllViews();
                populateTable();
                break;
        }
    }


}