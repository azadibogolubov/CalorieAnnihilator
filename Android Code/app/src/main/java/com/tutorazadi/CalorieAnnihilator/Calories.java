package com.tutorazadi.CalorieAnnihilator;

public class Calories
{
    private long id;
    private float calories;
    private float sugar;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public float getCalories()
    {
        return calories;
    }

    public void setCalories(float calories)
    {
        this.calories = calories;
    }

    public float getSugar()
    {
        return sugar;
    }

    public void setSugar(float sugar)
    {
        this.sugar = sugar;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString()
    {
        return "Calories: " + calories + "\nSugar: " + sugar;
    }
}