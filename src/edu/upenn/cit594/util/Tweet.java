package edu.upenn.cit594.util;

public class Tweet {
	
	private float latitude;
	private float longitude;
	private String text;
	
	public Tweet (float lati, float longi, String text) {
		this.latitude = lati;
		this.longitude = longi;
		this.text = text;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getText() {
		return text;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text+" |Tweet origins from "+"[latitude: "+this.latitude+", longitude: "+this.longitude+" ]";
	}
	

}
