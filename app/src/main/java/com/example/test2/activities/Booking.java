package com.example.test2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.test2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Booking extends AppCompatActivity {
    int aday,amonth, ayear,ahour,aminute;
    int dday, dmonth, dyear, dhour, dminute;

    long arr = 0;
    long dep = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        EditText arriveTimestamp;
        EditText departTimestamp;
        arriveTimestamp = (EditText) findViewById(R.id.arriveTimestamp);
        departTimestamp = (EditText) findViewById(R.id.departTimestamp);
        arriveTimestamp.setRawInputType((InputType.TYPE_NULL));
        departTimestamp.setRawInputType(InputType.TYPE_NULL);
        arriveTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                 aday = cldr.get(Calendar.DAY_OF_MONTH);
                 amonth = cldr.get(Calendar.MONTH);
                 ayear = cldr.get(Calendar.YEAR);
                 ahour = cldr.get(Calendar.HOUR);
                aminute = cldr.get(Calendar.MINUTE);
                DatePickerDialog start =new DatePickerDialog(Booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        arriveTimestamp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + " "+hourOfDay+ ":"+minute);
                                        try{
                                            arr = format.parse(arriveTimestamp.getText().toString()).getTime();
                                        }
                                        catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, ahour, aminute, DateFormat.is24HourFormat(Booking.this));
                                timePickerDialog.show();
                            }

                        }, ayear, amonth, aday);
                    start.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    start.show();
            }
        });
        departTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                dday = cldr.get(Calendar.DAY_OF_MONTH);
                dmonth = cldr.get(Calendar.MONTH);
                dyear = cldr.get(Calendar.YEAR);
                DatePickerDialog end =new DatePickerDialog(Booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        departTimestamp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + " " + hourOfDay + ":" + minute);
                                        try{
                                            dep = format.parse(departTimestamp.getText().toString()).getTime();
                                        }
                                        catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, dhour, dminute, DateFormat.is24HourFormat(Booking.this));

                                timePickerDialog.show();
                            }
                        }, dyear, dmonth, dday);
                end.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                end.show();
            }
        });
    }

    protected void onClick(){
        super.onStart();

        if (arr<System.currentTimeMillis()||dep<System.currentTimeMillis())
        {
            Toast.makeText(this, "Arrival Past time cannot be selected.", Toast.LENGTH_SHORT).show();
        }
        else if(arr>dep)
        {
            Toast.makeText(this, "Depart time cannot be before arrival time.", Toast.LENGTH_SHORT).show();
        }
    }
}