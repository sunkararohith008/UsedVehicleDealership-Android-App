package com.google.usedvehicledealership;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	Toolbar mToolbar;
	RecyclerView mRecyclerView;
	ArrayList<VehicleModel> arrayList;
	RecyclerView recyclerView;
	//	FloatingActionButton actionButton;
	DBManager mDBManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDBManager = new DBManager(this);
		mDBManager.open();
		mToolbar = findViewById(R.id.toolbar);
		recyclerView = findViewById(R.id.recyclerview);
//		mDBManager=new DBManager(this);
		displayVehicles();
//		setActionBar(mToolbar);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.add_record) {

			Intent add_mem = new Intent(this, AddVehicle.class);
			startActivity(add_mem);

		}
		if (id == R.id.action_sold) {
		}
		if (id == R.id.action_available_vehicles) {

		}
		if (id == R.id.action_company_details) {

		}
		return super.onOptionsItemSelected(item);
	}

	public void displayVehicles() {
		arrayList = new ArrayList<>(mDBManager.getNotes());
//	 mDBManager.fetch();
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		final RecycleAdapter adapter = new RecycleAdapter(getApplicationContext(), arrayList);
		recyclerView.setAdapter(adapter);
		adapter.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(int item) {
//				Toast.makeText(MainActivity.this, "Clicked"+item, Toast.LENGTH_SHORT).show();
				showScreen(item);
			}
		});
	}

	public void showScreen(final int position) {
		final LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View mView = layoutInflater.inflate(R.layout.details_of_vehicle, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		mDBManager = new DBManager(this);
		mDBManager.open();
		alertDialogBuilder.setView(mView);

		final EditText make = mView.findViewById(R.id.make_edit);
		make.setEnabled(false);
		final EditText model = mView.findViewById(R.id.model_edit);
		model.setEnabled(false);
		final EditText condition = mView.findViewById(R.id.condition_edit);
		condition.setEnabled(false);
		final EditText engine_cylinders = mView.findViewById(R.id.cylinder_edit);
		engine_cylinders.setEnabled(false);
		final EditText year = mView.findViewById(R.id.year_edit);
		year.setEnabled(false);
		final EditText number_of_doors = mView.findViewById(R.id.doors_edit);
		number_of_doors.setEnabled(false);
		final EditText price = mView.findViewById(R.id.price_edit);
		price.setEnabled(true);
		final EditText color = mView.findViewById(R.id.color_edit);
		color.setEnabled(false);
		final EditText date_sold = mView.findViewById(R.id.date_edit);
		final ImageView image_edit = mView.findViewById(R.id.image_edit);
		image_edit.setEnabled(false);

		Button save = mView.findViewById(R.id.save);
		Button cancel = mView.findViewById(R.id.cancel);

		make.setText(arrayList.get(position).getMake());
		model.setText(arrayList.get(position).getModel());
		condition.setText(arrayList.get(position).getCondition());
		engine_cylinders.setText(arrayList.get(position).getEngine_cylinders());
		year.setText(arrayList.get(position).getYear());
		number_of_doors.setText(arrayList.get(position).getNumber_of_doors());
		price.setText(arrayList.get(position).getPrice());
		color.setText(arrayList.get(position).getColor());
		byte[] imageProfile = arrayList.get(position).getImage();
		Bitmap mBitmap = BitmapFactory.decodeByteArray(imageProfile, 0, imageProfile.length);
		image_edit.setImageBitmap(mBitmap);
//		image_edit.setImageBitmap(arrayList.get(position).getImage());
		final AlertDialog alertDialog = alertDialogBuilder.create();

		// show it engine_cylinders.getSelectedItem().toString()
		alertDialog.show();
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (price.getText().toString().isEmpty() || date_sold.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "Field is empty!!", Toast.LENGTH_SHORT).show();
				} else {
//				Toast.makeText(MainActivity.this, "Cl "+arrayList.get(position).getId(), Toast.LENGTH_SHORT).show();
					mDBManager.update(arrayList.get(position).getId(), make.getText().toString(), model.getText().toString(), condition.getText().toString(),
							String.valueOf(engine_cylinders.getText().toString()), year.getText().toString(), number_of_doors.getText().toString(),
							price.getText().toString(), color.getText().toString(), date_sold.getText().toString());
					displayVehicles();
					alertDialog.cancel();
				}
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
	}


}