package com.tutorazadi.CalorieAnnihilator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class BingeActivity extends Activity {
    private ListView listView;
    private FoodListAdapter adapter;
    private EditText searchTxt;
    private ArrayList<FoodItem> listItems;
    private String foodName;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binge);

        listItems = new ArrayList<>();
        adapter = new FoodListAdapter(this, R.layout.food_item, listItems);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        searchTxt = (EditText) findViewById(R.id.searchTxt);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                foodName = searchTxt.getText().toString();
                new FetchJSONResults().execute();
            }
        });
    }

    public class FetchJSONResults extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            listItems.addAll(new JSONOperations().getResults(foodName));
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            adapter.notifyDataSetChanged();
        }
    }
}

