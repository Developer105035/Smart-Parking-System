package com.example.test2.activities;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Calendar;

public class Booking extends AppCompatActivity {
    long date=0,cur;
    int a_day, a_month, a_year;
    String arrivalDate;
    int[][] slots = new int[3][];
    String formattedDateStr = "";
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TableLayout tbl;
    EditText arriveTimestamp;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat saveTime = new SimpleDateFormat("dd-MM-yyyy HH");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        tbl = (TableLayout) findViewById(R.id.slots);
        tbl.setStretchAllColumns(true);
        Button search = (Button) findViewById(R.id.search);
        search.setEnabled(false);
        search.setOnClickListener(onClickListener);
        arriveTimestamp = (EditText) findViewById(R.id.arriveTimestamp);
        arriveTimestamp.setRawInputType((InputType.TYPE_NULL));
        arriveTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                a_day = cldr.get(Calendar.DAY_OF_MONTH);
                a_month = cldr.get(Calendar.MONTH);
                a_year = cldr.get(Calendar.YEAR);
                DatePickerDialog start = new DatePickerDialog(Booking.this,
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

    public void populateTable() {

        int timeStart = 1;
        arrivalDate = arriveTimestamp.getText().toString();
        try {
            LocalDateTime ldt = LocalDateTime.now();
            formattedDateStr = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(ldt);
            date = format.parse(arrivalDate).getTime();
            cur = format.parse(formattedDateStr).getTime();
            if (date == cur) {
                DateFormat df = new SimpleDateFormat("HH");
                df.setTimeZone(TimeZone.getTimeZone("EST"));
                timeStart = Integer.parseInt(df.format(new Date())) + 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date!=0){
//            for(int i = 0, i <  )


            reference = FirebaseDatabase.getInstance().getReference("Booking");
            reference.child(Long.toString(date)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        int i = 0,len=(int)childSnapshot.getChildrenCount(), slot=Integer.parseInt(childSnapshot.getKey())-1;

                        slots[slot] = new int[len];
                        if(childSnapshot!=null){
                            for(DataSnapshot subChild: childSnapshot.getChildren())
                            {
                                if(subChild!=null){
                                    slots[slot][i++]=Integer.parseInt(subChild.getKey());
                                }

                            }
                        }

                    }

//                    for(int i =0;i<3;i++){
//                        for(int j=0;j<slots[i].length;j++){
//                            System.out.println(i+" " +j+" "+slots[i][j]);
//                        }
//                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }

        TableRow header = new TableRow(getApplicationContext());
        for (int j = 1; j <= 3; j++) {
            TextView t = new TextView(getApplicationContext());
            t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            t.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size));
            t.setTextColor(Color.rgb(255, 255, 255));
            t.setGravity(Gravity.CENTER);
            t.setText("Slot" + Integer.toString(j));
            header.addView(t);
        }
        tbl.addView(header, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        for (int i = timeStart; i <= 23; i++) {
            TableRow tr = new TableRow(getApplicationContext());

            for (int j = 1; j <= 3; j++) {
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10, 10, 10, 10);
                Button b = new Button(getApplicationContext());
                b.setTag(Integer.toString(j) + ", " + Integer.toString(i));
                b.setLayoutParams(lp);
                b.setText(Integer.toString(i));

               System.out.println(slots[0].length);
//                    for (int e : slots[j-1]) {
//                        if (e == i) {
//                            b.setBackgroundColor(Color.rgb(255, 0, 0));
//                            flag = 1;
//                            break;
//                        }
//
//                }



//
//                if((Arrays.asList(slots[j-1]).contains(6)) && slots[j-1].length != 0){
//                    b.setBackgroundColor(Color.rgb(255, 0, 0));
//                    System.out.println("we are in the loop of changing color to RED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                }
//                if(slots.size()!=0 && slots.get(j-1).size()!=0){
//                    for(int k = 0;k<slots.get(j-1).size();k++)
//                    {
//                        if(slots.get(j-1).get(k) == i){
//                            b.setBackgroundColor(Color.rgb(255, 0, 0));
//                            flag = 1;
//                            break;
//                        }
//                    }
//
//                }

                    b.setBackgroundColor(Color.rgb(0, 255, 0));
                    b.setOnClickListener(onClickListener);

                tr.addView(b);
            }

            tbl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search:
                    tbl.removeAllViews();
                    populateTable();
                    break;
            }

            Object s = v.getTag();
            if(s != null){
                List<String> l = Arrays.asList(s.toString().split(","));
                String slot =l.get(0).trim(), time = l.get(1).trim();
                AlertDialog.Builder b = new AlertDialog.Builder(Booking.this);
                b.setMessage("Confirm booking for date "+ arrivalDate+ "\nTime: "+ time +"\nSlot: "+slot+"?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        long timestamp = 0;
                        try {
                            timestamp = saveTime.parse(arrivalDate+" "+ time).getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SlotBooking s = new SlotBooking(timestamp,slot,userID);
                        FirebaseDatabase.getInstance().getReference( "Booking")
                                .child(Long.toString(date))
                                .child(slot)
                                .child(time)
                                .setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText( Booking.this,"Booking Successful ",Toast.LENGTH_LONG ).show();
                                            startActivity(new Intent(Booking.this, ProfileActivity.class));
                                        }else {
                                            Toast.makeText(Booking.this,"Failed to book, Try Again !", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        FirebaseDatabase.getInstance().getReference( "History")
                                .child(Long.toString(date))
                                .child(slot)
                                .child(time)
                                .setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText( Booking.this,"Booking Successful ",Toast.LENGTH_LONG ).show();
                                            startActivity(new Intent(Booking.this, ProfileActivity.class));
                                        }else {
                                            Toast.makeText(Booking.this,"Failed to book, Try Again !", Toast.LENGTH_LONG).show();
                                        }                                    }
                                });
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = b.create();
                dialog.setTitle("Confirm Booking?");
                dialog.show();
            }

        }


    };
}

