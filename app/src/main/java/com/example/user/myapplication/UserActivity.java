package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private FirebaseUser logedInUser;
    private User user;
    private Intent logInIntent;
    private TextView userName, userPoints;
    private Button buttonFlights, buttonBuyFlights, buttonCreateBD;
    private List<User> allUsers = new ArrayList<>();

    public void init() {
        logInIntent = getIntent();

        userName = (TextView) findViewById(R.id.userName);
        userPoints = (TextView) findViewById(R.id.userPoints);


        buttonFlights = (Button) findViewById(R.id.watchUserFlights);
        buttonBuyFlights = (Button) findViewById(R.id.buyFlights);
        buttonCreateBD = (Button) findViewById(R.id.createDB);


        press(false);
        buttonFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent na = new Intent(UserActivity.this, UserFlightsActivity.class);
                na.putExtra("user", user);
                finish();
                startActivity(na);
            }
        });


        buttonBuyFlights.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent na = new Intent(UserActivity.this, BuyFlightsActivity.class);
                na.putExtra("user", user);
                finish();
                startActivity(na);
            }
        });

        buttonCreateBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Flight> flights = new ArrayList<>();
                flights.add(new Flight("1111", "11:00", "16:00", "Tel-aviv", "new york"));
                flights.add(new Flight("2222", "12:00", "17:00", "Paris", "new york"));
                flights.add(new Flight("3333", "13:00", "18:00", "Tel-aviv", "London"));
                flights.add(new Flight("4444", "14:00", "19:00", "Madrid", "Tel-aviv"));

                for (Flight flight : flights) {
                    DataBaseHelper.getInstance().getDB().child("flights").child(flight.getNumFlight()).setValue(flight);
                }
            }
        });


        logedInUser = DataBaseHelper.getInstance().getmAuth().getCurrentUser();
        String uid = logedInUser.getUid();
        getUserFromDB(uid);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();

    }

    public void getLogInUser() {
        userName.setText("Hello " + user.getName());
        userPoints.setText("Your sum of points is: " + user.getPoints());
        press(true);
    }

    public void getUserFromDB(String uId) {
        DataBaseHelper.getInstance().getDB().child("users").child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    ArrayList<String> listFlights = new ArrayList<>();
                    user = dataSnapshot.getValue(User.class);
                    for (DataSnapshot snapChild : dataSnapshot.getChildren()) {
                        for (DataSnapshot snapGrandChild : snapChild.getChildren()) {
                            String flight = snapGrandChild.getValue(String.class);
                            listFlights.add(flight);
                        }
                    }
                    user.setListFlights(listFlights);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getLogInUser();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void press(boolean canPress) {
        buttonFlights.setEnabled(canPress);
        buttonCreateBD.setEnabled(canPress);
        buttonCreateBD.setEnabled(canPress);
    }

}
