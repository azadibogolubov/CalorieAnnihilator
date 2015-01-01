package com.tutorazadi.CalorieAnnihilator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.*;

public class AvoidanceActivity extends Activity
{
    private static String url = "http://api.data.gov/usda/ndb/search/?format=json&q=papa%20johns&max=25&offset=0&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems;

    JSONObject list = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avoidance);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listItems = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleJSON();
            }
        });
    }

    public void handleJSON()
    {
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(url);
        try
        {
            list = json.getJSONObject("list");
            JSONArray item = list.getJSONArray("item");
            JSONObject name = item.getJSONObject(0);
            String nameObj = name.getString("name");
            listItems.add(nameObj);
            name = item.getJSONObject(1);
            nameObj = name.getString("name");
            listItems.add(nameObj);
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}