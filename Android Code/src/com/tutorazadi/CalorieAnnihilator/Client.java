package com.tutorazadi.CalorieAnnihilator;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

class Client
{
	private String name;
	private int caloriesAvoidedToDate;

	public Client(String clientName) {
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

