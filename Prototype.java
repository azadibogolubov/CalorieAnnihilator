import java.util.List;
import java.util.ArrayList;

class Client
{
	private String name;
	private int caloriesAvoidedToDate;

	public Client(String clientName)
	{
		name = clientName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String newName)
	{
		name = newName;
	}

	public int getTotalCalories()
	{
		return caloriesAvoidedToDate;
	}

	public void increaseTotalCaloriesSaved(int value)
	{
		caloriesAvoidedToDate += value;
	}
}

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

class AccessorFunctions
{
	private Client newClient;
	private List<Food> foodsList;

	public AccessorFunctions()
	{
		newClient = new Client("Azadi");
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
		return (newClient.getTotalCalories() / (454 * 4));	
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
		return newClient.getTotalCalories();
	}

	public Food findFood(String foodName)
	{
		for (Food f: foodsList)
			if (f.getName().equals(foodName))
				return f;
		return new Food("Food not found", "None", 0, 0);
	}
	
	public void addFood(String foodName, String category, int numCalories, int numGrams)
	{
		foodsList.add(new Food(foodName, category, numCalories, numGrams));
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
		instance.addFood("Pizza", "Meal", 300, 50);
		instance.addFood("Mountain Dew", "Beverage", 190, 336);
		instance.listAllFoods();	
	}
}
