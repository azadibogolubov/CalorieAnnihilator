package com.tutorazadi.CalorieAnnihilator;

import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

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
        searchTxt = (EditText) findViewById(R.id.searchTxt);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String foodName = searchTxt.getText().toString();
                handleJSON(foodName);
            }
        });
    }

    public void handleJSON(String foodName)
    {
        String name = handleNameJSON(foodName);
        String nutrition = handleNutritionJSON();
    }

    public String handleNutritionJSON()
    {
        return "";
    }

    public String handleNameJSON(String foodName)
    {
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
                listItems.add(nameObj);
            }
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }

}