package com.google.usedvehicledealership;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Table Name
	public static final String TABLE_NAME = "VEHICLE_MANAGEMENT";
	// Table columns
	public static final String _ID = "_id";
	public static final String MAKE="make";
	public static final String MODEL="model";
	public static final String CONDITION="condition";
	public static final String ENGINE_CYLINDERS="engine_cylinder";
	public static final String YEAR="year";
	public static final String NUMBER_OF_DOORS="doors";
	public static final String PRICE="price";
	public static final String COLOR="color";
	public static final String  IMAGE="image_full_thumbnail";
	public static final String DATE_SOLD="date_sold";

	// Database Information
	static final String DB_NAME = "VEHICLE_MANAGEMENT_DATABASE";

	// database version
	static final int DB_VERSION = 1;

	// Creating table query
	private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + MAKE + " TEXT NOT NULL, " + MODEL + " TEXT NOT NULL, "+
			CONDITION+ " TEXT NOT NULL, " +ENGINE_CYLINDERS+ " TEXT NOT NULL, " +YEAR+ " TEXT NOT NULL, " +NUMBER_OF_DOORS+ " TEXT NOT NULL, " +
			PRICE+ " TEXT NOT NULL, " +COLOR+ " TEXT NOT NULL, " +IMAGE+ " BLOB NOT NULL, " +DATE_SOLD+ " TEXT);";

	public DatabaseHelper(@Nullable Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
