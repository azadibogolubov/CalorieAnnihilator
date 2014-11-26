import java.util.List;
import java.util.ArrayList;

class Food
{
	/*
	 * Every food will have three components.
	 * 1) A name
	 * 2) The number of calories per serving.
         * 3) Serving size (in grams).
         */
	private String name;
	private int numCaloriesPerServing;
	private int numGramsPerServing;

	public Food(String foodName, int numCalories, int numGrams)
	{
		name = foodName;
		numCaloriesPerServing = numCalories;
		numGramsPerServing = numGrams;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String value)
	{
		name = value;
	}
}

class AccessorFunctions
{
	private int totalCalories;
	private List<Food> foodsList;

	public AccessorFunctions()
	{
		totalCalories = 0;
		foodsList = new ArrayList<Food>();
	}

	public float getLbsOfSugarSaved()
	{
		/* 
		 * There are 454.4 grams per pound.
		 * Additionally, there are 4 calories per gram of sugar.
		 * Therefore, taking the following quotient will yield the 
		 * number of pounds of sugar avoided to date.
		 */
		return totalCalories / (454 * 4);	
	}

	public float getKilogramsOfSugarSaved()
	{
		/* 
		 * There are 0.453592 kg per lb, so simply perform 
		 * the conversion.
		 */
		return (getLbsOfSugarSaved() * 0.45392f);
	}

	public int getCaloriesSaved()
	{
		/* Instead of focusing on calories taken in,
		 * this app will focus on calories averted. 
		 */
		return totalCalories;
	}

	public Food findFood(String foodName)
	{
		for (Food f: foodsList)
			if (f.getName().equals(foodName))
				return f;
		return new Food("Food not found", 0, 0);
	}
	
	public void addFood(String foodName, int numCalories, int numGrams)
	{
		foodsList.add(new Food(foodName, numCalories, numGrams));
	}
	
	public void listAllFoods()
	{
		for (Food f: foodsList)
			System.out.println(f.getName());
	}
}

public class Prototype
{
	public static void main(String[] args)
	{
		AccessorFunctions instance = new AccessorFunctions();
		instance.addFood("Pizza", 300, 50);
		instance.addFood("Mountain Dew", 190, 336);
		instance.listAllFoods();	
	}
}
