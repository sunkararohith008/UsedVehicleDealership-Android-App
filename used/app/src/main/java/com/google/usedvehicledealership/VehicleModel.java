package com.google.usedvehicledealership;

public class VehicleModel {

	String make;
	String model;
	String condition;
	String engine_cylinders;
	String year;
	String number_of_doors;
	String price;
	String color;
	byte[] image;
	String date_sold;
	long id;

	public VehicleModel() {

	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getEngine_cylinders() {
		return engine_cylinders;
	}

	public void setEngine_cylinders(String engine_cylinders) {
		this.engine_cylinders = engine_cylinders;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNumber_of_doors() {
		return number_of_doors;
	}

	public void setNumber_of_doors(String number_of_doors) {
		this.number_of_doors = number_of_doors;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDate_sold() {
		return date_sold;
	}

	public void setDate_sold(String date_sold) {
		this.date_sold = date_sold;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VehicleModel(String make, String model, String condition, String engine_cylinders, String year,
	                    String number_of_doors, String price, String color, byte[] image, String date_sold,
	                    long id) {
		this.make = make;
		this.model = model;
		this.condition = condition;
		this.engine_cylinders = engine_cylinders;
		this.year = year;
		this.number_of_doors = number_of_doors;
		this.price = price;
		this.color = color;
		this.image = image;
		this.date_sold = date_sold;
		this.id=id;
	}
}
