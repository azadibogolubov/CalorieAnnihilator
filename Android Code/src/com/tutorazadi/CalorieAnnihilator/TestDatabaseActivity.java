package com.tutorazadi.CalorieAnnihilator;

import java.util.List;
import java.util.Random;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

// Taken from http://www.vogella.com/tutorials/AndroidSQLite/article.html
public class TestDatabaseActivity extends ListActivity
{
    private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datasource);

        datasource = new DataSource(this);
        datasource.open();

        List<Calories> values = datasource.getAllEntries();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Calories> adapter = new ArrayAdapter<Calories>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view)
    {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Calories> adapter = (ArrayAdapter<Calories>) getListAdapter();
        Calories calories = null;
        switch (view.getId())
        {
            case R.id.add:
                float[] amounts = new float[]{5.0f, 150.00f, 300.10f};
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                calories = datasource.createEntry(amounts[nextInt]);
                adapter.add(calories);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0)
                {
                    calories = (Calories) getListAdapter().getItem(0);
                    datasource.deleteEntry(calories);
                    adapter.remove(calories);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume()
    {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        datasource.close();
        super.onPause();
    }
}