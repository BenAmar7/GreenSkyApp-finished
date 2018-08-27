package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FlightInfoAndWeatherActivity extends AppCompatActivity {

    private static final String TAG = "FlightInfoAndWeatherAct";

    private TextView departureTime, arrivalTime, departureAirPort, arrivalAirPort;
    private Button btnNavToWeatherActivity, goBackAct;
    private Flight flight;
    private User logedInUser;

    private void init() {
        flight = (Flight) getIntent().getSerializableExtra("flight");
        logedInUser= (User) getIntent().getSerializableExtra("logedInUser");
        btnNavToWeatherActivity = (Button) findViewById(R.id.btnGoToWeatherActivity);
        goBackAct = (Button) findViewById(R.id.btnGoBackAct);
        departureTime = (TextView) findViewById(R.id.DepartureTimeTextView);
        arrivalTime = (TextView) findViewById(R.id.ArrivalTimeTimeTextView);
        departureAirPort = (TextView) findViewById(R.id.departureAirPortNameTextView);
        arrivalAirPort = (TextView) findViewById(R.id.arrivalAirPortTextTextView);

        btnNavToWeatherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked btnToGoToWeatherActivity");

                Intent intent = new Intent(FlightInfoAndWeatherActivity.this, WeatherAcvtivity.class);
                intent.putExtra("destination",flight.getWhereTo());
                startActivity(intent);
            }
        });

        goBackAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked goBackAct");
                Intent intent = new Intent(FlightInfoAndWeatherActivity.this, InformationActivity.class);
                intent.putExtra("logedInUser", logedInUser);
                intent.putExtra("flight", flight);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_info_and_weather);

        init();
        Log.d(TAG, "onCreate: Starting.");

        departureTime.setText(flight.getTakeOff());
        arrivalTime.setText(flight.getLanding());
        departureAirPort.setText(flight.getFrom());
        arrivalAirPort.setText(flight.getWhereTo());




    }

}
