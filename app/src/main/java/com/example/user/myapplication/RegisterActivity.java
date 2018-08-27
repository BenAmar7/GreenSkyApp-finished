package com.example.user.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private User user;
    private static long userId = 1;
    private Button finalReg;
    private boolean regComplete;
    private EditText textEMail, textPassword, textVerifyPassword, textName;
    private String name, password, verifyPassword;
    //private DataBaseHelper dbHelper;
    //private FirebaseDatabase databaseGreenSky;
    //private DatabaseReference usersDB;
    //private FirebaseAuth mAuth;

    public void init() {
        finalReg = (Button) findViewById(R.id.finalReg);
        textEMail = (EditText) findViewById(R.id.eMail);
        textPassword = (EditText) findViewById(R.id.password);
        textVerifyPassword = (EditText) findViewById(R.id.verifyPassword);
        textName = (EditText) findViewById(R.id.textName);

        //dbHelper = new DataBaseHelper();
        //databaseGreenSky = FirebaseDatabase.getInstance();
        //usersDB = databaseGreenSky.getReference();
        //mAuth = FirebaseAuth.getInstance();
        finalReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
*/
    private void registerUser() {
        String email = textEMail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        name = textName.getText().toString().trim();

        verifyPassword = textVerifyPassword.getText().toString();

        if (TextUtils.isEmpty((email))) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty((password))) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(verifyPassword)){
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }
        DataBaseHelper.getInstance().getmAuth().createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = DataBaseHelper.getInstance().getmAuth().getCurrentUser();
                            String uId = user.getUid();
                            User newUser = new User(name);
                            DataBaseHelper.getInstance().getDB().child("users").child(uId).setValue(newUser);
                            finish();
                            Intent fb = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(fb);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registered Faild, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }

/*
    protected void checkingRegistration() {
        name = textEMail.getText().toString();
        password = textPassword.getText().toString();
        verifyPassword = textVerifyPassword.getText().toString();

        if (password.equals(verifyPassword)) {
            regComplete = true;
            user = new User(name, password, userId);
            //usersDB.child("users").child(String.valueOf(user.getId()))push().setValue(user);
            userId++;
            Toast.makeText(this, "Signup completed", Toast.LENGTH_SHORT).show();

        } else {
            regComplete = false;
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }
        if (regComplete) {
            try {
                Intent fb = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(fb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/


}
