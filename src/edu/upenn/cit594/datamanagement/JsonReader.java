package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

public class JsonReader extends Reader{
	
	public JsonReader (String name) {
		this.filename = name;
	}

	@Override
	public List<Tweet> getAllTweets() {
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		try {
			JSONParser parser = new JSONParser();
			JSONArray arry = (JSONArray) parser.parse(new FileReader(filename));
			
			for(Object tweet: arry) {
				JSONObject tweet_data = (JSONObject) tweet;
				
				JSONArray Location = (JSONArray) tweet_data.get("location");
				String Text = (String) tweet_data.get("text");
				
				Double latitude =  (double) Location.get(0);
				Double longitude = (double) Location.get(1);
				
				tweets.add(new Tweet(Float.valueOf(String.valueOf(latitude)),Float.valueOf(String.valueOf(longitude)),Text));
			}
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return tweets;
	}

	@Override
	public List<State> getAllStates() {
		// TODO Auto-generated method stub
		return null;
	}

}
