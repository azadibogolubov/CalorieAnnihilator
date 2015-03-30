package com.tutorazadi.CalorieAnnihilator;

import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by azadi on 3/29/15.
 */
public class FoodListAdapter extends ArrayAdapter<FoodItem> {
    // declaring our ArrayList of items
    private ArrayList<FoodItem> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public FoodListAdapter(Context context, int textViewResourceId, ArrayList<FoodItem> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
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

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.name = (TextView) v.findViewById(R.id.name);
        viewHolder.name.setText(viewHolder.name.getText() + item.name);
        viewHolder.servingSize = (TextView) v.findViewById(R.id.servingSize);
        viewHolder.servingSize.setText(viewHolder.servingSize.getText() + item.servingSize);
        viewHolder.calories = (TextView) v.findViewById(R.id.calories);
        viewHolder.calories.setText(viewHolder.calories.getText() + item.calories);

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        FoodItem i = objects.get(position);

        return v;

    }

    static class ViewHolder
    {
        TextView name, calories, servingSize;
    }
}
