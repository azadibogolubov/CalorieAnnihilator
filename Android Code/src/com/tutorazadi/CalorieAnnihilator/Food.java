package com.tutorazadi.CalorieAnnihilator;

class Food
{
	/*
	 * Every food will have three components.
	 * 1) A name
	 * 2) The number of calories per serving.
     * 3) Serving size (in grams).
     */
	private String name;
	private String category;
	private int caloriesPerServing;
	private int gramsPerServing;

	public Food(String foodName, String foodCategory,  int numCalories, int numGrams)
	{
		name = foodName;
		category = foodCategory;
		caloriesPerServing = numCalories;
		gramsPerServing = numGrams;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String value)
	{
		name = value;
	}

	public int getCaloriesPerServing()
	{
		return caloriesPerServing;
	}
	
	public int getGramsPerServing()
	{
		return gramsPerServing;
	}
}
