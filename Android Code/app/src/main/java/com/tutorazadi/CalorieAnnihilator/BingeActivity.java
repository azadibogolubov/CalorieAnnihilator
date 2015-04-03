package com.tutorazadi.CalorieAnnihilator;

import android.os.AsyncTask;
import android.widget.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
                Toast.makeText(BingeActivity.this, "Searching...This could take a moment.", Toast.LENGTH_SHORT).show();
                listItems.clear();
                adapter.notifyDataSetChanged();
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

