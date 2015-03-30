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

public class AvoidanceActivity extends Activity {
    private static String url;
    private ListView listView;
    private FoodListAdapter adapter;
    private EditText searchTxt;
    private ArrayList<FoodItem> listItems;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avoidance);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listItems = new ArrayList<>();
        adapter = new FoodListAdapter(getApplicationContext(), R.layout.food_item, listItems);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
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

                listItems = getResults(foodName);
                adapter = new FoodListAdapter(getApplicationContext(), R.layout.food_item, listItems);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public ArrayList<FoodItem> getResults(String request) {
        request = sanitize(request);

        ArrayList<FoodItem> results = new ArrayList<>();
        JSONParser parser = new JSONParser();

        for (int i = 0; i < 20; i++)
        {
            try {
                String name = issueGetRequest("http://api.data.gov/usda/ndb/search/?format=json&q=" + request + "&max=25&offset=0&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf");
                Object parsedName = parser.parse(name);
                Object foodName = getName(parsedName, i);
                Object ndbno = getNDB(parsedName, i);

                Object parsedData = parser.parse(issueGetRequest("http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&ndbno=" + ndbno + "&max=1"));
                Object servingSize = getServingSize(parsedData, 0);
                Object calories = getCalories(parsedData, 0);

                results.add(new FoodItem(foodName.toString(), servingSize.toString(), calories.toString()));
            }
            catch (ParseException | IOException | IndexOutOfBoundsException e) {
                if (e instanceof ParseException)
                    System.out.println("Error parsing JSON");
                else if (e instanceof IOException)
                    System.out.println("Error during GET request.");
                else if (e instanceof IndexOutOfBoundsException)
                    break;
            }
        }
        return results;
    }

    public String sanitize(String input)
    {
        input = input.replace(" ", "%20");
        return input;
    }

    public static String issueGetRequest(String urlQuery) throws IOException {
        URL url = new URL(urlQuery);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() != 200)
            throw new IOException(connection.getResponseMessage());

        // Buffer the result into a string
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        connection.disconnect();
        return sb.toString();
    }

    public static Object getServingSize(Object input, int index) {
        Object reportData = ((JSONObject) input).get("report");
        Object foodsData = ((JSONObject) reportData).get("foods");
        Object foodsArray = ((JSONArray) foodsData).get(index);
        Object servingSize = ((JSONObject) foodsArray).get("measure");
        return servingSize;
    }

    public static Object getCalories(Object input, int index) {
        Object reportData = ((JSONObject) input).get("report");
        Object foodsData = ((JSONObject) reportData).get("foods");
        Object foodsArray = ((JSONArray) foodsData).get(index);
        Object nutrientsData = ((JSONObject) foodsArray).get("nutrients");
        Object nutrientsArray = ((JSONArray) nutrientsData).get(index);
        Object value = ((JSONObject) nutrientsArray).get("value");
        return value;
    }

    public static Object getName(Object input, int index) {
        Object listData = ((JSONObject) input).get("list");
        Object itemData = ((JSONObject) listData).get("item");
        Object itemArray = ((JSONArray) itemData).get(index);
        Object name = ((JSONObject) itemArray).get("name");
        return name;
    }

    public static Object getNDB(Object input, int index) {
        Object listData = ((JSONObject) input).get("list");
        Object itemData = ((JSONObject) listData).get("item");
        Object itemArray = ((JSONArray) itemData).get(index);
        Object ndb = ((JSONObject) itemArray).get("ndbno");
        return ndb;
    }
}

