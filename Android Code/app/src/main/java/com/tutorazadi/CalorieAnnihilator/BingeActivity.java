package com.tutorazadi.CalorieAnnihilator;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azadi on 12/13/14.
 */
public class BingeActivity extends Activity
{
    private ListView listView;
    private FoodListAdapter adapter;
    private EditText searchTxt;
    private ArrayList<FoodItem> listItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binge);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listItems = new ArrayList<>();
        adapter = new FoodListAdapter(getApplicationContext(), R.layout.food_item, listItems);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                String[] calories = ((((TextView) view).getText().toString()).replaceAll("[^0-9.]+", " ").trim()).split(" ");
                SQLiteDatabase mydatabase = openOrCreateDatabase("MainDB",MODE_PRIVATE,null);
                Cursor resultSet = mydatabase.rawQuery("SELECT * FROM CalorieAnnihilator WHERE Username='azadi'",null);
                resultSet.moveToFirst();
                if (resultSet.getCount() > 0)
                    mydatabase.execSQL("UPDATE CalorieAnnihilator SET Calories=" + calories + ", Sugar=0.0 WHERE Username='azadi");
            }
        });

        searchTxt = (EditText) findViewById(R.id.searchTxt);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String foodName = searchTxt.getText().toString();

                listItems = new JSONOperations().getResults(foodName);
                adapter = new FoodListAdapter(getApplicationContext(), R.layout.food_item, listItems);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}