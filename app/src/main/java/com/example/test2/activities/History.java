package com.example.test2.activities;

import static android.graphics.Color.*;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View;
import android.widget.Toolbar;

import com.example.test2.R;
import com.example.test2.classes.User;
import com.example.test2.classes.history_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class History extends AppCompatActivity implements View.OnClickListener {

    private TableLayout tb2;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tb2 = findViewById(R.id.table);
        //tb2.setStretchAllColumns(true);

        final TextView TextView = (TextView) findViewById(R.id.test);
        //tb2.removeAllViews();
        ViewHistory();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(History.this, ProfileActivity.class));
            }
        });
    }

    public void ViewHistory() {
        reference = FirebaseDatabase.getInstance().getReference("History").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
///



        TableRow header = new TableRow(getApplicationContext());

        for(int j=1;j<=3;j++)
        {
            TextView t = new TextView(getApplicationContext());
          //  t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));




            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
            lp.setMargins(10,10,10,10);
            t.setLayoutParams(lp);


            t.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.font_size));

            //t.setBackgroundColor( rgb(255,255,255));
            //t.setBackgroundResource(R.drawable.border);
            t.setGravity(Gravity.CENTER);
            if(j==1){
                t.setText("Slot");
                t.setTextColor(rgb(255,0,0));
            }
            else if(j==2){
                t.setText("Booking Arrival Time");
                t.setTextColor(rgb(100,120,100));
            }
            else{
                t.setText("Date");
                t.setTextColor(rgb(0,0,255));
            }
            header.setBackgroundResource(R.drawable.border);
           // header.setBackgroundColor(rgb(0,0,0));
            header.addView(t);
        }
        tb2.addView(header,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    history_data data = new history_data();
//                    System.out.println(snapshot.getValue(history_data.class).getSlot());
//                    System.out.println(snapshot.getValue(history_data.class).getDate());
//                    System.out.println(snapshot.getValue(history_data.class).getBookingStart());


                    TableRow tr = new TableRow(getApplicationContext());

                    for(int j=1;j<=3;j++)
                    {
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(30,10,10,10);





                        TextView b = new TextView(getApplicationContext());
                        //b.setBackgroundResource(R.drawable.border);
                        b.setLayoutParams(lp);
                        b.setGravity( Gravity.CENTER  );
                        if(j==1){
                           b.setText((snapshot.getValue(history_data.class).getSlot()));
                            b.setTextColor(rgb(255,0,0));
                        }
                        else if (j==2){
                            b.setText((snapshot.getValue(history_data.class).getBookingStart()));
                            b.setTextColor(rgb(100,120,100));
                        }
                        else if (j==3){

                            b.setText((snapshot.getValue(history_data.class).getDate()));
                            b.setTextColor(rgb(0,0,255));
                        }

                        //b.setBackgroundColor(Color.rgb(0,255,0));
                        tr.setBackgroundResource(R.drawable.border);
                        tr.addView(b);
                    }

                    tb2.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });





















//        for(int i=1; i<=2;i++){
//
//            TableRow tr = new TableRow(getApplicationContext());
//
//            for(int j=1;j<=3;j++)
//            {
//                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
//                lp.setMargins(10,10,10,10);
//
//                TextView b = new TextView(getApplicationContext());
//                b.setLayoutParams(lp);
//                b.setText(Integer.toString(i));
//                b.setBackgroundColor(Color.rgb(0,255,0));
//                tr.addView(b);
//            }
//
//            tb2.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        }

    }

    @Override
    public void onClick(View v) {

    }
}