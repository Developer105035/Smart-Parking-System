package com.example.test2.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.test2.R;

import java.util.Calendar;

public class Booking extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        EditText arriveTimestamp;
        EditText departTimestamp;
        arriveTimestamp = (EditText) findViewById(R.id.arriveTimestamp);
        departTimestamp = (EditText) findViewById(R.id.departTimestamp);

        arriveTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                int hour = cldr.get(Calendar.HOUR);
                int minute = cldr.get(Calendar.MINUTE);

                DatePickerDialog start =new DatePickerDialog(Booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                arriveTimestamp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + " "+hour+ ":"+minute);
                            }

                        }, year, month, day);
                start.show();
            }
        });
        departTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                int hour = cldr.get(Calendar.HOUR);
                int minute = cldr.get(Calendar.MINUTE);
                DatePickerDialog end =new DatePickerDialog(Booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                departTimestamp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year+ " "+hour+ ":"+minute);
                            }

                        }, year, month, day);
                end.show();
            }
        });

    }
}