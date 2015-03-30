package com.tutorazadi.CalorieAnnihilator;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper
{
    public static final String TABLE_CALORIE_ANNIHILATOR = "CalorieAnnihilator";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_SUGAR = "sugar";

    private static final String DATABASE_NAME = "calorieannihilator.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table CalorieAnnihilator(_id integer primary key autoincrement, "
            + COLUMN_CALORIES + " decimal(5,2), " + COLUMN_SUGAR + " decimal(5,2));";

    public SQLiteHelper(Context context)
    {
        super(context, "calorieannihilator.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALORIE_ANNIHILATOR);
        onCreate(db);
    }
}
