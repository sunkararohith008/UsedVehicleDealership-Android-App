package com.google.usedvehicledealership;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class VehiclesDetails extends AppCompatActivity {
	EditText make, model, condition, year, number_of_doors, price, color, date_sold;
	Spinner engine_cylinders;
	Button save, cancel;
	DBManager mDBManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicles_details);
		make=findViewById(R.id.make_edit);
		model=findViewById(R.id.model_edit);
		condition=findViewById(R.id.condition_edit);
		engine_cylinders=findViewById(R.id.cylinder_edit);
		year=findViewById(R.id.year_edit);
		number_of_doors=findViewById(R.id.doors_edit);
		price=findViewById(R.id.price_edit);
		color=findViewById(R.id.color_edit);
		save=findViewById(R.id.save);
		cancel=findViewById(R.id.cancel);

		mDBManager=new DBManager(this);
		mDBManager.open();

	}
}