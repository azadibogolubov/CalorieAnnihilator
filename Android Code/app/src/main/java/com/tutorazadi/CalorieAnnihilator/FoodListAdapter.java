package com.tutorazadi.CalorieAnnihilator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Window;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by azadi on 3/29/15.
 */
public class FoodListAdapter extends ArrayAdapter<FoodItem> {
    // declaring our ArrayList of items
    private ArrayList<FoodItem> objects;
    Context context;
    DataSource datasource;
    String[] calories;
    String[] sugar;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public FoodListAdapter(Context context, int textViewResourceId, ArrayList<FoodItem> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        this.context = context;
        datasource = new DataSource(context);
        datasource.open();
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.food_item, null);
        }

        FoodItem item = objects.get(position);

        final ViewHolder viewHolder = new ViewHolder();

        viewHolder.mainLayout = (LinearLayout) v.findViewById(R.id.mainLayout);
        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calories = ((viewHolder.calories.getText().toString()).replaceAll("[^0-9.]+", " ").trim()).split(" ");
                sugar = ((viewHolder.sugar.getText().toString()).replaceAll("[^0-9.]+", " ").trim()).split(" ");

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.gray_gradient);

                dialog.setContentView(R.layout.dialog_quantity);

                final EditText quantityTxt = (EditText) dialog.findViewById(R.id.quantityTxt);

                Button confirmBtn, cancelBtn;
                confirmBtn = (Button) dialog.findViewById(R.id.confirmBtn);
                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float quantity = Float.parseFloat(quantityTxt.getText().toString());
                        if (context.toString().contains("Binge"))
                            datasource.createEntry(-quantity * Float.parseFloat(calories[0]), (-quantity * Float.parseFloat(sugar[0]) / 454.4f));
                        else if (context.toString().contains("Avoidance"))
                            datasource.createEntry(quantity * Float.parseFloat(calories[0]), (quantity * Float.parseFloat(sugar[0]) / 454.4f));
                        ((Activity)getContext()).finish();
                        dialog.dismiss();
                    }
                });

                cancelBtn = (Button) dialog.findViewById(R.id.cancelBtn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        viewHolder.name = (TextView) v.findViewById(R.id.name);
        viewHolder.name.setText("Name: " + item.name);
        viewHolder.servingSize = (TextView) v.findViewById(R.id.servingSize);
        viewHolder.servingSize.setText("Serving Size: " + item.servingSize);
        viewHolder.calories = (TextView) v.findViewById(R.id.calories);
        viewHolder.calories.setText("Calories: " + item.calories);
        viewHolder.sugar = (TextView) v.findViewById(R.id.sugar);
        viewHolder.sugar.setText("Grams of sugar: " + item.sugar);

        if (item.name.equals("No items found"))
        {
            viewHolder.name.setVisibility(View.GONE);
            viewHolder.servingSize.setVisibility(View.GONE);
            viewHolder.calories.setVisibility(View.GONE);
            new AlertDialog.Builder(context)
                    .setMessage("No matching entries. Are you sure you spelled it correctly?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else if (item.name.equals("Network error"))
        {
            viewHolder.name.setVisibility(View.GONE);
            viewHolder.servingSize.setVisibility(View.GONE);
            viewHolder.calories.setVisibility(View.GONE);
            new AlertDialog.Builder(context)
                    .setMessage("Network error. Please try again later.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        return v;
    }

    static class ViewHolder
    {
        LinearLayout mainLayout;
        TextView name, calories, servingSize, sugar;
    }
}
