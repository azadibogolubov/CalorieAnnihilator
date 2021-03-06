package com.tutorazadi.CalorieAnnihilator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.graphics.Typeface;

import java.util.List;

public class MainActivity extends Activity {

    Button avoidanceBtn, bingeBtn, aboutBtn, exerciseBtn;
    TextView caloriesAvoided, lbsOfSugarAvoided, welcome, pleaseChoose;
    float calories = 0.0f, sugar = 0.0f;
    private DataSource datasource;
    Typeface arimo, arimoItalic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arimo = Typeface.createFromAsset(getAssets(), "fonts/Arimo-Regular.ttf");;
        arimoItalic = Typeface.createFromAsset(getAssets(), "fonts/Arimo-Italic.ttf");;

        datasource = new DataSource(this);
        datasource.open();

        List<Calories> entries = datasource.getAllEntries();
        for (Calories c: entries) {
            calories += c.getCalories();
            sugar += c.getSugar();
        }

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setTypeface(arimo);

        pleaseChoose = (TextView) findViewById(R.id.pleaseChoose);
        pleaseChoose.setTypeface(arimo);

        caloriesAvoided = (TextView) findViewById(R.id.caloriesAvoided);
        caloriesAvoided.setText("Calories avoided: " + calories);
        caloriesAvoided.setTypeface(arimo);

        lbsOfSugarAvoided = (TextView) findViewById(R.id.lbsOfSugarAvoided);
        lbsOfSugarAvoided.setText("Lbs of sugar avoided: " + sugar);
        lbsOfSugarAvoided.setTypeface(arimo);

        avoidanceBtn = (Button) findViewById(R.id.avoidanceBtn);
        avoidanceBtn.setTypeface(arimoItalic);
        avoidanceBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent avoidance = new Intent(MainActivity.this, AvoidanceActivity.class);
                startActivity(avoidance);
            }
        });

        bingeBtn = (Button) findViewById(R.id.bingeBtn);
        bingeBtn.setTypeface(arimoItalic);
        bingeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caloriesAvoided = (TextView) findViewById(R.id.caloriesAvoided);
                lbsOfSugarAvoided = (TextView) findViewById(R.id.lbsOfSugarAvoided);

                Intent binge = new Intent(MainActivity.this, BingeActivity.class);
                startActivity(binge);
            }
        });

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setTypeface(arimoItalic);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about);
            }
        });

        exerciseBtn = (Button) findViewById(R.id.exerciseBtn);
        exerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exercise = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(exercise);
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        calories = 0.0f;
        sugar = 0.0f;

        datasource = new DataSource(this);
        datasource.open();

        List<Calories> entries = datasource.getAllEntries();
        for (Calories c: entries) {
            calories += c.getCalories();
            sugar += c.getSugar();
        }

        caloriesAvoided = (TextView) findViewById(R.id.caloriesAvoided);
        caloriesAvoided.setText("Calories avoided: " + calories);
        caloriesAvoided.setTypeface(arimo);

        lbsOfSugarAvoided = (TextView) findViewById(R.id.lbsOfSugarAvoided);
        lbsOfSugarAvoided.setText("Lbs of sugar avoided: " + sugar);
        lbsOfSugarAvoided.setTypeface(arimo);
    }
}
