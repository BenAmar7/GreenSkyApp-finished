package com.example.user.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public Button regButton, logInButton;
    private EditText textName, textPassword;
    private String name, password;

    public void init() {
        regButton = (Button) findViewById(R.id.regButton);
        logInButton = (Button) findViewById(R.id.logInButton);
        textName = (EditText) findViewById(R.id.logInUserName);
        textPassword = (EditText) findViewById(R.id.logInPassword);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rb = new Intent(LoginActivity.this, RegisterActivity.class);
                finish();
                startActivity(rb);
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }


    public void moveToNextActivity(FirebaseUser user) {
        Intent na = new Intent(LoginActivity.this, UserActivity.class);
        finish();
        startActivity(na);
    }

    public void signIn() {
        name = textName.getText().toString();
        password = textPassword.getText().toString();

        DataBaseHelper.getInstance().getmAuth().signInWithEmailAndPassword(name, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = DataBaseHelper.getInstance().getmAuth().getCurrentUser();
                            moveToNextActivity(user);

                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }

}
