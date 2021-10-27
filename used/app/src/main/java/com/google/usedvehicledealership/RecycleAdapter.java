package com.google.usedvehicledealership;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
	Context context;
	Activity activity;
	ArrayList<VehicleModel> arrayList;
	DBManager mDBManager;
	private OnItemClickListener listener;


	public RecycleAdapter(Context context, ArrayList<VehicleModel> arrayList) {
		this.context = context;
		this.arrayList = arrayList;
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.custom_view, parent, false);
		return new MyViewHolder(view, listener);
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		listener = itemClickListener;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
		holder.make.setText(arrayList.get(position).getMake());
		holder.model.setText(arrayList.get(position).getModel());
		holder.condition.setText(arrayList.get(position).getCondition());
		holder.engine_cylinders.setText(arrayList.get(position).getEngine_cylinders());
		holder.year.setText(arrayList.get(position).getYear());
		holder.number_of_doors.setText(arrayList.get(position).getNumber_of_doors());
		holder.price.setText("CAD " + arrayList.get(position).getPrice());
		holder.color.setText(arrayList.get(position).getColor());
		byte[] imageProfile = arrayList.get(position).getImage();
		Bitmap mBitmap = BitmapFactory.decodeByteArray(imageProfile, 0, imageProfile.length);
		holder.imageLoaded.setImageBitmap(mBitmap);
		if (arrayList.get(position).getDate_sold().length()!=0) {
			holder.mLinearLayout.setVisibility(View.VISIBLE);
			holder.date_sold.setText(arrayList.get(position).getDate_sold());
		}
		mDBManager = new DBManager(context);
		mDBManager.open();


	}

	@Override
	public int getItemCount() {
		return arrayList.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		TextView make, model, condition, engine_cylinders,dateText;
		TextView year, number_of_doors, price, color, image, date_sold, row_id;
		ImageView imageLoaded;
		LinearLayout mLinearLayout;

		public MyViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {
			super(itemView);
			make = itemView.findViewById(R.id.make);
			model = itemView.findViewById(R.id.model);
			condition = itemView.findViewById(R.id.condition);
			engine_cylinders = itemView.findViewById(R.id.engine);
			year = itemView.findViewById(R.id.year);
			number_of_doors = itemView.findViewById(R.id.doors);
			price = itemView.findViewById(R.id.price);
			color = itemView.findViewById(R.id.color);
			image = itemView.findViewById(R.id.image);
			dateText=itemView.findViewById(R.id.dateText);
			mLinearLayout=itemView.findViewById(R.id.linearlay);
			date_sold = itemView.findViewById(R.id.datesold);
			row_id = itemView.findViewById(R.id.row_id);
			imageLoaded = itemView.findViewById(R.id.thumbnail);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if (mListener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION) {
							listener.onItemClick(position);
						}
					}
				}
			});
		}
	}


}
