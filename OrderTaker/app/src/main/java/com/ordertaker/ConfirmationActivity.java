package com.ordertaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConfirmationActivity extends AppCompatActivity {

    TextView meats, cheeses, condiments;
    ArrayList<String> chosenItems;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        initializeControls();
        boolean success = getItemsFromBundle();
        if (success)
        {
            addItemsToTextViews();
            setListeners();
        }
        else
        {
            // Finish the app, for purposes of time, otherwise would handle gracefully.
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);
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

    public void initializeControls()
    {
        meats = (TextView) findViewById(R.id.meats);
        cheeses = (TextView) findViewById(R.id.cheeses);
        condiments = (TextView) findViewById(R.id.condiments);
        confirm = (Button) findViewById(R.id.confirm);
    }

    public boolean getItemsFromBundle()
    {
        chosenItems = this.getIntent().getStringArrayListExtra("ITEMS");
        if (chosenItems.size() == 3)
            return true;
        else
            return false;
    }

    /**
     * Add the items selected from previous activity to the current textviews where appropriate.
     */
    public void addItemsToTextViews()
    {
        meats.setText(meats.getText() + chosenItems.get(0));
        cheeses.setText(cheeses.getText() + chosenItems.get(1));
        condiments.setText(condiments.getText() + chosenItems.get(2));
    }

    public void setListeners()
    {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmationActivity.this, "Your order has been submitted and will be ready in 30 minutes.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
