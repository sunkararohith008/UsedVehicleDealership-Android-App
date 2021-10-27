package com.google.usedvehicledealership;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddVehicle extends AppCompatActivity {
	EditText make, model, condition, year, number_of_doors, price, color;
	ImageView imageView;
EditText engine_cylinders;
Button save, cancel;
	private static final int SELECT_PICTURE = 100;
DBManager mDBManager;
	private Uri selectedImageUri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_vehicle);
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
		imageView=findViewById(R.id.imageview_edit);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openImageChooser();
			}
		});
//		List<String> categories = new ArrayList<String>();
//		categories.add("Select..");
//		categories.add("V4");
//		categories.add("V6");
//		categories.add("V8");
		mDBManager=new DBManager(this);
		mDBManager.open();

		// Creating adapter for spinner
//		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//		// Drop down layout style - list view with radio button
//		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//		// attaching data adapter to spinner
//		engine_cylinders.setAdapter(dataAdapter);

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (make.getText().toString().isEmpty() || model.getText().toString().isEmpty() || condition.getText().toString().isEmpty()
				|| year.getText().toString().isEmpty() || color.getText().toString().isEmpty() ||engine_cylinders.getText().toString().isEmpty()){
					Toast.makeText(AddVehicle.this, "Field is empty!!", Toast.LENGTH_SHORT).show();
				}
				
				
//				else if(engine_cylinders.getSelectedItemPosition()==0){
//					Toast.makeText(AddVehicle.this, "Please select engine model!!", Toast.LENGTH_SHORT).show();
//				}
				
				else if(number_of_doors.getText().length()==0){
					Toast.makeText(AddVehicle.this, "Please enter the number of doors!!", Toast.LENGTH_SHORT).show();
				}
				else{
					InputStream iStream = null;
					byte[] inputData=null;
					try {
						iStream = getContentResolver().openInputStream(selectedImageUri);
						inputData = Utils.getBytes(iStream);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}


					mDBManager.insert(make.getText().toString(), model.getText().toString(),condition.getText().toString(),
							engine_cylinders.getText().toString(),year.getText().toString(),number_of_doors.getText().toString(),
							price.getText().toString(),color.getText().toString(),inputData,"");
					startActivity(new Intent(AddVehicle.this,MainActivity.class));
					finish();
				}
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AddVehicle.this, MainActivity.class));
				finish();
			}
		});
	}

	void openImageChooser() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				selectedImageUri = data.getData();
				if (null != selectedImageUri) {
					imageView.setImageURI(selectedImageUri);
				}
			}
		}
	}
}