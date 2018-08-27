package com.example.user.myapplication;


import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {
    private String name;
    private long points;
    private ArrayList<String> listFlights = new ArrayList<>();


    public User() {
    }

    public User(String name) {
        setName(name);
        setPoints(0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public void setListFlights(ArrayList<String> listFlights) {
        this.listFlights = listFlights;
    }


    public ArrayList<String> getListFlights() {
        return this.listFlights;
    }

    public String getName() {
        return this.name;
    }

    public long getPoints() {
        return this.points;
    }

}

