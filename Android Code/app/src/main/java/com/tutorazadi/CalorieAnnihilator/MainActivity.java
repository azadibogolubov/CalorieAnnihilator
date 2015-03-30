package com.tutorazadi.CalorieAnnihilator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.graphics.Typeface;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    Button avoidanceBtn, bingeBtn;
    TextView caloriesAvoided, lbsOfSugarAvoided, welcome, pleaseChoose;
    String username;
    float calories, sugar;
    SQLiteDatabase mydatabase;
    Cursor resultSet;
    Typeface arimo, arimoItalic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arimo = Typeface.createFromAsset(getAssets(), "fonts/Arimo-Regular.ttf");;
        arimoItalic = Typeface.createFromAsset(getAssets(), "fonts/Arimo-Italic.ttf");;

        mydatabase = openOrCreateDatabase("MainDB",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS CalorieAnnihilator(Username VARCHAR, Calories DECIMAL(10,2), Sugar DECIMAL(10, 2));");

        resultSet = mydatabase.rawQuery("SELECT * FROM CalorieAnnihilator",null);
        resultSet.moveToFirst();

        if (resultSet.getCount() > 0)
        {
            username = resultSet.getString(0);
            calories = resultSet.getFloat(1);
            sugar = resultSet.getFloat(2);
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
                MainActivity.this.startActivity(avoidance);
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
                MainActivity.this.startActivity(binge);
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mydatabase = openOrCreateDatabase("MainDB",MODE_PRIVATE,null);
        resultSet = mydatabase.rawQuery("SELECT * FROM CalorieAnnihilator",null);
        resultSet.moveToFirst();

        if (resultSet.getCount() > 0)
        {
            username = resultSet.getString(0);
            calories = resultSet.getFloat(1);
            sugar = resultSet.getFloat(2);
        }
        caloriesAvoided = (TextView) findViewById(R.id.caloriesAvoided);
        caloriesAvoided.setText("Calories avoided: " + calories);

        lbsOfSugarAvoided = (TextView) findViewById(R.id.lbsOfSugarAvoided);
        lbsOfSugarAvoided.setText("Lbs of sugar avoided: " + sugar);
    }
}
