package com.tutorazadi.CalorieAnnihilator;

/**
 * Created by azadi on 3/29/15.
 */
public class FoodItem {
    String name;
    String servingSize;
    String calories;
    String protein;
    String fat;
    String sugar;

    public FoodItem(String name, String servingSize, String calories, String protein, String fat, String sugar)
    {
        this.name = name;
        this.servingSize = servingSize;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
    }

}
