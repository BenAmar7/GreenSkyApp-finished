package com.example.user.myapplication;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Flight implements Serializable {
    private String numFlight, takeOff, Landing, from, whereTo;
    private Map<String, Long> passengersList = new HashMap<>();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(String whereTo) {
        this.whereTo = whereTo;
    }

    public Flight() {    }

    public Flight(String numFlight, String takeOff, String landing,String from, String whereTo) {
        this.numFlight = numFlight;
        this.takeOff = takeOff;
        this.Landing = landing;
        this.from=from;
        this.whereTo = whereTo;
    }

    public void addUserToList(String user) {
        this.passengersList.put(user, (long) 0);
        DataBaseHelper.getInstance().getDB().child("flights").child(this.getNumFlight()).setValue(this);
    }

    @Override
    public String toString() {
        return getNumFlight();
    }

    public Map<String, Long> getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(Map<String, Long> passengersList) {
        this.passengersList = passengersList;
    }

    public String getNumFlight() {
        return numFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(String takeOff) {
        this.takeOff = takeOff;
    }

    public String getLanding() {
        return Landing;
    }

    public void setLanding(String landing) {
        Landing = landing;
    }
}
