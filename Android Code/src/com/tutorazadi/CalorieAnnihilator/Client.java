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

