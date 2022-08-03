package edu.upenn.cit594.util;

public class State {
	
	private String name;
	private float latitude;
	private float longitude;
	public int flu_tweet = 0;
	
	public State(String name, float lati, float longi) {
		this.name = name;
		this.latitude = lati;
		this.longitude = longi;
	}
	
	public float CompareDis(float lati, float longi) {
		float distance = (float) Math.sqrt(Math.pow((this.longitude-longi),2)+Math.pow((this.latitude-lati), 2));
		return distance;
	}
	
	public String toString() {
		return name+": "+flu_tweet;
	}

	public String getName() {
		return name;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
	

}
