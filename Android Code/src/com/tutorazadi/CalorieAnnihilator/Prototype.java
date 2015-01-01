package com.tutorazadi.CalorieAnnihilator;

public class Prototype
{
	public static void main(String[] args)
	{
		AccessorFunctions instance = new AccessorFunctions();
		instance.addFood("Pizza", "Meal", 300, 50);
		instance.addFood("Mountain Dew", "Beverage", 190, 336);
		instance.listAllFoods();	
	}
}

