package com.ordertaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button submit;
    Spinner meatsSpinner, cheeseSpinner, condimentsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupControls();
        setButtonListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupControls()
    {
        meatsSpinner = (Spinner) findViewById(R.id.meats);
        cheeseSpinner = (Spinner) findViewById(R.id.cheeses);
        condimentsSpinner = (Spinner) findViewById(R.id.condiments);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.meats, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        meatsSpinner.setAdapter(adapter1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.cheeses, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        cheeseSpinner.setAdapter(adapter2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.condiments, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        condimentsSpinner.setAdapter(adapter3);

        submit = (Button) findViewById(R.id.submit);
    }

    public void setButtonListener()
    {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> chosenItems = new ArrayList<>();
                chosenItems.add(meatsSpinner.getSelectedItem().toString());
                chosenItems.add(cheeseSpinner.getSelectedItem().toString());
                chosenItems.add(condimentsSpinner.getSelectedItem().toString());
                //Toast.makeText(MainActivity.this, "Items: " + chosenItems.get(0) + "\n" + chosenItems.get(1) + "\n" + chosenItems.get(2), Toast.LENGTH_LONG).show();
                Intent goToConfirmation = new Intent(MainActivity.this, ConfirmationActivity.class);
                goToConfirmation.putStringArrayListExtra("ITEMS", chosenItems);
                startActivity(goToConfirmation);
            }
        });
    }
}
