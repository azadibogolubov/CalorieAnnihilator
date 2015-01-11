package com.tutorazadi.CalorieAnnihilator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.*;

public class AvoidanceActivity extends Activity {
    private static String url;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems;
    private EditText searchTxt;
    int count = 0;

    JSONObject list = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avoidance);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listItems = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                String[] calories = ((((TextView) view).getText().toString()).replaceAll("[^0-9.]+", " ").trim()).split(" ");
                Toast.makeText(getApplicationContext(), "Calories: " + calories[1], Toast.LENGTH_SHORT).show();
            }
        });

        searchTxt = (EditText) findViewById(R.id.searchTxt);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String foodName = searchTxt.getText().toString();
                handleNameJSON(foodName);

                SQLiteDatabase mydatabase = openOrCreateDatabase("MainDB",MODE_PRIVATE,null);
                mydatabase.execSQL("UPDATE CalorieAnnihilator SET Calories=125.25, Sugar=500.00 WHERE Username='azadi'");

                Cursor resultSet = mydatabase.rawQuery("SELECT * FROM CalorieAnnihilator",null);
                resultSet.moveToFirst();

                String username = resultSet.getString(0);
                float calories = resultSet.getFloat(1);
                float sugar = resultSet.getFloat(2);
                Toast.makeText(AvoidanceActivity.this, "Username: " + username + "\nCalories: " + calories + "\nSugar: " + sugar + "\n", Toast.LENGTH_LONG).show();
            }
        });
    }


    // Get the serving size and calorie information.
    public String handleNutritionJSON(String ndbNo)
    {
        String nutritionUrl = "http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&ndbno=" + ndbNo + "&max=1";
        String servingSize = null, calories = null;
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(nutritionUrl);
        try
        {
            JSONObject report = json.getJSONObject("report");
            servingSize = report.getJSONArray("foods").getJSONObject(0).getString("measure");
            calories = report.getJSONArray("foods").getJSONObject(0).getJSONArray("nutrients").getJSONObject(0).getString("value") + " calories";
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return servingSize + "\n" + calories;
    }

    public String handleNameJSON(String foodName)
    {
        // Sanitize spaces into %20 for the purposes of URL encoding.
        foodName = foodName.replace(" ", "%20");

        url = "http://api.data.gov/usda/ndb/search/?format=json&q=" + foodName + "&max=25&offset=0&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf";
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(url);
        try
        {
            list = json.getJSONObject("list");
            count = list.getInt("end");
            JSONArray item = list.getJSONArray("item");
            JSONObject name;
            String nameObj;
            for (int i = 0; i < count; i++)
            {
                name = item.getJSONObject(i);
                nameObj = name.getString("name");
                String ndbNo = name.getString("ndbno");
                String nutritionInfo = handleNutritionJSON(ndbNo);
                listItems.add(nameObj + "\n" + nutritionInfo);
            }
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e)
        {
            Toast.makeText(this, "Food not found. Maybe there was a misspelling.", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}