package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFlightsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private User logedInUser;
    private Intent userIntent;
    private ArrayAdapter<String> flightListAdapter;
    private List<String> listFlights;
    private ListView flightsList;
    private Button goBackButton;

    public void init() {
        userIntent = getIntent();
        logedInUser = (User) userIntent.getSerializableExtra("user");
        flightsList = (ListView) findViewById(R.id.userFlightsList);
        goBackButton = (Button) findViewById(R.id.back);

        listFlights = logedInUser.getListFlights();

        flightListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listFlights);
        flightsList.setAdapter(flightListAdapter);
        flightsList.setOnItemClickListener(this);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent na = new Intent(UserFlightsActivity.this, UserActivity.class);
                finish();
                startActivity(na);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_flights);
        init();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        DataBaseHelper.getInstance().getDB().child("flights").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Flight newFlight = dataSnapshot.child(listFlights.get(position)).getValue(Flight.class);
                Intent na = new Intent(UserFlightsActivity.this, InformationActivity.class);
                na.putExtra("logedInUser", logedInUser);
                na.putExtra("flight", newFlight);
                finish();
                startActivity(na);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
