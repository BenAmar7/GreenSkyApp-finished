package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MealsActivity extends AppCompatActivity {
    private User logedInUser;
    private Flight flight;
    private ArrayList<String> selection;
    private ArrayList<CheckBox> checkBoxList;
    private String uId;

    private void init() {
        logedInUser = (User) getIntent().getSerializableExtra("logedInUser");
        flight = (Flight) getIntent().getSerializableExtra("flight");
        selection = new ArrayList<String>();
        checkBoxList = new ArrayList<CheckBox>();
        createCheckBoxList();
        uId = DataBaseHelper.getInstance().getmAuth().getCurrentUser().getUid();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        init();
    }

    private void calculateMealPoints() {
        switch (selection.size()) {
            case 0:
                updatePoints(3);
                break;
            case 1:
                updatePoints(2);
                break;
            case 2:
                updatePoints(1);
                break;
            case 3:
                updatePoints(0);
                break;
        }
    }

    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.meat:
                if (checked) {
                    selection.add("Meat");
                } else {
                    selection.remove("Meat");
                }
                break;
            case R.id.meatballs:
                if (checked) {
                    selection.add("Meatballs");
                } else {
                    selection.remove("Meatballs");
                }
                break;


            case R.id.chicken:
                if (checked) {
                    selection.add("Chicken");
                } else {
                    selection.remove("Chicken");
                }
                break;

            case R.id.egg:
                if (checked) {
                    selection.add("Egg");
                } else {
                    selection.remove("Egg");
                }
                break;

            case R.id.fish:
                if (checked) {
                    selection.add("Fish");
                } else {
                    selection.remove("Fish");
                }
                break;

            case R.id.toast:
                if (checked) {
                    selection.add("Toast");
                } else {
                    selection.remove("Toast");
                }
                break;

            case R.id.tofu:
                if (checked) {
                    selection.add("Tofu");
                } else {
                    selection.remove("Tofu");
                }
                break;


            case R.id.lentil_patties:
                if (checked) {
                    selection.add("Lentil patties");
                } else {
                    selection.remove("Lentil patties");
                }
                break;


            case R.id.eggplant_in_tehina:
                if (checked) {
                    selection.add("Eggplant in Tehina");
                } else {
                    selection.remove("Eggplant in Tehina");
                }
                break;

            case R.id.vegetable_Salad:
                if (checked) {
                    selection.add("Vegetable Salad");
                } else {
                    selection.remove("Vegetable Salad");
                }
                break;

            case R.id.rice:
                if (checked) {
                    selection.add("Rice");
                } else {
                    selection.remove("Rice");
                }
                break;

            case R.id.puree:
                if (checked) {
                    selection.add("Puree");
                } else {
                    selection.remove("Puree");
                }
                break;

            case R.id.bean:
                if (checked) {
                    selection.add("Bean");
                } else {
                    selection.remove("Bean");
                }
                break;
        }
        checkAndLock();
    }

    public void finalSelection(View view) {
        String final_food_selection = "";
        for (String Selection : selection) {
            final_food_selection = final_food_selection + Selection + "\n";
        }
        calculateMealPoints();
    }

    private void createCheckBoxList() {
        checkBoxList.add((CheckBox) findViewById(R.id.meat));
        checkBoxList.add((CheckBox) findViewById(R.id.meatballs));
        checkBoxList.add((CheckBox) findViewById(R.id.chicken));
        checkBoxList.add((CheckBox) findViewById(R.id.egg));
        checkBoxList.add((CheckBox) findViewById(R.id.fish));
        checkBoxList.add((CheckBox) findViewById(R.id.toast));
        checkBoxList.add((CheckBox) findViewById(R.id.tofu));
        checkBoxList.add((CheckBox) findViewById(R.id.lentil_patties));
        checkBoxList.add((CheckBox) findViewById(R.id.eggplant_in_tehina));
        checkBoxList.add((CheckBox) findViewById(R.id.vegetable_Salad));
        checkBoxList.add((CheckBox) findViewById(R.id.rice));
        checkBoxList.add((CheckBox) findViewById(R.id.puree));
        checkBoxList.add((CheckBox) findViewById(R.id.bean));
    }

    public void checkAndLock() {
        if (selection.size() >= 3) {
            for (CheckBox box : checkBoxList) {
                if (!selection.contains(box.getText()))
                    box.setEnabled(false);
            }
        } else {
            for (CheckBox box : checkBoxList) {
                box.setEnabled(true);
            }
        }
    }

    public void updatePoints(long points) {
        flight.getPassengersList().put(uId, points);
        logedInUser.setPoints(logedInUser.getPoints() + points);
        DataBaseHelper.getInstance().getDB().child("users").child(uId).setValue(logedInUser);
        DataBaseHelper.getInstance().getDB().child("flights").child(flight.getNumFlight()).setValue(flight);
        Intent na = new Intent(MealsActivity.this, UserFlightsActivity.class);
        na.putExtra("user", logedInUser);
        na.putExtra("flight", flight);
        finish();
        startActivity(na);
    }
}
