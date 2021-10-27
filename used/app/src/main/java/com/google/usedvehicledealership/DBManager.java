package com.google.usedvehicledealership;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.google.usedvehicledealership.DatabaseHelper.TABLE_NAME;

public class DBManager {

	private DatabaseHelper mDatabaseHelper;
	private Context mContext;
	private SQLiteDatabase mSQLiteDatabase;

	public DBManager(Context context) {
		mContext = context;
	}

	public DBManager open() throws SQLException {
		mDatabaseHelper = new DatabaseHelper(mContext);
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDatabaseHelper.close();
	}

	public void insert(String make, String model, String condition, String engine_cylinder,
	                   String year, String number_of_doors, String price, String color,
	                   byte[] image, String date_sold) {
		ContentValues mContentValues = new ContentValues();
		mContentValues.put(mDatabaseHelper.MAKE, make);
		mContentValues.put(mDatabaseHelper.MODEL, model);
		mContentValues.put(mDatabaseHelper.CONDITION, condition);
		mContentValues.put(mDatabaseHelper.ENGINE_CYLINDERS, engine_cylinder);
		mContentValues.put(mDatabaseHelper.YEAR, year);
		mContentValues.put(mDatabaseHelper.NUMBER_OF_DOORS, number_of_doors);
		mContentValues.put(mDatabaseHelper.PRICE, price);
		mContentValues.put(mDatabaseHelper.COLOR, color);
		mContentValues.put(mDatabaseHelper.IMAGE, image);
		mContentValues.put(mDatabaseHelper.DATE_SOLD,date_sold);

		mSQLiteDatabase.insert(TABLE_NAME,null,mContentValues);
mSQLiteDatabase.close();
	}

	public Cursor fetch(){
		String[] columns = new String[]{
				DatabaseHelper._ID,DatabaseHelper.MAKE,DatabaseHelper.MODEL,DatabaseHelper.CONDITION,DatabaseHelper.ENGINE_CYLINDERS,DatabaseHelper.YEAR,
				DatabaseHelper.NUMBER_OF_DOORS,DatabaseHelper.PRICE,DatabaseHelper.COLOR,DatabaseHelper.IMAGE,DatabaseHelper.DATE_SOLD};

		Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME,columns,null,null,null,null,null);
		if (mCursor!=null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}


	public boolean update(long _id,String make, String model, String condition, String engine_cylinder,
	                  String year, String number_of_doors, String price, String color, String date_sold){
		ContentValues mContentValues = new ContentValues();
		mContentValues.put(mDatabaseHelper.MAKE, make);
		mContentValues.put(mDatabaseHelper.MODEL, model);
		mContentValues.put(mDatabaseHelper.CONDITION, condition);
		mContentValues.put(mDatabaseHelper.ENGINE_CYLINDERS, engine_cylinder);
		mContentValues.put(mDatabaseHelper.YEAR, year);
		mContentValues.put(mDatabaseHelper.NUMBER_OF_DOORS, number_of_doors);
		mContentValues.put(mDatabaseHelper.PRICE, price);
		mContentValues.put(mDatabaseHelper.COLOR, color);
		mContentValues.put(mDatabaseHelper.DATE_SOLD,date_sold);

		mSQLiteDatabase.update(TABLE_NAME, mContentValues, DatabaseHelper._ID + " = " + _id, null);
		return true;
	}


	public void delete(long _id) {
		mSQLiteDatabase.delete(TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
	}

	public ArrayList<VehicleModel> getNotes() {
		ArrayList<VehicleModel> arrayList = new ArrayList<>();

		// select all query
		String select_query= "SELECT *FROM " + TABLE_NAME;

//		SQLiteDatabase db = new DatabaseHelper(mContext).getWritableDatabase();
		Cursor cursor = mSQLiteDatabase.rawQuery(select_query, null);

		// looping through all rows and adding to list
		if(cursor!=null && cursor.getCount()>0){
		if (cursor.moveToFirst()) {
			do {
				VehicleModel mVehicleModel = new VehicleModel();
				mVehicleModel.setId(cursor.getLong(0));
				mVehicleModel.setMake(cursor.getString(1));
				mVehicleModel.setModel(cursor.getString(2));
				mVehicleModel.setCondition(cursor.getString(3));
				mVehicleModel.setEngine_cylinders(cursor.getString(4));
				mVehicleModel.setYear(cursor.getString(5));
				mVehicleModel.setNumber_of_doors(cursor.getString(6));
				mVehicleModel.setPrice(cursor.getString(7));
				mVehicleModel.setColor(cursor.getString(8));
				mVehicleModel.setImage(cursor.getBlob(9));
				mVehicleModel.setDate_sold(cursor.getString(10));
				arrayList.add(mVehicleModel);
			}while (cursor.moveToNext());
		}}
		return arrayList;
	}
}
