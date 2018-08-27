package com.example.user.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Meal implements Serializable {
    private boolean vegetarian, meat, vegan;
    private ArrayList<Boolean> meal = new ArrayList<>();
    private int points;

    public Meal() {
    }

    public Meal(boolean vegetarian, boolean meat, boolean vegan) {
        this.setVegetarian(vegetarian);
        this.setMeat(meat);
        this.setVegan(vegan);
        //Boolean[] boolArray={meat, vegetarian, vegan};
        //this.setMeal((ArrayList<Boolean>) Arrays.asList(boolArray));
        this.setPoints(0);
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Boolean> getMeal() {
        return meal;
    }

    public void setMeal(ArrayList<Boolean> meal) {
        this.meal = meal;
    }
}
