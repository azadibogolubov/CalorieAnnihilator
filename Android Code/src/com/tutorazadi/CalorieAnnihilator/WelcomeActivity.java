package com.tutorazadi.CalorieAnnihilator;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class WelcomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    Button avoidanceBtn, bingeBtn;
    TextView caloriesAvoided, lbsOfSugarAvoided;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        avoidanceBtn = (Button) findViewById(R.id.avoidanceBtn);
        avoidanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avoidance = new Intent(WelcomeActivity.this, AvoidanceActivity.class);
                WelcomeActivity.this.startActivity(avoidance);
            }
        });
        bingeBtn = (Button) findViewById(R.id.bingeBtn);
        bingeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent binge = new Intent(WelcomeActivity.this, BingeActivity.class);
                WelcomeActivity.this.startActivity(binge);

        caloriesAvoided = (TextView) findViewById(R.id.caloriesAvoided);
        lbsOfSugarAvoided = (TextView) findViewById(R.id.lbsOfSugarAvoided);
            }
        });
    }
}
