package com.example.user.myapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class DataBaseHelper implements Serializable {
    private FirebaseDatabase databaseGreenSky;
    private DatabaseReference DB;
    private FirebaseAuth mAuth;
    private static DataBaseHelper db;

    public static DataBaseHelper getInstance(){
        if (db==null)
                db= new DataBaseHelper();
        return db;
    }

    private DataBaseHelper(){
        databaseGreenSky = FirebaseDatabase.getInstance();
        DB = databaseGreenSky.getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseDatabase getDatabaseGreenSky() {
        return databaseGreenSky;
    }

    public DatabaseReference getDB() {
        return DB;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
